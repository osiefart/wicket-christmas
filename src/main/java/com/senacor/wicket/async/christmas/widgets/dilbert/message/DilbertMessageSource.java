package com.senacor.wicket.async.christmas.widgets.dilbert.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageLoader;
import com.senacor.wicket.async.christmas.widgets.api.message.MessageSource;
import com.sun.syndication.feed.synd.SyndFeed;

@Service
@Scope(value = "prototype")
public class DilbertMessageSource extends MessageSource<SyndFeed> {

  @Autowired
  @Qualifier("dilbertMessageLoader")
  private IMessageLoader<SyndFeed> messageLoader;

  @Override
  public IMessageLoader<SyndFeed> getMessageLoader() {
    return messageLoader;
  }
}
