package com.senacor.wicket.async.christmas.app;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.senacor.wicket.async.christmas.app.lazyloading.LazyLoadingPage;
import com.senacor.wicket.async.christmas.app.websocket.WebsocketPage;
import com.senacor.wicket.async.christmas.core.snow.LetItSnowComponentInstantiationListener;

public class ChristmasApplication extends WebApplication {

  @Override
  public Class<? extends WebPage> getHomePage() {
    return HomePage.class;
  }

  @Override
  public void init() {
    super.init();

    mountPage("lazyloading", LazyLoadingPage.class);
    mountPage("websockets", WebsocketPage.class);

    getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    getComponentInstantiationListeners().add(new LetItSnowComponentInstantiationListener());
  }

  public static ChristmasApplication get() {
    return (ChristmasApplication) WebApplication.get();
  }

}