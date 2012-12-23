package com.senacor.wicket.async.christmas.widgets.dilbert.message;

import java.net.URL;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.senacor.wicket.async.christmas.core.runtime.profile.Prod;
import com.senacor.wicket.async.christmas.widgets.api.message.IMessageLoader;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * @author Jochen Mader
 */
@Service("dilbertMessageLoader")
@Prod
public class DilbertMessageLoader implements IMessageLoader<SyndFeed> {

  private static final String feedUrl = "http://feeds.dilbert.com/DilbertDailyStrip?format=xml";

  @SuppressWarnings("unchecked")
  @Override
  public Future<List<SyndFeed>> load() {
    final SyndFeedInput input = new SyndFeedInput();
    try {
      final SyndFeed feed = input.build(new XmlReader(new URL(feedUrl)));
      return new AsyncResult<List<SyndFeed>>(feed.getEntries());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}