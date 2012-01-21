package com.github.kohanyirobert.sggc;

import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a location-type node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public enum LocationType {

  // @checkstyle:off JavadocVariable

  APPROXIMATE("APPROXIMATE"),
  GEOMETRIC_CENTER("GEOMETRIC_CENTER"),
  RANGE_INTERPOLATED("RANGE_INTERPOLATED"),
  ROOFTOP("ROOFTOP");

  // @checkstyle:on JavadocVariable

  private final String value;

  private LocationType(String value) {
    this.value = value;
  }

  /**
   * Returns this location-type's value.
   * 
   * @return this location-type's value
   */
  public String value() {
    return value;
  }

  /**
   * Returns the location-type corresponding to {@code value}.
   * 
   * @param value the value used to look-up a corresponding location-type
   * @return the location-type corresponding to {@code value}
   * @throws NullPointerException if {@code value} is null
   * @throws IllegalArgumentException if no location-type corresponding to
   * {@code value} was found
   */
  public static LocationType get(String value) {
    Preconditions.checkNotNull(value, "null value");
    for (LocationType locationType : values())
      if (locationType.value.equals(value))
        return locationType;
    throw new IllegalArgumentException(String.format("no location-type"
        + "corresponding to '%s' was found", value));
  }
}
