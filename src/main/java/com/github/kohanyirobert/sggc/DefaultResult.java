package com.github.kohanyirobert.sggc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

import javax.annotation.Nullable;

final class DefaultResult implements Result {

  private final List<Type> types;
  private final List<AddressComponent> addressComponents;
  private final String formattedAddress;
  private final Geometry geometry;
  private final Boolean partialMatch;

  // @do-not-check ParameterNumber
  DefaultResult(
      List<Type> types,
      String formattedAddress,
      List<AddressComponent> addressComponents,
      Geometry geometry,
      @Nullable Boolean partialMatch) {
    Preconditions.checkNotNull(types, "null types");
    Preconditions.checkNotNull(formattedAddress, "null formattedAddress");
    Preconditions.checkNotNull(addressComponents, "null addressComponents");
    Preconditions.checkNotNull(geometry, "null geometry");
    this.types = ImmutableList.copyOf(types);
    this.formattedAddress = formattedAddress;
    this.addressComponents = ImmutableList.copyOf(addressComponents);
    this.geometry = geometry;
    this.partialMatch = partialMatch;
  }

  @Override
  public List<Type> types() {
    return types;
  }

  @Override
  public List<AddressComponent> addressComponents() {
    return addressComponents;
  }

  @Override
  public String formattedAddress() {
    return formattedAddress;
  }

  @Override
  public Geometry geometry() {
    return geometry;
  }

  @Override
  public Boolean partialMatch() {
    return partialMatch;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(
        types(),
        addressComponents(),
        formattedAddress(),
        geometry(),
        partialMatch());
  }

  // @do-not-check CyclomaticComplexity
  @Override
  public boolean equals(Object object) {
    if (object instanceof Result) {
      Result other = (Result) object;
      return Objects.equal(types(), other.types())
          && Objects.equal(formattedAddress(), other.formattedAddress())
          && Objects.equal(addressComponents(), other.addressComponents())
          && Objects.equal(geometry(), other.geometry())
          && Objects.equal(partialMatch(), other.partialMatch());
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(Result.class)
        .add("types", types())
        .add("formattedAddress", formattedAddress())
        .add("addressComponents", addressComponents())
        .add("geometry", geometry())
        .add("partialMatch", partialMatch())
        .toString();
  }
}
