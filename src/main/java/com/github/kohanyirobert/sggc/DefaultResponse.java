package com.github.kohanyirobert.sggc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

final class DefaultResponse implements Response {

  private final Status status;
  private final List<Result> results;

  DefaultResponse(Status status, List<Result> results) {
    Preconditions.checkNotNull(status, "null status");
    Preconditions.checkNotNull(results, "null results");
    this.status = status;
    this.results = ImmutableList.copyOf(results);
  }

  @Override
  public Status status() {
    return status;
  }

  @Override
  public List<Result> results() {
    return results;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(status(), results());
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Response) {
      Response other = (Response) object;
      return status().equals(other.status())
          && results().equals(other.results());
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(Response.class)
        .add("status", status())
        .add("results", results())
        .toString();
  }
}
