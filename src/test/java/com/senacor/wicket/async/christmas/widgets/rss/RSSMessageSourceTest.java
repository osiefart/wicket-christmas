package com.senacor.wicket.async.christmas.widgets.rss;
//package com.senacor.wicket.async.christmas.data.rss;
//
//import com.senacor.wicket.async.christmas.data.dilbert.message.source.DilbertSyndFeedSource;
//import junit.framework.Assert;
//
//import org.junit.Ignore;
//import org.junit.Test;
//
//import com.senacor.wicket.async.christmas.data.dilbert.message.DilbertMessageSource;
//import com.sun.syndication.feed.synd.SyndFeed;
//
////Ignored because this test needs a network connection ...
//@Ignore
//public class RSSMessageSourceTest {
//
//  @Test
//  public void testDilbertGetMessage() {
//    DilbertSyndFeedSource rssMessageSource = new DilbertSyndFeedSource();
//    SyndFeed syndFeed = rssMessageSource.loadSyndFeed();
//    Assert.assertNotNull(syndFeed.getTitle());
//    System.out.println(syndFeed);
//  }
//
//  @Test
//  public void testSantaClausGetMessage() {
//    DilbertSyndFeedSource rssMessageSource = new DilbertSyndFeedSource("http://www.northpoletimes.com/RSS/");
//    SyndFeed syndFeed = rssMessageSource.loadSyndFeed();
//    Assert.assertNotNull(syndFeed.getTitle());
//    System.out.println(syndFeed);
//  }
//
//  @Test
//  public void testDeutscheBahnGetMessage() {
//    DilbertSyndFeedSource rssMessageSource = new DilbertSyndFeedSource("http://www.bahn-o-meter.de/feed/");
//    SyndFeed syndFeed = rssMessageSource.loadSyndFeed();
//    Assert.assertNotNull(syndFeed.getTitle());
//    System.out.println(syndFeed);
//
//    rssMessageSource = new DilbertSyndFeedSource("http://www.bahn-o-meter.de/comments/feed/");
//    syndFeed = rssMessageSource.loadSyndFeed();
//    Assert.assertNotNull(syndFeed.getTitle());
//    System.out.println(syndFeed);
//  }
//
// }
