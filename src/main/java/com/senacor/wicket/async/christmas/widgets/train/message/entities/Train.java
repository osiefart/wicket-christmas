package com.senacor.wicket.async.christmas.widgets.train.message.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Jochen Mader
 */
@SuppressWarnings("serial")
public class Train implements Serializable {
  private String status;
  private Date started;
  private Date nextrun;
  private Date finished;
  private List<Integer> date;
  @JsonProperty("train_nr")
  private String trainNr;
  private List<WayPoint> stations;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getStarted() {
    return started;
  }

  public void setStarted(Date started) {
    this.started = started;
  }

  public Date getNextrun() {
    return nextrun;
  }

  public void setNextrun(Date nextrun) {
    this.nextrun = nextrun;
  }

  public Date getFinished() {
    return finished;
  }

  public void setFinished(Date finished) {
    this.finished = finished;
  }

  public List<Integer> getDate() {
    return date;
  }

  public void setDate(List<Integer> date) {
    this.date = date;
  }

  public String getTrainNr() {
    return trainNr;
  }

  public void setTrainNr(String trainNr) {
    this.trainNr = trainNr;
  }

  public List<WayPoint> getStations() {
    return stations;
  }

  public void setStations(List<WayPoint> stations) {
    this.stations = stations;
  }
}
