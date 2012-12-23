package com.senacor.wicket.async.christmas.core.heartbeat;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;

/**
 * A self updating behavior listening to HeartBeatEvents.
 *
 * @see HeartBeatEvent
 *
 * @author Jochen Mader
 */
public class HeartBeatSelfUpdatingBehavior extends Behavior{

  private int reactOnBeat;
  private int beatCount;

  public HeartBeatSelfUpdatingBehavior() {
    this.reactOnBeat = 1;
  }

  /**
   * Construct a behavior that only reacts on each n-th beat.
   * @param reactOnBeat
   */
  public HeartBeatSelfUpdatingBehavior(int reactOnBeat) {
    this.reactOnBeat = reactOnBeat;
  }

    @Override
  public void onEvent(Component component, IEvent<?> event) {
    if (event.getPayload() instanceof HeartBeatEvent) {
      beatCount++;
      HeartBeatEvent heartBeatEvent = (HeartBeatEvent) event.getPayload();
      if(beatCount == reactOnBeat) {
        beatCount = 0;
        heartBeatEvent.getTarget().add(component);
      }
      heartBeatEvent.getHeartBeatEventSource().continueBeating();
    }
  }
}
