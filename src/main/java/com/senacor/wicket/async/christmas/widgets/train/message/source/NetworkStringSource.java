package com.senacor.wicket.async.christmas.widgets.train.message.source;

import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.senacor.wicket.async.christmas.core.runtime.profile.Prod;
import com.senacor.wicket.async.christmas.widgets.train.message.IStringSource;

/**
 * @author Jochen Mader
 */
@Repository
@Scope("prototype")
@Prod
public class NetworkStringSource implements IStringSource {

  @Override
  public String getCitiesAndStations() {
    DefaultHttpClient httpclient = new DefaultHttpClient();
    HttpGet httpGet = new HttpGet("http://zugmonitor.sueddeutsche.de/api/stations");
    try {
      HttpResponse response1 = httpclient.execute(httpGet);
      HttpEntity entity1 = response1.getEntity();
      StringWriter writer = new StringWriter();
      IOUtils.copy(entity1.getContent(), writer, null);
      return writer.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      httpGet.releaseConnection();
    }
  }

  @Override
  public String getTrains(DateTime dateTime) {
    DefaultHttpClient httpclient = new DefaultHttpClient();
    HttpGet httpGet = new HttpGet("http://zugmonitor.sueddeutsche.de/api/trains/" + dateTime.getYear() + "-"
        + StringUtils.leftPad(Integer.toString(dateTime.getMonthOfYear()), 2, "0") + "-"
        + StringUtils.leftPad(Integer.toString(dateTime.getDayOfMonth()), 2, "0"));
    try {
      HttpResponse response1 = httpclient.execute(httpGet);
      HttpEntity entity1 = response1.getEntity();
      StringWriter writer = new StringWriter();
      IOUtils.copy(entity1.getContent(), writer, null);
      return writer.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      httpGet.releaseConnection();
    }
  }
}
