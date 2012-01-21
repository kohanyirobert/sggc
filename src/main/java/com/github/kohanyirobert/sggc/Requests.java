package com.github.kohanyirobert.sggc;

import java.net.URL;

/**
 * Utility class for working with {@linkplain Request requests}.
 */
public final class Requests {

  private Requests() {}

  /**
   * Returns a new request using {@code address}.
   * 
   * @param address the address of the request
   * @return a new request using {@code address}
   * @throws NullPointerException if {@code address} is null
   */
  public static Request request(String address) {
    return builder().address(address).build();
  }

  /**
   * Returns a new request builder.
   * 
   * @return a new request builder
   */
  public static Request.Builder builder() {
    return new DefaultRequestBuilder();
  }

  static Request request(final URL url) {
    return new ForwardingRequest() {

      @Override
      public URL url() {
        return url;
      }

      @Override
      protected Request delegate() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
