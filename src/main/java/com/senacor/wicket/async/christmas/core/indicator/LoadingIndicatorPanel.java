package com.senacor.wicket.async.christmas.core.indicator;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Panel, das den Wicket Loading Indicator anzeigt.
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class LoadingIndicatorPanel extends Panel {

  /**
   * Konstruktor.
   * 
   * @param id
   *          die wicket id
   */
  public LoadingIndicatorPanel(String id) {
    super(id);
    add(new Image("indicator", AbstractDefaultAjaxBehavior.INDICATOR));
    setOutputMarkupId(true);
  }
}
