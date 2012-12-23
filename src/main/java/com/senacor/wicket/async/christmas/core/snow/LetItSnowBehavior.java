package com.senacor.wicket.async.christmas.core.snow;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * This Behavior adds the {@link LetItSnowResourceReference} to the binding
 * component.
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class LetItSnowBehavior extends Behavior {

  @Override
  public void renderHead(Component component, IHeaderResponse response) {
    super.renderHead(component, response);
    response.render(JavaScriptHeaderItem.forReference(LetItSnowResourceReference.get()));
  }

}
