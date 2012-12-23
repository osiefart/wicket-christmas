package com.senacor.wicket.async.christmas.widgets.twitter;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.springframework.social.twitter.api.Tweet;

/**
 * Zeigt die aktuellen Twitter-informationen an.
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class TwitterInformationPanel extends Panel {

  /**
   * Konstruktor.
   * 
   * @param id
   *          die wicket id
   * @param messageModel
   *          das Model, das die Nachrichten von Twitter liefert.
   */
  public TwitterInformationPanel(String id, IModel<Tweet> messageModel) {
    super(id, messageModel);
    setOutputMarkupPlaceholderTag(true);
    add(new Image("animatedTwitter", new PackageResourceReference(TwitterInformationPanel.class, "animated_twitter.gif")));
    add(createTextComponent("text", new TextModel(messageModel)));
  }

  protected Component createTextComponent(String id, IModel<String> textModel) {
    return new Label(id, textModel).setOutputMarkupId(true);
  }

  private static class TextModel extends AbstractReadOnlyModel<String> {

    private final IModel<Tweet> tweetModel;

    private TextModel(IModel<Tweet> tweetModel) {
      this.tweetModel = tweetModel;
    }

    @Override
    public String getObject() {
      Tweet tweet = tweetModel.getObject();
      if (tweet != null)
        return tweet.getFromUser() + ":" + tweet.getText();
      else
        return "N/A";
    }

    @Override
    public void detach() {
      super.detach();
      tweetModel.detach();
    }

  }

}