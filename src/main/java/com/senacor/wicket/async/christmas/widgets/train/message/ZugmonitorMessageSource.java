package com.senacor.wicket.async.christmas.widgets.train.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageLoader;
import com.senacor.wicket.async.christmas.widgets.api.message.MessageSource;

/**
 * @author Jochen Mader
 */
@Service
@Scope("prototype")
public class ZugmonitorMessageSource extends MessageSource<String> {

  @Autowired
  @Qualifier("zugmonitorMessageLoader")
  private IMessageLoader<String> messageLoader;

  @Override
  public IMessageLoader<String> getMessageLoader() {
    return messageLoader;
  }
}
