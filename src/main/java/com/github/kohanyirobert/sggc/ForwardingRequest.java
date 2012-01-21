package com.github.kohanyirobert.sggc;

import com.google.common.collect.ForwardingObject;

import java.net.URL;

abstract class ForwardingRequest extends ForwardingObject implements Request {

  protected ForwardingRequest() {}

  @Override
  public Protocol protocol() {
    return delegate().protocol();
  }

  @Override
  public Output output() {
    return delegate().output();
  }

  @Override
  public String address() {
    return delegate().address();
  }

  @Override
  public Location location() {
    return delegate().location();
  }

  @Override
  public Bounds bounds() {
    return delegate().bounds();
  }

  @Override
  public Language language() {
    return delegate().language();
  }

  @Override
  public Region region() {
    return delegate().region();
  }

  @Override
  public boolean sensor() {
    return delegate().sensor();
  }

  @Override
  public String client() {
    return delegate().client();
  }

  @Override
  public String signature() {
    return delegate().signature();
  }

  @Override
  public URL url() {
    return delegate().url();
  }

  @Override
  protected abstract Request delegate();
}
