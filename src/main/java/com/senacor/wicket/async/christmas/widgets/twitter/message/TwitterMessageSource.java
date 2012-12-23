package com.senacor.wicket.async.christmas.widgets.twitter.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageLoader;
import com.senacor.wicket.async.christmas.widgets.api.message.MessageSource;

/**
 * A message factory linked to a Twitter account. This class MUST be used as a
 * session scoped object.
 * 
 * @author Jochen Mader
 */
@Service
@Qualifier("twitterMessageSource")
@Scope(value = "prototype")
public class TwitterMessageSource extends MessageSource<Tweet> {

  @Autowired
  @Qualifier("twitterMessageLoader")
  private IMessageLoader<Tweet> messageLoader;

  @Override
  public IMessageLoader<Tweet> getMessageLoader() {
    return messageLoader;
  }
}
