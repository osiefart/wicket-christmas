package com.senacor.wicket.async.christmas.widgets.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageSource;
import com.senacor.wicket.async.christmas.widgets.api.model.AbstractAsyncModel;
import com.senacor.wicket.async.christmas.widgets.train.message.ZugmonitorMessageSource;

@Configurable
public class TrainMessageAsyncModel extends AbstractAsyncModel<String> {

  @Autowired
  private transient ZugmonitorMessageSource messageSource;

  @Override
  public IMessageSource<String> getMessageSource() {
    return messageSource;
  }
}