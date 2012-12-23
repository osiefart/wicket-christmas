package com.senacor.wicket.async.christmas.widgets.dilbert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageSource;
import com.senacor.wicket.async.christmas.widgets.api.model.AbstractAsyncModel;
import com.senacor.wicket.async.christmas.widgets.dilbert.message.DilbertMessageSource;
import com.sun.syndication.feed.synd.SyndFeed;

@Configurable
public class DilbertAsyncModel extends AbstractAsyncModel<SyndFeed> {

  @Autowired
  private transient DilbertMessageSource messageSource;

  @Override
  public IMessageSource<SyndFeed> getMessageSource() {
    return messageSource;
  }

}