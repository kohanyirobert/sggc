package com.github.kohanyirobert.sggc;

import java.util.List;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of an address component node in an XML <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding</a> response.
 */
@Immutable
public interface AddressComponent {

  /**
   * Returns this address component's long name.
   * 
   * @return this address component's long name
   */
  String longName();

  /**
   * Returns this address component's short name.
   * 
   * @return this address component's short name
   */
  String shortName();

  /**
   * Returns this address component's types as an unmodifiable list.
   * 
   * @return this address component's types as an unmodifiable list
   */
  List<Type> types();
}
