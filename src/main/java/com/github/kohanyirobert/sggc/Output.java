package com.github.kohanyirobert.sggc;

import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a location-type node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public enum Output {

  // @checkstyle:off JavadocVariable

  XML("xml"),
  JSON("json");

  // @checkstyle:on JavadocVariable

  private final String value;

  private Output(String value) {
    this.value = value;
  }

  /**
   * Returns this output's value.
   * 
   * @return this output's value
   */
  public String value() {
    return value;
  }

  /**
   * Returns the output corresponding to {@code value}.
   * 
   * @param value the value used to look-up a corresponding corresponding
   * @return the corresponding corresponding to {@code value}
   * @throws NullPointerException if {@code value} is null
   * @throws IllegalArgumentException if no output corresponding to
   * {@code value} was found
   */
  public static Output get(String value) {
    Preconditions.checkNotNull(value, "null value");
    for (Output output : values())
      if (output.value.equals(value))
        return output;
    throw new IllegalArgumentException(String.format("no output "
        + "corresponding to '%s' was found", value));
  }
}
