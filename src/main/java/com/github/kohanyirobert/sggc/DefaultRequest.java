package com.github.kohanyirobert.sggc;

import com.google.common.base.Objects;

import java.net.URL;

final class DefaultRequest implements Request {

  private final Protocol protocol;
  private final Output output;
  private final String address;
  private final Location location;
  private final Bounds bounds;
  private final Language language;
  private final Region region;
  private final boolean sensor;
  private final String client;
  private final String signature;
  private final URL url;

  // @do-not-check ParameterNumber
  DefaultRequest(
      Protocol protocol,
      Output output,
      String address,
      Location location,
      Bounds bounds,
      Region region,
      Language language,
      String client,
      String signature,
      boolean sensor,
      URL url) {
    this.protocol = protocol;
    this.output = output;
    this.address = address;
    this.location = location;
    this.bounds = bounds;
    this.region = region;
    this.language = language;
    this.client = client;
    this.signature = signature;
    this.sensor = sensor;
    this.url = url;
  }

  @Override
  public Protocol protocol() {
    return protocol;
  }

  @Override
  public Output output() {
    return output;
  }

  @Override
  public String address() {
    return address;
  }

  @Override
  public Location location() {
    return location;
  }

  @Override
  public Bounds bounds() {
    return bounds;
  }

  @Override
  public Language language() {
    return language;
  }

  @Override
  public Region region() {
    return region;
  }

  @Override
  public boolean sensor() {
    return sensor;
  }

  @Override
  public String client() {
    return client;
  }

  @Override
  public String signature() {
    return signature;
  }

  @Override
  public URL url() {
    return url;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(
        protocol(),
        output(),
        address(),
        location(),
        bounds(),
        region(),
        language(),
        Boolean.valueOf(sensor()),
        client(),
        signature());
  }

  // @do-not-check CyclomaticComplexity
  @Override
  public boolean equals(Object object) {
    if (object instanceof DefaultRequest) {
      DefaultRequest other = (DefaultRequest) object;
      return protocol().equals(other.protocol())
          && output().equals(other.output())
          && Objects.equal(address(), other.address())
          && Objects.equal(location(), other.location())
          && Objects.equal(bounds(), other.bounds())
          && Objects.equal(region(), other.region())
          && Objects.equal(language(), other.language())
          && sensor() == other.sensor()
          && Objects.equal(client(), other.client())
          && Objects.equal(signature(), other.signature());
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(Request.class)
        .add("protocol", protocol())
        .add("output", output())
        .add("address", address())
        .add("latlng", location())
        .add("bounds", bounds())
        .add("region", region())
        .add("language", language())
        .add("sensor", String.valueOf(sensor))
        .add("client", client())
        .add("signature", signature())
        .toString();
  }
}
