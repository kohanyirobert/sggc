package com.github.kohanyirobert.sggc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

final class DefaultBounds implements Viewport {

  private final SouthWest southWest;
  private final NorthEast northEast;

  DefaultBounds(SouthWest southWest, NorthEast northEast) {
    Preconditions.checkNotNull(southWest, "null southWest");
    Preconditions.checkNotNull(northEast, "null northEast");
    this.southWest = southWest;
    this.northEast = northEast;
  }

  @Override
  public SouthWest southWest() {
    return southWest;
  }

  @Override
  public NorthEast northEast() {
    return northEast;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(southWest(), northEast());
  }

  @Override
  public boolean equals(Object object) {
    boolean equals = false;
    if (object == this)
      equals = true;
    else if (object instanceof Bounds) {
      Bounds other = (Bounds) object;
      equals = Objects.equal(southWest(), other.southWest())
          && Objects.equal(northEast(), other.northEast());
    }
    return equals;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(Bounds.class)
        .add("southWest", southWest())
        .add("northEast", northEast())
        .toString();
  }
}
