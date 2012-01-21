package com.github.kohanyirobert.sggc;

import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a type node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public enum Type {

  // @checkstyle:off JavadocVariable

  ADMINISTRATIVE_AREA_LEVEL_1("administrative_area_level_1"),
  ADMINISTRATIVE_AREA_LEVEL_2("administrative_area_level_2"),
  ADMINISTRATIVE_AREA_LEVEL_3("administrative_area_level_3"),
  AIRPORT("airport"),
  BUS_STATION("bus_station"),
  CAMPGROUND("campground"),
  COLLOQUIAL_AREA("colloquial_area"),
  COUNTRY("country"),
  ESTABLISHMENT("establishment"),
  FLOOR("floor"),
  HEALTH("health"),
  HOSPITAL("hospital"),
  INTERSECTION("intersection"),
  LIBRARY("library"),
  LOCALITY("locality"),
  LODGING("lodging"),
  NATURAL_FEATURE("natural_feature"),
  NEIGHBORHOOD("neighborhood"),
  PARK("park"),
  PLACE_OF_WORSHIP("place_of_worship"),
  POINT_OF_INTEREST("point_of_interest"),
  POLICE("police"),
  POLITICAL("political"),
  POST_BOX("post_box"),
  POSTAL_CODE("postal_code"),
  POSTAL_CODE_PREFIX("postal_code_prefix"),
  PREMISE("premise"),
  ROOM("room"),
  ROUTE("route"),
  STREET_ADDRESS("street_address"),
  STREET_NUMBER("street_number"),
  SUBLOCALITY("sublocality"),
  SUBPREMISE("subpremise"),
  SUBWAY_STATION("subway_station"),
  TRAIN_STATION("train_station"),
  TRANSIT_STATION("transit_station"),
  UNIVERSITY("university");

  // @checkstyle:on JavadocVariable

  private final String value;

  private Type(String value) {
    this.value = value;
  }

  /**
   * Returns this type's value.
   * 
   * @return this type's value
   */
  public String value() {
    return value;
  }

  /**
   * Returns the type corresponding to {@code value}.
   * 
   * @param value the value used to look-up a corresponding type
   * @return the type corresponding to {@code value}
   * @throws NullPointerException if {@code value} is null
   * @throws IllegalArgumentException if no type corresponding to {@code value}
   * was found
   */
  public static Type get(String value) {
    Preconditions.checkNotNull(value, "null value");
    for (Type type : values())
      if (type.value.equals(value))
        return type;
    throw new IllegalArgumentException(String.format("no type"
        + "corresponding to '%s' was found", value));
  }
}
