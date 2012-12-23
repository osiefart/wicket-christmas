package com.senacor.wicket.async.christmas.widgets.train;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Zeigt alle Zuginformationen an.
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class TrainInformationPanel extends Panel {

  /**
   * Konstruktor.
   * 
   * @param id
   *          die wicket id
   * @param messageModel
   *          das Model, aus dem die Nachricht kommt.
   */
  public TrainInformationPanel(String id, IModel<String> messageModel) {
    super(id, messageModel);
    setOutputMarkupPlaceholderTag(true);
    add(new Image("animatedTrain", new PackageResourceReference(TrainInformationPanel.class, "animated_train.gif")));
    add(createTextComponent("text", messageModel));
  }

  protected Component createTextComponent(String id, IModel<String> textModel) {
    return new Label(id, textModel).setOutputMarkupId(true);
  }

}