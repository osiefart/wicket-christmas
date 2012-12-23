package com.senacor.wicket.async.christmas.app;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.senacor.wicket.async.christmas.app.badheart.BadHeartPage;
import com.senacor.wicket.async.christmas.app.goodheart.HeartBeatPage;
import com.senacor.wicket.async.christmas.app.lazyloading.LazyLoadingPage;
import com.senacor.wicket.async.christmas.app.websocket.WebsocketPage;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;

  public HomePage(final PageParameters parameters) {
    super(parameters);
    add(new BookmarkablePageLink<Void>("lazyLoading", LazyLoadingPage.class));
    add(new BookmarkablePageLink<Void>("websocket", WebsocketPage.class));
    add(new BookmarkablePageLink<Void>("badHeart", BadHeartPage.class));
    add(new BookmarkablePageLink<Void>("heartBeat", HeartBeatPage.class));
  }
}
