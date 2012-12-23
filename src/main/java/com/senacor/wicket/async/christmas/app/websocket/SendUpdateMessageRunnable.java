package com.senacor.wicket.async.christmas.app.websocket;

import java.util.Iterator;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ThreadContext;

import com.senacor.wicket.async.christmas.widgets.api.model.IAsyncModel;

/**
 * Prüft, ob Komponenten fertig geladen wurden und fügt sie in die Map ein, auf
 * der Updates gemacht werden
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class SendUpdateMessageRunnable extends WebsocketRunnable {

  public static final String UPDATE_MESSAGE = "update";

  private final Map<Component, Component> updateNeededComponents;
  private final Map<Component, Component> asyncLoadingComponents;

  public SendUpdateMessageRunnable(ThreadContext threadContext, Map<Component, Component> asyncLoadingComponents,
      Map<Component, Component> updateNeededComponents, Integer messagePageId, String messageSessionId) {
    super(threadContext, messagePageId, messageSessionId);
    this.asyncLoadingComponents = asyncLoadingComponents;
    this.updateNeededComponents = updateNeededComponents;
  }

  @Override
  public void run() {
    while (!asyncLoadingComponents.isEmpty()) {
      for (Iterator<Map.Entry<Component, Component>> iterator = asyncLoadingComponents.entrySet().iterator(); iterator.hasNext();) {
        Map.Entry<Component, Component> entry = iterator.next();
        IAsyncModel<?> model = (IAsyncModel<?>) entry.getKey().getDefaultModel();
        if (Boolean.FALSE.equals(model.isLoading())) {
          iterator.remove();
          updateNeededComponents.put(entry.getKey(), entry.getValue());
          sendMessage(UPDATE_MESSAGE);
        }
      }
      sleep(250);
    }
  }

}