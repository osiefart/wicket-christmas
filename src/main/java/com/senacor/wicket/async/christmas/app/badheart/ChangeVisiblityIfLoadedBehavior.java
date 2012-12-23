package com.senacor.wicket.async.christmas.app.badheart;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.time.Duration;

import com.senacor.wicket.async.christmas.widgets.api.model.IAsyncModel;

/**
 * Blendet den LoadingIndicator aus und das InformationPanel ein, sobald das
 * Laden abgeschlosssen ist.
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class ChangeVisiblityIfLoadedBehavior extends AbstractAjaxTimerBehavior {

  private final Component initial;
  private final Component replacement;
  private final IAsyncModel<?> model;

  public ChangeVisiblityIfLoadedBehavior(Component initial, Component replacement, IAsyncModel<?> model) {
    super(Duration.ONE_SECOND);
    this.initial = initial;
    this.replacement = replacement;
    this.model = model;

    // Sichtbarkeit schalten
    this.initial.setVisibilityAllowed(true);
    this.replacement.setVisibilityAllowed(false);
  }

  @Override
  public void onConfigure(Component component) {
    super.onConfigure(component);
    model.getObject();
  }

  @Override
  protected void onTimer(AjaxRequestTarget target) {
    if (!model.isLoading()) {

      // Sichtbarkeit umschalten
      this.initial.setVisibilityAllowed(false);
      this.replacement.setVisibilityAllowed(true);

      // Komponenten aktualisieren
      target.add(initial);
      target.add(replacement);

    }
  }

  @Override
  public void detach(Component component) {
    super.detach(component);
    model.detach();
  }

}