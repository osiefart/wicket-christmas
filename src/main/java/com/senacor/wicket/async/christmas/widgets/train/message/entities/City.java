package com.senacor.wicket.async.christmas.widgets.train.message.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jochen Mader
 */
@SuppressWarnings("serial")
public class City implements Serializable {
  private String name;
  private List<Integer> stations = new ArrayList<Integer>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Integer> getStations() {
    return stations;
  }

  public void setStations(List<Integer> stations) {
    this.stations = stations;
  }
}
