package com.senacor.wicket.async.christmas.widgets.dilbert.message;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.senacor.wicket.async.christmas.core.runtime.delay.Delay;
import com.senacor.wicket.async.christmas.core.runtime.profile.Dev;
import com.senacor.wicket.async.christmas.widgets.api.message.IMessageLoader;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
@Service("dilbertMessageLoader")
@Dev
public class DilbertMessageLoaderMock implements IMessageLoader<SyndFeed> {

  @SuppressWarnings("unchecked")
  @Override
  @Delay(5000)
  public Future<List<SyndFeed>> load() {
    SyndFeed syndFeed = new SyndFeedImpl();
    syndFeed.getEntries().add(createSyndEntry("20121213.gif"));
    syndFeed.getEntries().add(createSyndEntry("20121212.gif"));
    return new AsyncResult<List<SyndFeed>>(syndFeed.getEntries());
  }

  private SyndEntryImpl createSyndEntry(String imageName) {
    SyndEntryImpl syndEntry = new SyndEntryImpl();
    SyndContentImpl syndContent = new SyndContentImpl();
    syndContent.setValue("<img src='/images/mock/" + imageName + "' border='0' ></img>");
    syndEntry.setDescription(syndContent);
    return syndEntry;
  }

}