package com.senacor.wicket.async.christmas.widgets.train.message.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Station implements Serializable {
  private int id;
  private float lat;
  private float lon;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getLat() {
    return lat;
  }

  public void setLat(float lat) {
    this.lat = lat;
  }

  public float getLon() {
    return lon;
  }

  public void setLon(float lon) {
    this.lon = lon;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
