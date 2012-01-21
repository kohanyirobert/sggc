package com.github.kohanyirobert.sggc;

import com.google.common.io.Resources;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

public final class DefaultGeocoderTest {

  private final DefaultGeocoder geocoder;

  public DefaultGeocoderTest() {
    geocoder = new DefaultGeocoder();
  }

  @Test(expected = NullPointerException.class)
  public void geocode_withNullRequest() throws IOException {
    geocoder.geocode(null);
  }

  @Test
  public void geocode() throws IOException {
    URL xml = Resources.getResource("1600 Amphitheatre Parkway Mountain View, CA 94043.xml");

    Response response = geocoder.geocode(Requests.request(xml));
    assertEquals(Status.OK, response.status());
    assertEquals(1, response.results().size());

    Result result = response.results().get(0);
    assertEquals(1, result.types().size());
    assertEquals(Type.STREET_ADDRESS, result.types().get(0));
    // @checkstyle:off MagicNumber
    assertEquals(7, result.addressComponents().size());

    Geometry geometry = result.geometry();
    assertEquals(new BigDecimal("37.4211444"), geometry.location().latitude());
    assertEquals(new BigDecimal("-122.0853032"), geometry.location().longitude());
    assertEquals(LocationType.ROOFTOP, geometry.locationType());
  }
}
