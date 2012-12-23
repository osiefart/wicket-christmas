package com.senacor.wicket.async.christmas.widgets.train.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.senacor.wicket.async.christmas.widgets.api.message.IMessageLoader;
import com.senacor.wicket.async.christmas.widgets.train.message.entities.CitiesAndStations;
import com.senacor.wicket.async.christmas.widgets.train.message.entities.City;
import com.senacor.wicket.async.christmas.widgets.train.message.entities.Station;
import com.senacor.wicket.async.christmas.widgets.train.message.entities.Train;
import com.senacor.wicket.async.christmas.widgets.train.message.entities.WayPoint;

/**
 * @author Jochen Mader
 */
@Service("zugmonitorMessageLoader")
@Scope("prototype")
public class ZugmonitorMessageLoader implements IMessageLoader<String> {

  @Autowired
  private IStringSource source;

  public IStringSource getSource() {
    return source;
  }

  public void setSource(IStringSource source) {
    this.source = source;
  }

  @Override
  @Async
  public Future<List<String>> load() {
    List<String> ret = new ArrayList<String>();
    for (Map.Entry<String, Train> entry : parseTrains(source.getTrains(DateTime.now())).entrySet()) {
      WayPoint wayPoint = entry.getValue().getStations().get(entry.getValue().getStations().size() - 1);
      if ((wayPoint.getDelay() != null) && (wayPoint.getDelay() > 0)) {
        ret.add("DELAY " + entry.getValue().getTrainNr() + " - " + wayPoint.getDelay() + " Min");
      }
    }
    return new AsyncResult<List<String>>(ret);
  }

  public static CitiesAndStations parseStationsAndCities(String theString) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      Map<String, JsonNode> jsonNodes = mapper.readValue(theString, new TypeReference<HashMap<String, JsonNode>>() {
      });
      // GRRRRRRRRR WHY THE F DO THEY HAVE TO MIX OBJECTS IN A MAP????????
      Map<String, City> cityMap = mapper.readValue(jsonNodes.remove("cities"), new TypeReference<HashMap<String, City>>() {
      });
      Map<String, Station> stationMap = new HashMap<String, Station>();
      for (Map.Entry<String, JsonNode> entry : jsonNodes.entrySet()) {
        stationMap.put(entry.getKey(), mapper.readValue(entry.getValue(), Station.class));
      }
      return new CitiesAndStations(Collections.unmodifiableMap(stationMap), Collections.unmodifiableMap(cityMap));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static Map<String, Train> parseTrains(String theString) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(theString, new TypeReference<HashMap<String, Train>>() {
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
