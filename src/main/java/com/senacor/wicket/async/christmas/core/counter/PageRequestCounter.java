package com.senacor.wicket.async.christmas.core.counter;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * Render the page counter from the page on every Request
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class PageRequestCounter extends Label {

  public PageRequestCounter(String id) {
    super(id);
    setOutputMarkupId(true);
    setDefaultModel(new CountingModel());
  }

  @Override
  public void onEvent(IEvent<?> event) {
    if (event.getPayload() instanceof AjaxRequestTarget) {
      ((AjaxRequestTarget) event.getPayload()).add(this);
    }
  }

  /**
   * Counts how often getObject was called
   * 
   * @author Olaf Siefart, Senacor Technologies AG
   */
  private static class CountingModel extends AbstractReadOnlyModel<Integer> {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Integer getObject() {
      return counter.incrementAndGet();
    }

  }

}