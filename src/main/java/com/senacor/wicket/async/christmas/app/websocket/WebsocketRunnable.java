package com.senacor.wicket.async.christmas.app.websocket;

import java.io.IOException;

import org.apache.wicket.Application;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.IWebSocketConnectionRegistry;
import org.apache.wicket.protocol.ws.api.SimpleWebSocketConnectionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.senacor.wicket.async.christmas.app.ChristmasApplication;

/**
 * Websocket Runnable
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public abstract class WebsocketRunnable implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(WebsocketRunnable.class);

  private final Integer messagePageId;
  private final String messageSessionId;
  private final ThreadContext threadContext;

  public WebsocketRunnable(ThreadContext threadContext, Integer messagePageId, String messageSessionId) {
    this.threadContext = threadContext;
    this.messagePageId = messagePageId;
    this.messageSessionId = messageSessionId;
  }

  protected void sleep(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected void sendMessage(String message) {
    ThreadContext.restore(threadContext);
    final Application application = ChristmasApplication.get();

    IWebSocketConnectionRegistry registry = new SimpleWebSocketConnectionRegistry();
    if ((messagePageId != null) && (messageSessionId != null)) {
      IWebSocketConnection wsConnection = registry.getConnection(application, messageSessionId, messagePageId);
      if ((wsConnection != null) && wsConnection.isOpen()) {
        try {
          wsConnection.sendMessage(message);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } else {
      LOG.error("No update over websocket possible");
    }
  }

}