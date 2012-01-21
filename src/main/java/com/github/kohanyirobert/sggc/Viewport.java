package com.github.kohanyirobert.sggc;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a viewport node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface Viewport extends Bounds {

  /**
   * Returns this viewport's south-west location.
   * 
   * @return this viewport's south-west location
   */
  @Override
  SouthWest southWest();

  /**
   * Returns this viewport's north-east location.
   * 
   * @return this viewport's north-east location
   */
  @Override
  NorthEast northEast();
}
