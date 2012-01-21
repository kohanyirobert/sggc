package com.github.kohanyirobert.sggc;

import java.util.List;

import javax.annotation.CheckForNull;
import javax.annotation.concurrent.Immutable;

/**
 * Representation of a result node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface Result {

  /**
   * Returns this result's types as an unmodifiable list.
   * 
   * @return this result's types as an unmodifiable list
   */
  List<Type> types();

  /**
   * Returns this result's address components as an unmodifiable list.
   * 
   * @return this result's address components as an unmodifiable list
   */
  List<AddressComponent> addressComponents();

  /**
   * Returns this result's formatted address.
   * 
   * @return this result's formatted address
   */
  String formattedAddress();

  /**
   * Returns this result's geometry.
   * 
   * @return this result's geometry
   */
  Geometry geometry();

  /**
   * Returns this result's partial match.
   * 
   * @return this result's partial match
   */
  @CheckForNull
  Boolean partialMatch();
}
