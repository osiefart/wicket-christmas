package com.senacor.wicket.async.christmas.app.badheart;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.springframework.social.twitter.api.Tweet;

import com.senacor.wicket.async.christmas.core.counter.PageRequestCounter;
import com.senacor.wicket.async.christmas.core.indicator.LoadingIndicatorPanel;
import com.senacor.wicket.async.christmas.widgets.api.model.IAsyncModel;
import com.senacor.wicket.async.christmas.widgets.dilbert.DilbertAsyncModel;
import com.senacor.wicket.async.christmas.widgets.dilbert.DilbertInformationPanel;
import com.senacor.wicket.async.christmas.widgets.train.TrainInformationPanel;
import com.senacor.wicket.async.christmas.widgets.train.TrainMessageAsyncModel;
import com.senacor.wicket.async.christmas.widgets.twitter.TwitterInformationPanel;
import com.senacor.wicket.async.christmas.widgets.twitter.TwitterMessageAsyncModel;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * A Bad Heart pumps too much. By adding a Time-Behavior to each of the included
 * panels a request is generated for EACH ONE. The more panels the more requests
 * you got. A nice way to load-test your server.
 * 
 * @author Jochen Mader, Senacor Technologies AG
 */
public class BadHeartPage extends WebPage {

  /**
   * Konstruktor.
   */
  public BadHeartPage() {
    add(new PageRequestCounter("counter"));
    addTrainComponents();
    addTwitterComponents();
    addDilbertComponents();
  }

  private void addTrainComponents() {
    IAsyncModel<String> trainMessageModel = new TrainMessageAsyncModel();
    Component trainLoadingIndicator = new LoadingIndicatorPanel("trainLoadingIndicator");
    TrainInformationPanel trainInformationPanel = new TrainInformationPanel("trainInformationPanel", trainMessageModel) {
      @Override
      protected Component createTextComponent(String id, IModel<String> textModel) {
        return super.createTextComponent(id, textModel).add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(5)));
      }
    };
    add(trainLoadingIndicator.add(new ChangeVisiblityIfLoadedBehavior(trainLoadingIndicator, trainInformationPanel, trainMessageModel)));
    add(trainInformationPanel);
  }

  private void addTwitterComponents() {
    IAsyncModel<Tweet> twitterMessageModel = new TwitterMessageAsyncModel();
    Component twitterLoadingIndicator = new LoadingIndicatorPanel("twitterLoadingIndicator");
    TwitterInformationPanel twitterInformationPanel = new TwitterInformationPanel("twitterInformationPanel", twitterMessageModel) {
      @Override
      protected Component createTextComponent(String id, IModel<String> textModel) {
        return super.createTextComponent(id, textModel).add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(15)));
      }
    };
    add(twitterLoadingIndicator.add(new ChangeVisiblityIfLoadedBehavior(twitterLoadingIndicator, twitterInformationPanel, twitterMessageModel)));
    add(twitterInformationPanel);
  }

  private void addDilbertComponents() {
    IAsyncModel<SyndFeed> dilbertFeedModel = new DilbertAsyncModel();
    Component dilbertLoadingIndicator = new LoadingIndicatorPanel("dilbertLoadingIndicator");
    Component dilbertInformationPanel = new DilbertInformationPanel("dilbertInformationPanel", dilbertFeedModel) {
      @Override
      protected Component createTextComponent(String id, IModel<String> textModel) {
        return super.createTextComponent(id, textModel).add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(45)));
      }
    };
    add(dilbertLoadingIndicator.add(new ChangeVisiblityIfLoadedBehavior(dilbertLoadingIndicator, dilbertInformationPanel, dilbertFeedModel)));
    add(dilbertInformationPanel);
  }

}
