package com.github.kohanyirobert.sggc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

final class DefaultAddressComponent implements AddressComponent {

  private final String longName;
  private final String shortName;
  private final List<Type> types;

  DefaultAddressComponent(String longName, String shortName, List<Type> types) {
    Preconditions.checkNotNull(longName, "null longName");
    Preconditions.checkNotNull(shortName, "null shortName");
    Preconditions.checkNotNull(types, "null types");
    this.longName = longName;
    this.shortName = shortName;
    this.types = ImmutableList.copyOf(types);
  }

  @Override
  public String longName() {
    return longName;
  }

  @Override
  public String shortName() {
    return shortName;
  }

  @Override
  public List<Type> types() {
    return types;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(longName(), shortName(), types());
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof AddressComponent) {
      AddressComponent other = (AddressComponent) object;
      return Objects.equal(longName(), other.longName())
          && Objects.equal(shortName(), other.shortName())
          && Objects.equal(types(), other.types());
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(AddressComponent.class)
        .add("longName", longName())
        .add("shortName", shortName())
        .add("types", types())
        .toString();
  }
}
