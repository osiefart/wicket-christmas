package com.senacor.wicket.async.christmas.widgets.api.model;

import org.apache.wicket.model.IModel;

/**
 * @author Jochen Mader
 */
public interface IAsyncModel<T> extends IModel<T> {

  Boolean isLoading();

  void startLoading();

  Boolean hasNext();

}