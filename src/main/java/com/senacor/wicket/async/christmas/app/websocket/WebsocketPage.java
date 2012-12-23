package com.senacor.wicket.async.christmas.app.websocket;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.WebSocketRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.ws.api.message.TextMessage;

import com.senacor.wicket.async.christmas.core.counter.PageRequestCounter;
import com.senacor.wicket.async.christmas.core.indicator.LoadingIndicatorPanel;
import com.senacor.wicket.async.christmas.widgets.dilbert.DilbertAsyncModel;
import com.senacor.wicket.async.christmas.widgets.dilbert.DilbertInformationPanel;
import com.senacor.wicket.async.christmas.widgets.train.TrainInformationPanel;
import com.senacor.wicket.async.christmas.widgets.train.TrainMessageAsyncModel;
import com.senacor.wicket.async.christmas.widgets.twitter.TwitterInformationPanel;
import com.senacor.wicket.async.christmas.widgets.twitter.TwitterMessageAsyncModel;

/**
 * Seite mit Websockets
 * 
 * <p>
 * http://splitshade.wordpress.com/2012/11/04/wicket-6-2-websockets-und-jquery-
 * visualize-die-richtige-atmosphare-schaffen/
 * </p>
 * <p>
 * http://wicketinaction.com/2012/07/wicket-6-native-websockets/
 * </p>
 * 
 * 
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class WebsocketPage extends WebPage {

  private final ConcurrentHashMap<Component, Component> loadingComponents;

  public WebsocketPage() {

    loadingComponents = new ConcurrentHashMap<Component, Component>();

    final PageRequestCounter pageRequestCounter = new PageRequestCounter("counter");
    add(pageRequestCounter);

    add(new UpdatingWebsocketBehavior(loadingComponents) {
      @Override
      protected void onMessage(WebSocketRequestHandler handler, TextMessage message) {
        super.onMessage(handler, message);
        handler.add(pageRequestCounter);
      }
    });

    // Train
    Component trainLoadingIndicator = new LoadingIndicatorPanel("trainInformation");
    Component trainInformationPanel = new TrainInformationPanel("trainInformation", new TrainMessageAsyncModel());
    loadingComponents.put(trainInformationPanel, trainLoadingIndicator);
    add(trainLoadingIndicator);

    // Twitter
    Component twitterLoadingIndicator = new LoadingIndicatorPanel("twitterInformation");
    Component twitterInformationPanel = new TwitterInformationPanel("twitterInformation", new TwitterMessageAsyncModel());
    loadingComponents.put(twitterInformationPanel, twitterLoadingIndicator);
    add(twitterLoadingIndicator);

    // Dilbert
    Component dilbertLoadingIndicator = new LoadingIndicatorPanel("dilbertInformation");
    Component dilbertInformationPanel = new DilbertInformationPanel("dilbertInformation", new DilbertAsyncModel());
    loadingComponents.put(dilbertInformationPanel, dilbertLoadingIndicator);
    add(dilbertLoadingIndicator);

  }

  @Override
  protected void onConfigure() {
    super.onConfigure();

    // Das Laden in den einzelnen Komponenten anstoßen
    for (Component asyncLoadingComponent : loadingComponents.keySet()) {
      asyncLoadingComponent.getDefaultModel().getObject();
    }

  }

}