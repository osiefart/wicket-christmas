package com.senacor.wicket.async.christmas.core.heartbeat;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;

import com.senacor.wicket.async.christmas.core.indicator.LoadingIndicatorPanel;
import com.senacor.wicket.async.christmas.widgets.api.model.IAsyncModel;

/**
 * A behavior which checks a set of IAsyncModels if they're done loading their
 * content. In the meantime it displays a Spinner. To display something else
 * overwrite #newSpinner(). To react to stopping the spinner overwrite #done().
 * 
 * @see IAsyncModel
 * 
 * @author Timo Weber
 * @author Jochen Mader
 */
public class HeartBeatConsumerBehavior extends Behavior {

  private final IAsyncModel<?>[] models;

  private Component contentComponent;
  private Component controllingComponent;

  public HeartBeatConsumerBehavior(IAsyncModel<?>... models) {
    this.models = models;
  }

  @Override
  public void onConfigure(Component component) {
    super.onConfigure(component);
    if (component.getPage().getBehaviors(HeartBeatEventTimerBehavior.class).isEmpty()) {
      throw new WicketRuntimeException("A HeartBeatEventTimerBehavior is needed!");
    }
    if (controllingComponent.equals(contentComponent)) {
      Component spinningComponent = newSpinner(contentComponent.getId());
      controllingComponent = spinningComponent;
      contentComponent.replaceWith(spinningComponent);
      contentComponent.remove(this);
      spinningComponent.add(this);
    }
  }

  @Override
  public void bind(Component controllingComponent) {
    super.bind(controllingComponent);
    this.controllingComponent = controllingComponent;
    if (contentComponent == null) {
      contentComponent = controllingComponent;
    }
    contentComponent.setOutputMarkupId(true);
  }

  @Override
  public void onEvent(Component component, IEvent<?> event) {
    super.onEvent(component, event);
    if (event.getPayload() instanceof HeartBeatEvent) {
      HeartBeatEvent heartBeatEvent = (HeartBeatEvent) event.getPayload();
      if (!isSpinningNeeded()) {
        controllingComponent.replaceWith(contentComponent);
        heartBeatEvent.getTarget().add(contentComponent);
        done();
      } else {
        heartBeatEvent.getHeartBeatEventSource().continueBeating();
      }
    }
  }

  /**
   * Called when the spinner is removed.
   */
  protected void done() {

  }

  private Boolean isSpinningNeeded() {
    for (IAsyncModel<?> model : models) {
      if (model.isLoading()) {
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }

  protected Component newSpinner(String id) {
    return new LoadingIndicatorPanel(id);
  }

  @Override
  public void detach(Component component) {
    super.detach(component);
    for (IAsyncModel<?> model : models) {
      model.detach();
    }
  }
}
