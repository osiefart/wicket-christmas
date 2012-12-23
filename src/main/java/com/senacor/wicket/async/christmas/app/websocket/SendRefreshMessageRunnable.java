package com.senacor.wicket.async.christmas.app.websocket;

import static org.apache.commons.lang.BooleanUtils.isTrue;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.wicket.Component;
import org.apache.wicket.ThreadContext;

import com.senacor.wicket.async.christmas.widgets.api.model.IAsyncModel;

/**
 * Prüft, ob Komponenten fertig geladen wurden und fügt sie in die Map ein, auf
 * der Updates gemacht werden
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class SendRefreshMessageRunnable extends WebsocketRunnable {

  public static final String REFRESH_MESSAGE = "refresh";

  private final List<Component> refreshableComponents;
  private final AtomicBoolean finishThread;

  public SendRefreshMessageRunnable(ThreadContext threadContext, List<Component> refreshableComponents, AtomicBoolean finishThread, Integer messagePageId,
      String messageSessionId) {
    super(threadContext, messagePageId, messageSessionId);
    this.refreshableComponents = refreshableComponents;
    this.finishThread = finishThread;
  }

  @Override
  public void run() {
    while (!finishThread.get()) {
      boolean refreshNeeded = false;
      for (Component component : refreshableComponents) {
        IAsyncModel<?> model = (IAsyncModel<?>) component.getDefaultModel();
        if (isTrue(model.hasNext())) {
          refreshNeeded = true;
          break;
        }
      }
      if (refreshNeeded) {
        sendMessage(REFRESH_MESSAGE);
      }
      sleep(30000);
    }
  }

}