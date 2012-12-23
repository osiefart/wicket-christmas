package com.senacor.wicket.async.christmas.widgets.api.model;

import org.apache.wicket.model.AbstractReadOnlyModel;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageSource;

/**
 * Model that blocks the Thread and waits for the Result of the loading call.
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public abstract class AbstractBlockingModel<T> extends AbstractReadOnlyModel<T> implements IAsyncModel<T> {

  @Override
  public T getObject() {
    return getMessageSource().getMessageSync();
  }

  @Override
  public Boolean isLoading() {
    return getMessageSource().isLoading();
  }

  @Override
  public void startLoading() {
    getMessageSource().loadNext();
  }

  @Override
  public Boolean hasNext() {
    return getMessageSource().hasNext();
  }

  protected abstract IMessageSource<T> getMessageSource();
}