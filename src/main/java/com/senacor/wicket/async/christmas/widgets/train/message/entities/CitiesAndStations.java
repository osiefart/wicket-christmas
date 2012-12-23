package com.senacor.wicket.async.christmas.widgets.train.message.entities;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Jochen Mader
 */
@SuppressWarnings("serial")
public class CitiesAndStations implements Serializable {
  public Map<String, Station> idToStationMap;
  public Map<String, City> idToCityMap;

  public CitiesAndStations(Map<String, Station> idToStationMap, Map<String, City> idToCityMap) {
    this.idToStationMap = idToStationMap;
    this.idToCityMap = idToCityMap;
  }
}
