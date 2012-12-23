package com.senacor.wicket.async.christmas.widgets.twitter.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import com.senacor.wicket.async.christmas.core.runtime.delay.Delay;
import com.senacor.wicket.async.christmas.core.runtime.profile.Dev;
import com.senacor.wicket.async.christmas.widgets.api.message.IMessageLoader;

/**
 * A message factory linked to a Twitter account. This class MUST be used as a
 * session scoped object.
 * 
 * @author Jochen Mader
 */
@Service("twitterMessageLoader")
@Scope(value = "prototype")
@Dev
public class TwitterMessageLoaderMock implements IMessageLoader<Tweet> {

  @Override
  @Async
  @Delay(5000)
  public Future<List<Tweet>> load() {
    List<Tweet> ret = new ArrayList<Tweet>();
    ret.add(createTweet("Santa Claus",
        "I've been so busy this year that I forgot to set up the geolocation tracker on my sleigh! NORAD to the rescue -- I'll be retweeting"));
    ret.add(createTweet("Reindeer", "@Santa just iMessaged me a photo of this ornament he saw tonight. He said it reminded him of me. How cool is that?!"));
    ret.add(createTweet("Santa Claus", "One of those work days where I'm too busy to tweet!!"));
    ret.add(createTweet("Reindeer", "One time we hit ludicrous speed on a training run; it's the only time I've gone to plaid."));
    ret.add(createTweet("Santa Claus", "Lots of requests for people as gifts this holiday season . . . sorry, all, but I can't bring you a person as a present"));
    return new AsyncResult<List<Tweet>>(ret);
  }

  private Tweet createTweet(String user, String text) {
    return new Tweet(0, text, new Date(), user, null, null, 0, null, null);
  }

}
