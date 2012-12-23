package com.senacor.wicket.async.christmas.widgets.api.message;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;

/**
 * @author Jochen Mader
 */
public interface IMessageLoader<T> {

  @Async
  Future<List<T>> load();

}
