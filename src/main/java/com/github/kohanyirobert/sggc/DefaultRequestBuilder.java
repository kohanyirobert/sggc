package com.github.kohanyirobert.sggc;

import com.github.kohanyirobert.sggc.Request.Builder;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

// @checkstyle:off ClassFanOutComplexity
final class DefaultRequestBuilder implements Request.Builder {

  private static final String ALGORITHM = "HmacSHA1";

  private Protocol protocol = Protocol.HTTP;
  private Output output = Output.XML;
  private String address;
  private Location location;
  private Bounds bounds;
  private Language language;
  private Region region;
  private String client;
  private String signature;
  private boolean sensor;

  private byte[] key;

  DefaultRequestBuilder() {}

  @Override
  public Request.Builder protocol(Protocol protocol) {
    Preconditions.checkNotNull(protocol, "null protocol");
    this.protocol = protocol;
    return this;
  }

  @Override
  public Builder output(Output output) {
    Preconditions.checkNotNull(output, "null output");
    if (output == Output.JSON)
      throw new UnsupportedOperationException("json geocoding requests isn't supported");
    this.output = output;
    return this;
  }

  @Override
  public Builder address(String address) {
    Preconditions.checkNotNull(address, "null address");
    Preconditions.checkState(location == null, "location was already called");
    this.address = address;
    return this;
  }

  @Override
  public Request.Builder location(String latitude, String longitude) {
    return location(new BigDecimal(latitude), new BigDecimal(longitude));
  }

  @Override
  public Request.Builder location(double latitude, double longitude) {
    return location(BigDecimal.valueOf(latitude), BigDecimal.valueOf(longitude));
  }

  @Override
  public Request.Builder location(BigDecimal latitude, BigDecimal longitude) {
    return location(new DefaultLocation(latitude, longitude));
  }

  @Override
  public Builder location(Location location) {
    Preconditions.checkNotNull(location, "null location");
    Preconditions.checkState(address == null, "address was already called");
    this.location = location;
    return this;
  }

  @Override
  public Request.Builder bounds(
      String southWestLatitude, String southWestLongitude,
      String northEastLatitude, String northEastLongitude) {
    return bounds(
        new BigDecimal(southWestLatitude), new BigDecimal(southWestLongitude),
        new BigDecimal(northEastLatitude), new BigDecimal(northEastLongitude));
  }

  @Override
  public Request.Builder bounds(
      double southWestLatitude, double southWestLongitude,
      double northEastLatitude, double northEastLongitude) {
    return bounds(
        BigDecimal.valueOf(southWestLatitude), BigDecimal.valueOf(southWestLongitude),
        BigDecimal.valueOf(northEastLatitude), BigDecimal.valueOf(northEastLongitude));
  }

  @Override
  public Request.Builder bounds(
      BigDecimal southWestLatitude, BigDecimal southWestLongitude,
      BigDecimal northEastLatitude, BigDecimal northEastLongitude) {
    return bounds(
        new DefaultLocation(southWestLatitude, southWestLongitude),
        new DefaultLocation(northEastLatitude, northEastLongitude));
  }

  @Override
  public Request.Builder bounds(SouthWest southWest, NorthEast northEast) {
    return bounds(new DefaultBounds(southWest, northEast));
  }

  @Override
  public Request.Builder bounds(Bounds bounds) {
    Preconditions.checkNotNull(bounds, "null bounds");
    this.bounds = bounds;
    return this;
  }

  @Override
  public Request.Builder region(Region region) {
    Preconditions.checkNotNull(region, "null region");
    this.region = region;
    return this;
  }

  @Override
  public Request.Builder language(Language language) {
    Preconditions.checkNotNull(language, "null language");
    this.language = language;
    return this;
  }

  @Override
  public Request.Builder sensor(boolean sensor) {
    this.sensor = sensor;
    return this;
  }

  @Override
  public Request.Builder client(String client) {
    Preconditions.checkNotNull(client, "null client");
    this.client = client;
    return this;
  }

  @Override
  public Request.Builder signature(String key) {
    Preconditions.checkNotNull(key, "null key");
    Preconditions.checkArgument(!key.isEmpty(), "empty key");
    Preconditions.checkArgument(DatatypeConverter.parseBase64Binary(key).length > 0,
        "key: '%s' too short", key);
    this.key = DatatypeConverter.parseBase64Binary(
        key.replace('-', '+').replace('_', '/'));
    return this;
  }

  @Override
  public Request build() {
    checkAddressAndLocation();
    checkClientAndSignature();
    // build here because signature is computed in url()
    URL url = buildUrl();
    return new DefaultRequest(
        protocol,
        output,
        address,
        location,
        bounds,
        region,
        language,
        client,
        signature,
        sensor,
        url);
  }

  private void checkAddressAndLocation() {
    if (address == null && location == null)
      throw new IllegalStateException("address or location must be called, but not both of them");
  }

  private void checkClientAndSignature() {
    if (client == null)
      Preconditions.checkState(key == null, "signature was called without calling client");
    else
      Preconditions.checkState(key != null, "client was called without calling signature");
  }

  private URL buildUrl() {
    StringBuilder sb = new StringBuilder();
    appendProtocolPathAndOutput(sb);
    appendAddressOrLocation(sb);
    appendBounds(sb);
    appendRegion(sb);
    appendLanguage(sb);
    appendSensor(sb);
    appendClientAndSignature(sb);

    try {
      return new URL(sb.toString());
    } catch (MalformedURLException ex) {
      throw new IllegalStateException(String.format(
          "failed to build a url from '%s'", this), ex);
    }
  }

  private void appendProtocolPathAndOutput(StringBuilder sb) {
    sb.append(protocol.value())
        .append("://maps.googleapis.com/maps/api/geocode/")
        .append(output.value())
        .append("?");
  }

  private void appendAddressOrLocation(StringBuilder sb) {
    if (address != null)
      sb.append("address=")
          .append(encode(address));

    else if (location != null)
      sb.append("latlng=")
          .append(location.latitude())
          .append(",")
          .append(location.longitude());
  }

  private void appendBounds(StringBuilder sb) {
    if (bounds != null)
      sb.append("&bounds=")
          .append(bounds.southWest().latitude()).append(",")
          .append(bounds.southWest().longitude()).append("|")
          .append(bounds.northEast().latitude()).append(",")
          .append(bounds.northEast().longitude());
  }

  private void appendRegion(StringBuilder sb) {
    if (region != null)
      sb.append("&region=")
          .append(region.value());
  }

  private void appendLanguage(StringBuilder sb) {
    if (language != null)
      sb.append("&language=")
          .append(language.value());
  }

  private void appendSensor(StringBuilder sb) {
    sb.append("&sensor=")
        .append(sensor);
  }

  // http://code.google.com/apis/maps/documentation/webservices/#URLSigning
  private void appendClientAndSignature(StringBuilder sb) {
    if (client != null) {
      sb.append("&client=")
          .append(client);

      URL unsigned;
      try {
        unsigned = new URL(sb.toString());
      } catch (MalformedURLException ex) {
        throw new RuntimeException(ex);
      }

      String pathAndQuery = unsigned.getPath() + "?" + unsigned.getQuery();
      byte[] bytes = signPathAndQuery(pathAndQuery.getBytes(Charsets.UTF_8));
      signature = DatatypeConverter.printBase64Binary(bytes);
      sb.append("&signature=")
          .append(signature.replace('+', '-').replace('/', '_'));
    }
  }

  private String encode(String string) {
    try {
      return URLEncoder.encode(string, Charsets.UTF_8.name());
    } catch (UnsupportedEncodingException ex) {
      throw new RuntimeException(String.format("failed to encode "
          + "'%s' with 'UTF-8' encoding", address), ex);
    }
  }

  private byte[] signPathAndQuery(byte[] input) {
    try {
      Mac mac = Mac.getInstance(ALGORITHM);
      mac.init(new SecretKeySpec(key, ALGORITHM));
      return mac.doFinal(input);
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
    } catch (InvalidKeyException ex) {
      throw new IllegalStateException(ex);
    }
  }
}
