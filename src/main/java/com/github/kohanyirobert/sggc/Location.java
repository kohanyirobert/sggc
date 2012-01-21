package com.github.kohanyirobert.sggc;

import java.math.BigDecimal;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a location node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface Location {

  /**
   * Returns this location's latitude.
   * 
   * @return this location's latitude.
   */
  BigDecimal latitude();

  /**
   * Returns this location's longitude.
   * 
   * @return this location's longitude.
   */
  BigDecimal longitude();
}
