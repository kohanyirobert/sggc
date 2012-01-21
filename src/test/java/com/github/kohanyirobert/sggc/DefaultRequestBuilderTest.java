package com.github.kohanyirobert.sggc;

import com.google.common.base.Strings;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public final class DefaultRequestBuilderTest {

  private static final String EMPTY_SIGNATURE = "";
  private static final String TOO_SHORT_SIGNATURE = "a";
  private static final String LONG_ENOUGH_SIGNATURE = Strings.repeat(TOO_SHORT_SIGNATURE, 10);

  private DefaultRequestBuilder builder;

  public DefaultRequestBuilderTest() {}

  @Before
  public void setUp() {
    builder = new DefaultRequestBuilder();
  }

  @Test(expected = IllegalStateException.class)
  public void build_withNoParameters() {
    builder.build();
  }

  @Test(expected = IllegalStateException.class)
  public void build_withAddressAndLocation() {
    builder.address("").location(new Location() {

      @Override
      public BigDecimal longitude() {
        return null;
      }

      @Override
      public BigDecimal latitude() {
        return null;
      }
    });
  }

  @Test(expected = IllegalStateException.class)
  public void build_withClientAndNoSignature() {
    builder.client("").build();
  }

  @Test(expected = IllegalStateException.class)
  public void build_withSignatureAndNoClient() {
    builder.signature(LONG_ENOUGH_SIGNATURE).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void build_withClientAndEmptySignature() {
    builder.address("").client("").signature(EMPTY_SIGNATURE).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void build_withClientAndTooShortSignature() {
    builder.address("").client("").signature(TOO_SHORT_SIGNATURE).build();
  }

  @Test
  public void build_withAddress() {
    builder.address("").build();
  }

  @Test
  public void build_withAddressClientAndSignature() {
    builder.address("").client("").signature(LONG_ENOUGH_SIGNATURE).build();
  }
}
