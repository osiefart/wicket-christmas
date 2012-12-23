package com.senacor.wicket.async.christmas.core.snow;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.markup.html.WebPage;

/**
 * Snow is hard work. Use a {@link IComponentInstantiationListener} and add the
 * {@link LetItSnowBehavior} to every {@link WebPage}. So you don't miss the
 * error pages :-)
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class LetItSnowComponentInstantiationListener implements IComponentInstantiationListener {

  @Override
  public void onInstantiation(Component component) {
    if (component instanceof WebPage) {
      component.add(new LetItSnowBehavior());
    }
  }

}