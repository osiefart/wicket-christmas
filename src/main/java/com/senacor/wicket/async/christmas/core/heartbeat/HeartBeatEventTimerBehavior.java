package com.senacor.wicket.async.christmas.core.heartbeat;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.util.time.Duration;

/**
 * A simple Timer used for updating HeartBeatEventTimerBehaviors
 * 
 * @see HeartBeatEventTimerBehavior
 * 
 * @author Timo Weber
 * @author Jochen Mader
 */
public class HeartBeatEventTimerBehavior extends AbstractAjaxTimerBehavior implements IHeartBeatEventSource {

  private boolean needed = true;

  public HeartBeatEventTimerBehavior(Duration updateInterval) {
    super(updateInterval);
  }

  @Override
  protected void onTimer(AjaxRequestTarget target) {
    needed = false;
    getComponent().send(getComponent(), Broadcast.BREADTH, new HeartBeatEvent(target, this));
    if (!needed) {
      target.add(getComponent());
    }
  }

  @Override
  public boolean isEnabled(Component component) {
    return needed;
  }

  @Override
  protected void onBind() {
    super.onBind();
    Component component = getComponent();
    if (!Page.class.isAssignableFrom(component.getClass())) {
      throw new IllegalArgumentException("HeartBehavior is only allowed for Pages");
    }
  }

  @Override
  public void continueBeating() {
    needed = true;
  }
}
