package com.senacor.wicket.async.christmas.widgets.api.message;

/**
 * @author Jochen Mader
 */
public interface IMessageSource<T> {

  T getMessage();

  T getMessageSync();

  Boolean isLoading();

  Boolean hasNext();

  void loadNext();
}