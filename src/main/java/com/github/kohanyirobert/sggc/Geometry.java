package com.github.kohanyirobert.sggc;

import javax.annotation.CheckForNull;
import javax.annotation.concurrent.Immutable;

/**
 * Representation of a geometry node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface Geometry {

  /**
   * Returns this geometry's location.
   * 
   * @return this geometry's location
   */
  Location location();

  /**
   * Returns this geometry's location type.
   * 
   * @return this geometry's location type
   */
  LocationType locationType();

  /**
   * Returns this geometry's viewport.
   * 
   * @return this geometry's viewport
   */
  Viewport viewport();

  /**
   * Returns this geometry's bounds.
   * 
   * @return this geometry's bounds
   */
  @CheckForNull
  Bounds bounds();
}
