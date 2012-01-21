package com.github.kohanyirobert.sggc;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.CheckForNull;
import javax.annotation.WillClose;

final class InputStreams {

  private InputStreams() {}

  static InputStream get(URL url) throws IOException {
    Preconditions.checkNotNull(url, "null url");
    URLConnection connection;
    try {
      connection = url.openConnection();
    } catch (IOException ex) {
      throw new IOException(String.format("failed to open connection to '%s'", url), ex);
    }

    try {
      connection.connect();
    } catch (IOException ex) {
      throw new IOException(String.format("failed to connect to '%s'", url), ex);
    }

    try {
      return connection.getInputStream();
    } catch (IOException ex) {
      throw new IOException(String.format("failed to get inputstream for '%s'", url), ex);
    }
  }

  static void close(@WillClose @CheckForNull InputStream stream) {
    if (stream != null)
      try {
        stream.close();
      } catch (IOException ex) {
        throw new RuntimeException("failed to close stream", ex);
      }
  }
}
