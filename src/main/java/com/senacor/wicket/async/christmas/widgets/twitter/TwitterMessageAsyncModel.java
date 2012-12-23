package com.senacor.wicket.async.christmas.widgets.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.social.twitter.api.Tweet;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageSource;
import com.senacor.wicket.async.christmas.widgets.api.model.AbstractAsyncModel;
import com.senacor.wicket.async.christmas.widgets.twitter.message.TwitterMessageSource;

@Configurable
public class TwitterMessageAsyncModel extends AbstractAsyncModel<Tweet> {

  @Autowired
  private transient TwitterMessageSource messageSource;

  @Override
  public IMessageSource<Tweet> getMessageSource() {
    return messageSource;
  }

}