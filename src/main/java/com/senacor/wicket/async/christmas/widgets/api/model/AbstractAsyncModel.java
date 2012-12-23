package com.senacor.wicket.async.christmas.widgets.api.model;

import org.apache.wicket.model.AbstractReadOnlyModel;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageSource;

/**
 * @author Jochen Mader
 */
public abstract class AbstractAsyncModel<T> extends AbstractReadOnlyModel<T> implements IAsyncModel<T> {

  private transient T lastMessage = null;

  @Override
  public T getObject() {
    T ret = getMessageSource().getMessage();
    if (ret == null) {
      getMessageSource().loadNext();
      ret = lastMessage;
    } else {
      lastMessage = ret;
    }
    return ret;
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

  public abstract IMessageSource<T> getMessageSource();
}
