package com.github.kohanyirobert.sggc;

import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding request's</a> protocol.
 */
@Immutable
public enum Protocol {

  // @checkstyle:off JavadocVariable

  HTTP("http"),
  HTTPS("https");

  // @checkstyle:on JavadocVariable

  private final String value;

  private Protocol(String value) {
    this.value = value;
  }

  /**
   * Returns this protocol's value.
   * 
   * @return this protocol's value
   */
  public String value() {
    return value;
  }

  /**
   * Returns the protocol corresponding to {@code value}.
   * 
   * @param value the value used to look-up a corresponding protocol
   * @return the protocol corresponding to {@code value}
   * @throws NullPointerException if {@code value} is null
   * @throws IllegalArgumentException if no protocol corresponding to
   * {@code value} was found
   */
  public static Protocol get(String value) {
    Preconditions.checkNotNull(value, "null value");
    for (Protocol output : values())
      if (output.value.equals(value))
        return output;
    throw new IllegalArgumentException(String.format("no protocol"
        + "corresponding to '%s' was found", value));
  }
}
