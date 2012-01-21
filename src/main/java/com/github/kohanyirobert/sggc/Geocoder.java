package com.github.kohanyirobert.sggc;

import java.io.IOException;

import javax.annotation.concurrent.Immutable;

/**
 * Geocoder client for sending requests and receiving responses from the <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/">Google
 * Geocoding API</a>.
 * <p>
 * <b>Note:</b> use the {@linkplain Geocoders geocoders} utility class to create
 * geocoder instances.
 * </p>
 * 
 * @param <K> the request type
 * @param <V> the response type
 */
@Immutable
public interface Geocoder<K, V> {

  /**
   * Returns the response corresponding to {@code request}.
   * 
   * @param request the request used to look up the response
   * @return the response corresponding to {@code request}
   * @throws NullPointerException if {@code request} is null
   * @throws IOException if a network error occurs while retrieving the response
   * over the network
   */
  V geocode(K request) throws IOException;
}
