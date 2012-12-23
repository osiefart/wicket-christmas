package com.senacor.wicket.async.christmas.widgets.train.message.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Jochen Mader
 */
@SuppressWarnings("serial")
public class WayPoint implements Serializable {
  private Boolean scraped;
  @JsonProperty("track_change")
  private Boolean trackChange;
  private Integer delay;
  @JsonProperty("delay_cause")
  private String delayCause;
  private Long arrival;
  private Long departure;
  @JsonProperty("station_id")
  private Long stationId;

  public Boolean getTrackChange() {
    return trackChange;
  }

  public void setTrackChange(Boolean trackChange) {
    this.trackChange = trackChange;
  }

  public Integer getDelay() {
    return delay;
  }

  public void setDelay(Integer delay) {
    this.delay = delay;
  }

  public String getDelayCause() {
    return delayCause;
  }

  public void setDelayCause(String delayCause) {
    this.delayCause = delayCause;
  }

  public Boolean getScraped() {
    return scraped;
  }

  public void setScraped(Boolean scraped) {
    this.scraped = scraped;
  }

  public Long getArrival() {
    return arrival;
  }

  public void setArrival(Long arrival) {
    this.arrival = arrival;
  }

  public Long getDeparture() {
    return departure;
  }

  public void setDeparture(Long departure) {
    this.departure = departure;
  }

  public Long getStationId() {
    return stationId;
  }

  public void setStationId(Long stationId) {
    this.stationId = stationId;
  }
}
