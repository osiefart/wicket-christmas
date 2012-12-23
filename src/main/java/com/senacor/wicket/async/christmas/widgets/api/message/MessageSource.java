package com.senacor.wicket.async.christmas.widgets.api.message;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Loads messages from a given source
 * 
 * @author Jochen Mader
 */
public abstract class MessageSource<T> implements IMessageSource<T> {

  private final LinkedBlockingQueue<T> messageQueue = new LinkedBlockingQueue<T>();
  private final AtomicBoolean loading = new AtomicBoolean();
  private Future<List<T>> future = null;

  public abstract IMessageLoader<T> getMessageLoader();

  @Override
  public T getMessage() {
    if (messageQueue.peek() == null) {
      checkIfFutureIsDoneWithoutBlocking();
    }
    return messageQueue.poll();
  }

  @Override
  public T getMessageSync() {
    if (messageQueue.peek() == null) {
      loadNext();
      fillQueueFromFutureBlocking();
    }
    return messageQueue.poll();
  }

  private void checkIfFutureIsDoneWithoutBlocking() {
    if ((future != null) && future.isDone()) {
      fillQueueFromFutureBlocking();
    }
  }

  private void fillQueueFromFutureBlocking() {
    try {
      for (T status : future.get()) {
        messageQueue.put(status);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    } finally {
      future = null;
      loading.set(Boolean.FALSE);
    }
  }

  @Override
  public Boolean hasNext() {
    return !messageQueue.isEmpty();
  }

  @Override
  public void loadNext() {
    if (messageQueue.isEmpty() && loading.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
      future = getMessageLoader().load();
    }
  }

  @Override
  public Boolean isLoading() {
    checkIfFutureIsDoneWithoutBlocking();
    return loading.get();
  }
}
