package com.senacor.wicket.async.christmas.widgets.twitter.message;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import com.senacor.wicket.async.christmas.core.runtime.profile.Prod;
import com.senacor.wicket.async.christmas.widgets.api.message.IMessageLoader;

/**
 * A message factory linked to a Twitter account. This class MUST be used as a
 * session scoped object.
 * 
 * @author Jochen Mader
 */
@Service("twitterMessageLoader")
@Scope(value = "prototype")
@Prod
public class TwitterMessageLoader implements IMessageLoader<Tweet> {

  @Autowired
  private transient TwitterTemplate twitterTemplate;

  public TwitterMessageLoader() {
  }

  @Override
  @Async
  public Future<List<Tweet>> load() {
    return new AsyncResult<List<Tweet>>(twitterTemplate.searchOperations().search("xmas").getTweets());
  }

}
