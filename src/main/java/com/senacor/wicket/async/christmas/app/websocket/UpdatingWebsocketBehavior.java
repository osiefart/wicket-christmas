package com.senacor.wicket.async.christmas.app.websocket;

import static com.senacor.wicket.async.christmas.app.websocket.SendRefreshMessageRunnable.REFRESH_MESSAGE;
import static com.senacor.wicket.async.christmas.app.websocket.SendUpdateMessageRunnable.UPDATE_MESSAGE;
import static org.apache.commons.lang.BooleanUtils.isTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.wicket.Component;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.ajax.WebSocketRequestHandler;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.message.ClosedMessage;
import org.apache.wicket.protocol.ws.api.message.ConnectedMessage;
import org.apache.wicket.protocol.ws.api.message.TextMessage;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.senacor.wicket.async.christmas.widgets.api.model.IAsyncModel;

/**
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class UpdatingWebsocketBehavior extends WebSocketBehavior {

  private static final Logger LOG = LoggerFactory.getLogger(UpdatingWebsocketBehavior.class);

  // Variablen für den update-Mechanismus
  private final ConcurrentHashMap<Component, Component> asyncLoadingComponents;
  private final ConcurrentHashMap<Component, Component> updatableComponents;

  // Variablen für den refresh-Mechanismus
  private final List<Component> refreshableComponents;
  private final AtomicBoolean finishThread;

  public UpdatingWebsocketBehavior(ConcurrentHashMap<Component, Component> loadingComponents) {

    // Variablen für den update-Mechanismus
    this.asyncLoadingComponents = loadingComponents;
    updatableComponents = new ConcurrentHashMap<Component, Component>();

    // Variablen für den refresh-Mechanismus
    refreshableComponents = new CopyOnWriteArrayList<Component>();
    finishThread = new AtomicBoolean(false);

  }

  @Override
  protected void onConnect(ConnectedMessage message) {
    super.onConnect(message);

    // Das Laden in den einzelnen Komponenten anstoßen
    ThreadContext threadContext = ThreadContext.get(false);

    // Start the update thread
    Thread updateThread = new Thread(new SendUpdateMessageRunnable(threadContext, asyncLoadingComponents, updatableComponents, message.getPageId(),
        message.getSessionId()));
    updateThread.start();

    // Start the refresh thread
    Thread refreshThread = new Thread(new SendRefreshMessageRunnable(threadContext, refreshableComponents, finishThread, message.getPageId(),
        message.getSessionId()));
    refreshThread.start();

  }

  @Override
  protected void onMessage(WebSocketRequestHandler handler, TextMessage message) {
    super.onMessage(handler, message);
    if (UPDATE_MESSAGE.equals(message.getText())) {
      LOG.info("websocket message: " + message.getText());
      update(handler);
    } else if (REFRESH_MESSAGE.equals(message.getText())) {
      LOG.info("websocket message: " + message.getText());
      refresh(handler);
    } else {
      LOG.error("Unknown websocket message: " + message.getText());
    }
  }

  private void refresh(WebSocketRequestHandler handler) {
    for (Component component : refreshableComponents) {

      // Notwendig wegen vermutlichem Bug im wicket websocket framework
      RequestCycle.get().getUrlRenderer().setBaseUrl(Url.parse("websockets"));

      IAsyncModel<?> model = (IAsyncModel<?>) component.getDefaultModel();
      if (isTrue(model.hasNext())) {
        handler.add(component);
      }

    }
  }

  private void update(WebSocketRequestHandler handler) {
    for (Iterator<Map.Entry<Component, Component>> iterator = updatableComponents.entrySet().iterator(); iterator.hasNext();) {
      Map.Entry<Component, Component> entry = iterator.next();
      entry.getValue().replaceWith(entry.getKey());

      // Notwendig wegen vermutlichem Bug im wicket websocket framework
      RequestCycle.get().getUrlRenderer().setBaseUrl(Url.parse("websockets"));

      handler.add(entry.getKey());
      iterator.remove();
      refreshableComponents.add(entry.getKey());
    }
  }

  @Override
  public void renderHead(Component component, IHeaderResponse response) {
    super.renderHead(component, response);
    response.render(JavaScriptHeaderItem.forReference(WebsocketClientResourceReference.get()));
  }

  @Override
  protected void onClose(ClosedMessage message) {
    super.onClose(message);

    // Stop the update thread
    asyncLoadingComponents.clear();
    updatableComponents.clear();

    // stop the refresh thread
    finishThread.set(true);
  }

}