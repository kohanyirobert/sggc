package com.github.kohanyirobert.sggc;

import com.google.common.collect.ForwardingObject;

import java.io.IOException;

/**
 * A {@linkplain Geocoder geocoder} which forwards all its method calls to
 * another geocoder. Subclasses should override one or more methods to modify
 * the
 * behavior of the backing list as desired per the <a
 * href="http://en.wikipedia.org/wiki/Decorator_pattern">decorator pattern</a>.
 */
// ignore missing type param
// @checkstyle:off JavadocType
abstract class ForwardingGeocoder<K, V> extends ForwardingObject implements Geocoder<K, V> {

  /**
   * Constructor for use by subclasses.
   */
  protected ForwardingGeocoder() {}

  // @checkstyle:off DesignForExtension
  @Override
  public V geocode(K request) throws IOException {
    return delegate().geocode(request);
  }

  @Override
  protected abstract Geocoder<K, V> delegate();
}
