package com.github.kohanyirobert.sggc;

import java.util.List;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface Response {

  /**
   * Returns this response's status.
   * 
   * @return this response's status
   */
  Status status();

  /**
   * Returns this response's results as an unmodifiable list.
   * 
   * @return this response's status as an unmodifiable list
   */
  List<Result> results();
}
