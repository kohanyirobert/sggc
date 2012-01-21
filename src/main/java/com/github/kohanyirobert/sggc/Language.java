package com.github.kohanyirobert.sggc;

import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * Representation of a <a
 * href="http://code.google.com/apis/maps/documentation/geocoding/"
 * >geocoding request's</a> display language parameter.
 * <p>
 * <b>Note</b>: the values are based upon the <a href=
 * "https://spreadsheets.google.com/pub?key=p9pdwsai2hDMsLkXsoM05KQ&gid=1">
 * display languages supported by the Google Geocoding API</a>.
 * </p>
 */
@Immutable
public enum Language {

  // @checkstyle:off JavadocVariable

  AR("ar"),
  BG("bg"),
  BN("bn"),
  CA("ca"),
  CS("cs"),
  DA("da"),
  DE("de"),
  EL("el"),
  EN("en"),
  EN_AU("en-AU"),
  EN_GB("en-GB"),
  ES("es"),
  EU("eu"),
  FA("fa"),
  FI("fi"),
  FIL("fil"),
  FR("fr"),
  GL("gl"),
  GU("gu"),
  HI("hi"),
  HR("hr"),
  HU("hu"),
  ID("id"),
  IT("it"),
  IW("iw"),
  JA("ja"),
  KN("kn"),
  KO("ko"),
  LT("lt"),
  LV("lv"),
  ML("ml"),
  MR("mr"),
  NL("nl"),
  NN("nn"),
  NO("no"),
  OR("or"),
  PL("pl"),
  PT("pt"),
  PT_BR("pt-BR"),
  PT_PT("pt-PT"),
  RM("rm"),
  RO("ro"),
  RU("ru"),
  SK("sk"),
  SL("sl"),
  SR("sr"),
  SV("sv"),
  TA("ta"),
  TE("te"),
  TH("th"),
  TL("tl"),
  TR("tr"),
  UK("uk"),
  VI("vi"),
  ZH_CN("zh-CN"),
  ZH_TW("zh-TW");

  // @checkstyle:off JavadocVariable

  private final String value;

  private Language(String value) {
    this.value = value;
  }

  /**
   * Returns this language's value.
   * 
   * @return this language's value
   */
  public String value() {
    return value;
  }

  /**
   * Returns the language corresponding to {@code value}.
   * 
   * @param value the value used to look-up a corresponding language
   * @return the language corresponding to {@code value}
   * @throws NullPointerException if {@code value} is null
   * @throws IllegalArgumentException if no language corresponding to
   * {@code value} was found
   */
  public static Language get(String value) {
    Preconditions.checkNotNull(value, "null value");
    for (Language language : values())
      if (language.value.equals(value))
        return language;
    throw new IllegalArgumentException(String.format("no language "
        + "corresponding to '%s' was found", value));
  }
}
