package com.senacor.wicket.async.christmas.widgets.dilbert;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Zeigt den aktuelle Dilbert-Comic an.
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class DilbertInformationPanel extends Panel {

  /**
   * Konstruktor.
   * 
   * @param id
   *          die wicket id
   * @param messageModel
   *          das Model, das die Dilbert <img ...> -Informationen liefert.
   */
  public DilbertInformationPanel(String id, IModel<SyndFeed> messageModel) {
    super(id, messageModel);
    setOutputMarkupPlaceholderTag(true);
    add(createTextComponent("text", new PropertyModel<String>(messageModel, "description.value")));
  }

  protected Component createTextComponent(String id, IModel<String> textModel) {
    return new Label(id, textModel).setEscapeModelStrings(false).setOutputMarkupId(true);
  }

}