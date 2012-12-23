package com.senacor.wicket.async.christmas.app.websocket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.protocol.ws.api.WicketWebSocketJQueryResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class WebsocketClientResourceReference extends PackageResourceReference {

  private static WebsocketClientResourceReference instance = new WebsocketClientResourceReference();

  public WebsocketClientResourceReference() {
    super(WebsocketClientResourceReference.class, "client.js");
  }

  @Override
  public java.lang.Iterable<? extends org.apache.wicket.markup.head.HeaderItem> getDependencies() {
    List<HeaderItem> result = new ArrayList<HeaderItem>();
    result.add(JavaScriptHeaderItem.forReference(WicketWebSocketJQueryResourceReference.get()));
    return result;
  }

  public static WebsocketClientResourceReference get() {
    return instance;
  }

}