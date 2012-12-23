package com.senacor.wicket.async.christmas.app.lazyloading;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

import com.senacor.wicket.async.christmas.core.counter.PageRequestCounter;
import com.senacor.wicket.async.christmas.widgets.dilbert.DilbertBlockingModel;
import com.senacor.wicket.async.christmas.widgets.dilbert.DilbertInformationPanel;
import com.senacor.wicket.async.christmas.widgets.train.TrainInformationPanel;
import com.senacor.wicket.async.christmas.widgets.train.TrainMessageBlockingModel;
import com.senacor.wicket.async.christmas.widgets.twitter.TwitterInformationPanel;
import com.senacor.wicket.async.christmas.widgets.twitter.TwitterMessageBlockingModel;

/**
 * Seite mit mehreren LazyLoadingPanels
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class LazyLoadingPage extends WebPage {

  public LazyLoadingPage() {

    add(new PageRequestCounter("counter"));

    Panel trainInformationLazyLoadPanel = new AjaxLazyLoadPanel("trainInformationPanel") {

      @Override
      public Component getLazyLoadComponent(String markupId) {
        TrainInformationPanel trainInformationPanel = new TrainInformationPanel(markupId, new TrainMessageBlockingModel()) {
          @Override
          protected Component createTextComponent(String id, IModel<String> textModel) {
            return super.createTextComponent(id, textModel).add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(5)));
          }
        };
        return trainInformationPanel;
      }

    };
    add(trainInformationLazyLoadPanel);

    Panel twitterInformationLazyLoadPanel = new AjaxLazyLoadPanel("twitterInformationPanel") {

      @Override
      public Component getLazyLoadComponent(String markupId) {
        TwitterInformationPanel twitterInformationPanel = new TwitterInformationPanel(markupId, new TwitterMessageBlockingModel()) {
          @Override
          protected Component createTextComponent(String id, IModel<String> textModel) {
            return super.createTextComponent(id, textModel).add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(15)));
          }
        };
        return twitterInformationPanel;
      }

    };
    add(twitterInformationLazyLoadPanel);

    Panel dilbertInformationLazyLoadPanel = new AjaxLazyLoadPanel("dilbertInformationPanel") {

      @Override
      public Component getLazyLoadComponent(String markupId) {
        DilbertInformationPanel dilbertInformationPanel = new DilbertInformationPanel(markupId, new DilbertBlockingModel()) {
          @Override
          protected Component createTextComponent(String id, IModel<String> textModel) {
            return super.createTextComponent(id, textModel).add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(45)));
          }
        };
        return dilbertInformationPanel;
      }

    };
    add(dilbertInformationLazyLoadPanel);

  }
}