package com.senacor.wicket.async.christmas.widgets.train.message.source;

import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.senacor.wicket.async.christmas.core.runtime.delay.Delay;
import com.senacor.wicket.async.christmas.core.runtime.profile.Dev;
import com.senacor.wicket.async.christmas.widgets.train.message.IStringSource;

/**
 * @author Jochen Mader
 */
@Repository
@Scope("prototype")
@Dev
public class FileStringSource implements IStringSource {

  @Override
  @Delay(2500)
  public String getCitiesAndStations() {
    try {
      StringWriter writer = new StringWriter();
      IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("stations.json"), writer);
      return writer.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @Delay(2500)
  public String getTrains(DateTime dateTime) {
    try {
      StringWriter writer = new StringWriter();
      IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("trains.json"), writer);
      return writer.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
