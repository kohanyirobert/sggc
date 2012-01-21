package com.github.kohanyirobert.sggc;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import java.io.IOException;

/**
 * Utility class for creating and working with {@linkplain Geocoder geocoders}.
 */
public final class Geocoders {

  private static final Geocoder<Request, Response> INSTANCE = new DefaultGeocoder();

  private Geocoders() {}

  /**
   * Returns a new regular geocoder.
   * 
   * @return a new regular geocoder
   */
  public static Geocoder<Request, Response> geocoder() {
    return INSTANCE;
  }

  /**
   * Returns a new transformer geocoder that wraps a regular geocoder and
   * delegates requests/responses to/from it by transforming them with
   * {@code packer}/{@code unpacker} respectively.
   * 
   * @param packer the function used to pack requests
   * @param unpacker the function used to unpack responses
   * @param <K> the returned transformer geocoder's request type
   * @param <V> the returned transformer geocoder's response type
   * @return a new transformer geocoder
   * @throws NullPointerException if {@code packer} or {@code unpacker} are null
   */
  public static <K, V> Geocoder<K, V> geocoder(
      final Function<K, Request> packer,
      final Function<Response, V> unpacker) {
    return geocoder(geocoder(), packer, unpacker);
  }

  /**
   * Returns a new transformer geocoder that wraps {@code geocoder} and
   * delegates requests/responses to/from it by transforming them with
   * {@code packer}/{@code unpacker} respectively.
   * 
   * @param geocoder the geocoder that will be used by the returned transformer
   * geocoder
   * @param packer the function used to pack requests
   * @param unpacker the function used to unpack responses
   * @param <K> the returned transformer geocoder's request type
   * @param <V> the returned transformer geocoder's response type
   * @param <P> {@code geocoder}'s request type
   * @param <Q> {@code geocoder}'s response type
   * @return a new transformer geocoder
   * @throws NullPointerException if {@code geocoder}, {@code packer} or
   * {@code unpacker} are null
   */
  public static <K, V, P, Q> Geocoder<K, V> geocoder(
      final Geocoder<P, Q> geocoder,
      final Function<K, P> packer,
      final Function<Q, V> unpacker) {
    Preconditions.checkNotNull(geocoder, "null geocoder");
    Preconditions.checkNotNull(packer, "null package");
    Preconditions.checkNotNull(unpacker, "null unpacker");
    return new Geocoder<K, V>() {

      @Override
      public V geocode(K request) throws IOException {
        Preconditions.checkNotNull(request, "null request");
        return unpacker.apply(geocoder.geocode(packer.apply(request)));
      }
    };
  }
}
