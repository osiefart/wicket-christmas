package com.senacor.wicket.async.christmas.widgets.train.message;

import org.joda.time.DateTime;

/**
 * @author Jochen Mader
 */
public interface IStringSource {
  String getCitiesAndStations();

  String getTrains(DateTime date);
}
