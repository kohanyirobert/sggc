package com.github.kohanyirobert.sggc;

import java.math.BigDecimal;
import java.net.URL;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of an an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> request.
 */
@Immutable
public interface Request {

  /**
   * Returns this request's protocol (defaults to {@linkplain Protocol#HTTP
   * HTTP}).
   * 
   * @return this request's protocol.
   */
  Protocol protocol();

  /**
   * Returns this request's output (defaults to {@linkplain Output#XML
   * XML}).
   * 
   * @return this request's output.
   */
  Output output();

  /**
   * Returns this request's address (defaults to null if
   * {@linkplain #location() location} is non-null).
   * 
   * @return this request's address
   */
  String address();

  /**
   * Returns this request's location (defaults to null if
   * {@linkplain #address() address} is non-null).
   * 
   * @return this request's location
   */
  Location location();

  /**
   * Returns this request's bounds (defaults to null).
   * 
   * @return this request's bounds
   */
  Bounds bounds();

  /**
   * Returns this request's language (defaults to null).
   * 
   * @return this request's language
   */
  Language language();

  /**
   * Returns this request's region (defaults to null).
   * 
   * @return this request's region
   */
  Region region();

  /**
   * Returns this request's sensor (defaults to false).
   * 
   * @return this request's sensor
   */
  boolean sensor();

  /**
   * Returns this request's client (defaults to null).
   * 
   * @return this request's client
   */
  String client();

  /**
   * Returns this request's signature (defaults to null).
   * 
   * @return this request's signature
   */
  String signature();

  /**
   * Returns this request's url.
   * 
   * @return this request's url
   */
  URL url();

  /**
   * Builder for {@linkplain Request geocoding requests}.
   */
  interface Builder {

    /**
     * Sets the protocol of the request being built.
     * 
     * @param protocol the protocol
     * @return this builder
     * @throws NullPointerException if {@code protocol} is null
     */
    Builder protocol(Protocol protocol);

    /**
     * Sets the output of the request being built.
     * 
     * @param output the output
     * @return this builder
     * @throws NullPointerException if {@code output} is null
     */
    Builder output(Output output);

    /**
     * Sets the address of the request being built.
     * 
     * @param address the address
     * @return this builder
     * @throws NullPointerException if {@code address} is null
     * @throws IllegalStateException if any of this builder's
     * {@linkplain #location location} methods were called before with valid
     * parameters
     */
    Builder address(String address);

    /**
     * Sets the latitude and longitude of the location of request being built.
     * 
     * @param latitude the latitude
     * @param longitude the longitude
     * @return this builder
     * @throws NumberFormatException if {@code latitude} or {@code longitude}
     * don't represent valid decimal numbers
     * @throws IllegalArgumentException if {@code latitude} is not between -90.0
     * and 90.0 (inclusive) or {@code longitude} is not between -180.0 and 180.0
     * (inclusive)
     * @throws IllegalStateException if this builder's {@linkplain #address
     * address} method were called before with valid parameters
     */
    Builder location(String latitude, String longitude);

    /**
     * Sets the latitude and longitude of the location of request being built.
     * 
     * @param latitude the latitude
     * @param longitude the longitude
     * @return this builder
     * @throws IllegalArgumentException if {@code latitude} is not between -90.0
     * and 90.0 (inclusive) or {@code longitude} is not between -180.0 and 180.0
     * (inclusive)
     * @throws IllegalStateException if this builder's {@linkplain #address
     * address} method were called before with valid parameters
     */
    Builder location(double latitude, double longitude);

    /**
     * Sets the latitude and longitude of the location of request being built.
     * 
     * @param latitude the latitude
     * @param longitude the longitude
     * @return this builder
     * @throws NullPointerException if {@code latitude} or {@code longitude} are
     * null
     * @throws IllegalArgumentException if {@code latitude} is not between -90.0
     * and 90.0 (inclusive) or {@code longitude} is not between -180.0 and 180.0
     * (inclusive)
     * @throws IllegalStateException if this builder's {@linkplain #address
     * address} method were called before with valid parameters
     */
    Builder location(BigDecimal latitude, BigDecimal longitude);

    /**
     * Sets the location of the request being built.
     * 
     * @param location the location
     * @return this builder
     * @throws NullPointerException if {@code location} is null
     * @throws IllegalStateException if this builder's {@linkplain #address
     * address} method were called before with valid parameters
     */
    Builder location(Location location);

    /**
     * Sets the latitude and longitude values of the south-west and north-east
     * location of the bounds of the request being built.
     * 
     * @param southWestLatitude the the south-west latitude
     * @param southWestLongitude the the south-west longitude
     * @param northEastLatitude the north-east latitude
     * @param northEastLongitude the north-east longitude
     * @return this builder
     * @throws NumberFormatException if any of {@code southWestLatitude},
     * {@code southWestLongitude}, {@code northEastLatitude} or
     * {@code northEastLongitude} don't represent valid decimal numbers
     * @throws IllegalArgumentException if {@code southWestLatitude} and
     * {@code northEastLatitude} are not between -90.0 and 90.0 (inclusive) or
     * {@code southWestLongitude} and {@code northEastLongitude} are not between
     * -180.0 and 180.0 (inclusive)
     */
    Builder bounds(String southWestLatitude, String southWestLongitude,
        String northEastLatitude, String northEastLongitude);

    /**
     * Sets the latitude and longitude values of the south-west and north-east
     * location of the bounds of the request being built.
     * 
     * @param southWestLatitude the the south-west latitude
     * @param southWestLongitude the the south-west longitude
     * @param northEastLatitude the north-east latitude
     * @param northEastLongitude the north-east longitude
     * @return this builder
     * @throws IllegalArgumentException if {@code southWestLatitude} and
     * {@code northEastLatitude} are not between -90.0 and 90.0 (inclusive) or
     * {@code southWestLongitude} and {@code northEastLongitude} are not between
     * -180.0 and 180.0 (inclusive)
     */
    Builder bounds(double southWestLatitude, double southWestLongitude,
        double northEastLatitude, double northEastLongitude);

    /**
     * Sets the latitude and longitude values of the south-west and north-east
     * location of the bounds of the request being built.
     * 
     * @param southWestLatitude the the south-west latitude
     * @param southWestLongitude the the south-west longitude
     * @param northEastLatitude the north-east latitude
     * @param northEastLongitude the north-east longitude
     * @return this builder
     * @throws NullPointerException if any of {@code southWestLatitude},
     * {@code southWestLongitude}, {@code northEastLatitude} or
     * {@code northEastLongitude} are null
     * @throws IllegalArgumentException if {@code southWestLatitude} and
     * {@code northEastLatitude} are not between -90.0 and 90.0 (inclusive) or
     * {@code southWestLongitude} and {@code northEastLongitude} are not between
     * -180.0 and 180.0 (inclusive)
     */
    Builder bounds(BigDecimal southWestLatitude, BigDecimal southWestLongitude,
        BigDecimal northEastLatitude, BigDecimal northEastLongitude);

    /**
     * Sets the south-west and north-east locations of the bounds of the request
     * built.
     * 
     * @param southWest the south-west location
     * @param northEast the north-east location
     * @return this builder
     * @throws NullPointerException if any of {@code southWest} or
     * {@code northEast} are null
     */
    Builder bounds(SouthWest southWest, NorthEast northEast);

    /**
     * Sets the bounds of the request being built.
     * 
     * @param bounds the bounds
     * @return this builder
     * @throws NullPointerException if {@code bounds} is null
     */
    Builder bounds(Bounds bounds);

    /**
     * Sets the region of the request being built.
     * 
     * @param region the region
     * @return this builder
     * @throws NullPointerException if {@code region} is null
     */
    Builder region(Region region);

    /**
     * Sets the language of the request being built.
     * 
     * @param language the language
     * @return this builder
     * @throws NullPointerException if {@code language} is null
     */
    Builder language(Language language);

    /**
     * Sets the sensor of the request being built.
     * 
     * @param sensor the sensor
     * @return this builder
     */
    Builder sensor(boolean sensor);

    /**
     * Sets the client of the request being built.
     * 
     * @param client the client
     * @return this builder
     * @throws NullPointerException if {@code client} is null
     */
    Builder client(String client);

    /**
     * Sets the key that will be used to create the signature of the request
     * being built.
     * 
     * @param key the key
     * @return this builder
     * @throws NullPointerException if {@code key} is null
     */
    Builder signature(String key);

    /**
     * Returns a new request based on this builder's contents.
     * 
     * @return a new request based on this builder's contents
     */
    Request build();
  }
}
