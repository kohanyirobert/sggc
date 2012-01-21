package com.github.kohanyirobert.sggc;

import java.math.BigDecimal;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a south-west location node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface SouthWest extends Location {

  /**
   * Returns this south-west location's latitude.
   * 
   * @return this south-west location's latitude.
   */
  @Override
  BigDecimal latitude();

  /**
   * Returns this south-west location's longitude.
   * 
   * @return this south-west location's longitude.
   */
  @Override
  BigDecimal longitude();
}
