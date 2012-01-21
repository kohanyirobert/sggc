package com.github.kohanyirobert.sggc;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a bounds node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface Bounds {

  /**
   * Returns this bounds' south-west location.
   * 
   * @return this bounds' south-west location
   */
  SouthWest southWest();

  /**
   * Returns this bounds' north-east location.
   * 
   * @return this bounds' north-east location
   */
  NorthEast northEast();
}
