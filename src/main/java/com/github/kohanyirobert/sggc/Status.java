package com.github.kohanyirobert.sggc;

import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a status node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public enum Status {

  // @checkstyle:off JavadocVariable

  INVALID_REQUEST("INVALID_REQUEST"),
  OK("OK"),
  OVER_QUERY_LIMIT("OVER_QUERY_LIMIT"),
  REQUEST_DENIED("REQUEST_DENIED"),
  ZERO_RESULTS("ZERO_RESULTS");

  // @checkstyle:on JavadocVariable

  private final String value;

  private Status(String value) {
    this.value = value;
  }

  /**
   * Returns this status' value.
   * 
   * @return this status' value
   */
  public String value() {
    return value;
  }

  /**
   * Returns the status corresponding to {@code value}.
   * 
   * @param value the value used to look-up a corresponding status
   * @return the status corresponding to {@code value}
   * @throws NullPointerException if {@code value} is null
   * @throws IllegalArgumentException if no status corresponding to
   * {@code value} was found
   */
  public static Status get(String value) {
    Preconditions.checkNotNull(value, "null value");
    for (Status status : values())
      if (status.value.equals(value))
        return status;
    throw new IllegalArgumentException(String.format("no status"
        + "corresponding to '%s' was found", value));
  }
}
