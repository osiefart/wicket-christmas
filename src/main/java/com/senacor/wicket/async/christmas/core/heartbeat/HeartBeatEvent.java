package com.senacor.wicket.async.christmas.core.heartbeat;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Event broadcasted by the @see HeartBeatEventTimerBehavior
 * 
 * @author Jochen Mader
 */
public class HeartBeatEvent {

  private final AjaxRequestTarget target;
  private final IHeartBeatEventSource behavior;

  public HeartBeatEvent(AjaxRequestTarget target, IHeartBeatEventSource behavior) {
    this.target = target;
    this.behavior = behavior;
  }

  public AjaxRequestTarget getTarget() {
    return target;
  }

  public IHeartBeatEventSource getHeartBeatEventSource() {
    return behavior;
  }

}
