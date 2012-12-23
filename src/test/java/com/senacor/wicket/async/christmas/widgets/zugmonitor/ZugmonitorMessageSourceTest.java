package com.senacor.wicket.async.christmas.widgets.zugmonitor;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.senacor.wicket.async.christmas.widgets.train.message.ZugmonitorMessageLoader;
import com.senacor.wicket.async.christmas.widgets.train.message.entities.CitiesAndStations;
import com.senacor.wicket.async.christmas.widgets.train.message.entities.Train;
import com.senacor.wicket.async.christmas.widgets.train.message.source.FileStringSource;

/**
 * @author Jochen Mader
 */
public class ZugmonitorMessageSourceTest {
  @Test
  public void testParseStationsAndCities() throws Exception {
    StringWriter writer = new StringWriter();
    IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("stations.json"), writer);
    CitiesAndStations cs = ZugmonitorMessageLoader.parseStationsAndCities(writer.toString());
    assertEquals("Brno", cs.idToCityMap.get("3").getName());
    assertEquals("Aalen", cs.idToStationMap.get("3").getName());
  }

  @Test
  public void testParseTrains() throws Exception {
    StringWriter writer = new StringWriter();
    IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("trains.json"), writer);
    Map<String, Train> zuege = ZugmonitorMessageLoader.parseTrains(writer.toString());
    assertEquals("E", zuege.get("176128").getStatus());
  }

  @Test
  public void testGetMessage() throws Exception {
    ZugmonitorMessageLoader source = new ZugmonitorMessageLoader();
    source.setSource(new FileStringSource());
    assertEquals("DELAY ICE 576 - 5 Min", source.load().get().get(0));
  }
}
