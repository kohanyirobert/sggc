package com.github.kohanyirobert.sggc;

import java.math.BigDecimal;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a north-east location node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface NorthEast extends Location {

  /**
   * Returns this north-east location's latitude.
   * 
   * @return this north-east location's latitude.
   */
  @Override
  BigDecimal latitude();

  /**
   * Returns this north-east location's longitude.
   * 
   * @return this north-east location's longitude.
   */
  @Override
  BigDecimal longitude();
}
