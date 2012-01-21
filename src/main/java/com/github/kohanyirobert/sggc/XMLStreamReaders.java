package com.github.kohanyirobert.sggc;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Monitor;

import java.io.InputStream;
import java.nio.charset.Charset;

import javax.annotation.Nullable;
import javax.annotation.WillClose;
import javax.annotation.WillNotClose;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

final class XMLStreamReaders {

  private static final Monitor MONITOR = new Monitor(true);
  private static final XMLInputFactory FACTORY = XMLInputFactory.newFactory();

  private XMLStreamReaders() {}

  public static void close(@WillClose @Nullable XMLStreamReader reader) {
    if (reader != null)
      try {
        reader.close();
      } catch (XMLStreamException ex) {
        throw failedTo("close", reader.getEncoding(), ex);
      }
  }

  public static XMLStreamReader create(@WillNotClose InputStream stream,
      StreamFilter filter) {
    XMLStreamReader unfiltered = create(stream);
    return filter(unfiltered, filter);
  }

  public static XMLStreamReader create(@WillNotClose InputStream stream,
      StreamFilter filter, Charset charset) {
    XMLStreamReader unfiltered = create(stream, charset);
    return filter(unfiltered, filter);
  }

  public static XMLStreamReader create(InputStream stream) {
    return create(stream, Charsets.UTF_8);
  }

  public static XMLStreamReader create(@WillNotClose InputStream stream,
      Charset charset) {
    Preconditions.checkNotNull(stream, "null stream");
    Preconditions.checkNotNull(charset, "null charset");
    MONITOR.enter();
    try {
      return FACTORY.createXMLStreamReader(stream, charset.name());
    } catch (XMLStreamException ex) {
      throw failedTo("create", charset.name(), ex);
    } finally {
      MONITOR.leave();
    }
  }

  public static XMLStreamReader filter(@WillNotClose XMLStreamReader reader,
      StreamFilter filter) {
    Preconditions.checkNotNull(reader, "null reader");
    Preconditions.checkNotNull(filter, "null filter");
    MONITOR.enter();
    try {
      return FACTORY.createFilteredReader(reader, filter);
    } catch (XMLStreamException ex) {
      throw failedTo("filter", reader.getEncoding(), ex);
    } finally {
      MONITOR.leave();
    }
  }

  private static RuntimeException failedTo(String reason, String encoding, Throwable cause) {
    throw new RuntimeException(String.format("failed to '%s' xml stream "
        + "reader with '%s' encoding", reason, encoding), cause);
  }
}
