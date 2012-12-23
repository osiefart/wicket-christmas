package com.senacor.wicket.async.christmas.core.snow;

import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * {@link PackageResourceReference} for a Javascript, that renders snow on the
 * page
 * 
 * @author Olaf Siefart, Senacor Technologies AG
 */
public class LetItSnowResourceReference extends PackageResourceReference {

  private static LetItSnowResourceReference instance = new LetItSnowResourceReference();

  /**
   * Konstruktor
   */
  private LetItSnowResourceReference() {
    super(LetItSnowResourceReference.class, "let_it_snow.js");
  }

  public static LetItSnowResourceReference get() {
    return instance;
  }

}