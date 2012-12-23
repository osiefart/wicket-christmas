package com.senacor.wicket.async.christmas.app.goodheart;

import com.senacor.wicket.async.christmas.core.heartbeat.HeartBeatSelfUpdatingBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

import com.senacor.wicket.async.christmas.core.counter.PageRequestCounter;
import com.senacor.wicket.async.christmas.core.heartbeat.HeartBeatConsumerBehavior;
import com.senacor.wicket.async.christmas.core.heartbeat.HeartBeatEventTimerBehavior;
import com.senacor.wicket.async.christmas.widgets.dilbert.DilbertAsyncModel;
import com.senacor.wicket.async.christmas.widgets.dilbert.DilbertInformationPanel;
import com.senacor.wicket.async.christmas.widgets.train.TrainInformationPanel;
import com.senacor.wicket.async.christmas.widgets.train.TrainMessageAsyncModel;
import com.senacor.wicket.async.christmas.widgets.twitter.TwitterInformationPanel;
import com.senacor.wicket.async.christmas.widgets.twitter.TwitterMessageAsyncModel;

/**
 * This page avoids the problem of too man requests by only registering one
 * Timer-Behavior (the Heart) so only one request is used for updating, no
 * matter how many panels are used.
 * 
 * @author Jochen Mader, Senacor Technologies AG
 */
public class HeartBeatPage extends WebPage {

  public HeartBeatPage() {
    add(new PageRequestCounter("counter"));

    HeartBeatEventTimerBehavior heart = new HeartBeatEventTimerBehavior(Duration.seconds(3));
    add(heart);

    TrainMessageAsyncModel trainMessageModel = new TrainMessageAsyncModel();
    trainMessageModel.startLoading();
    Panel trainPanel = new TrainInformationPanel("dataPanel1", trainMessageModel) {
      @Override
      protected Component createTextComponent(String id, IModel<String> textModel) {
        return super.createTextComponent(id, textModel).add(new HeartBeatSelfUpdatingBehavior());
      }
    };
    HeartBeatConsumerBehavior trainSpinner = new HeartBeatConsumerBehavior(trainMessageModel);
    add(trainPanel.add(trainSpinner));

    TwitterMessageAsyncModel twitterMessageModel = new TwitterMessageAsyncModel();
    twitterMessageModel.startLoading();
    Panel twitterPanel = new TwitterInformationPanel("dataPanel2", twitterMessageModel) {
      @Override
      protected Component createTextComponent(String id, IModel<String> textModel) {
        return super.createTextComponent(id, textModel).add(new HeartBeatSelfUpdatingBehavior());
      }
    };
    HeartBeatConsumerBehavior twitterSpinner = new HeartBeatConsumerBehavior(twitterMessageModel);
    add(twitterPanel.add(twitterSpinner));

    DilbertAsyncModel dilbertRSSFeedModel = new DilbertAsyncModel();
    dilbertRSSFeedModel.startLoading();
    Panel dilbertPanel = new DilbertInformationPanel("dataPanel3", dilbertRSSFeedModel) {
      @Override
      protected Component createTextComponent(String id, IModel<String> textModel) {
        return super.createTextComponent(id, textModel).add(new HeartBeatSelfUpdatingBehavior(3));
      }
    };
    HeartBeatConsumerBehavior dilbertSpinner = new HeartBeatConsumerBehavior(dilbertRSSFeedModel);
    add(dilbertPanel.add(dilbertSpinner));

    trainMessageModel.startLoading();
    twitterMessageModel.startLoading();
    dilbertRSSFeedModel.startLoading();

  }
}