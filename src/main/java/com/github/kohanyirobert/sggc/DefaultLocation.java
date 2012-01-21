package com.github.kohanyirobert.sggc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

import java.math.BigDecimal;

class DefaultLocation implements SouthWest, NorthEast {

  private static final Range<BigDecimal> LATITUDE_RANGE =
      Ranges.closed(new BigDecimal("-90.0"), new BigDecimal("90.0"));

  private static final Range<BigDecimal> LONGITUDE_RANGE =
      Ranges.closed(new BigDecimal("-180.0"), new BigDecimal("180.0"));

  private final BigDecimal latitude;
  private final BigDecimal longitude;

  DefaultLocation(BigDecimal latitude, BigDecimal longitude) {
    Preconditions.checkNotNull(latitude, "null latitude");
    Preconditions.checkNotNull(longitude, "null longitude");
    Preconditions.checkArgument(LATITUDE_RANGE.contains(latitude),
        "latitude: '%s' is outside of range: '%s'", latitude, LATITUDE_RANGE);
    Preconditions.checkArgument(LONGITUDE_RANGE.contains(longitude),
        "longitude: '%s' is outside of range: '%s'", longitude, LONGITUDE_RANGE);
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @Override
  public BigDecimal latitude() {
    return latitude;
  }

  @Override
  public BigDecimal longitude() {
    return longitude;
  }

  @Override
  public final int hashCode() {
    return Objects.hashCode(latitude(), longitude());
  }

  @Override
  public final boolean equals(Object object) {
    if (object instanceof Location) {
      Location other = (Location) object;
      return ComparisonChain.start()
          .compare(latitude(), other.latitude())
          .compare(longitude(), other.longitude())
          .result() == 0;
    }
    return false;
  }

  @Override
  public final String toString() {
    return Objects.toStringHelper(Location.class)
        .add("latitude", latitude())
        .add("longitude", longitude())
        .toString();
  }
}
