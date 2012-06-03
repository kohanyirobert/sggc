package com.github.kohanyirobert.sggc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

final class DefaultGeometry implements Geometry {

  private final Location location;
  private final LocationType locationType;
  private final Viewport viewport;
  private final Bounds bounds;

  DefaultGeometry(
      Location location,
      LocationType locationType,
      Viewport viewport,
      Bounds bounds) {
    Preconditions.checkNotNull(location, "null location");
    Preconditions.checkNotNull(locationType, "null locationType");
    Preconditions.checkNotNull(viewport, "null viewport");
    this.location = location;
    this.locationType = locationType;
    this.viewport = viewport;
    this.bounds = bounds;
  }

  @Override
  public Location location() {
    return location;
  }

  @Override
  public LocationType locationType() {
    return locationType;
  }

  @Override
  public Viewport viewport() {
    return viewport;
  }

  @Override
  public Bounds bounds() {
    return bounds;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(location(), locationType(), viewport(), bounds());
  }

  // @do-not-check-next-line CyclomaticComplexity
  @Override
  public boolean equals(Object object) {
    if (object instanceof Geometry) {
      Geometry other = (Geometry) object;
      return Objects.equal(location(), other.location())
          && Objects.equal(locationType(), other.locationType())
          && Objects.equal(viewport(), other.viewport())
          && Objects.equal(bounds(), other.bounds());
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(Geometry.class)
        .add("location", location())
        .add("locationType", locationType())
        .add("viewport", viewport())
        .add("bounds", bounds())
        .toString();
  }
}
