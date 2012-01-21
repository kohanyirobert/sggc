package com.github.kohanyirobert.sggc;

import javax.xml.stream.XMLStreamException;

abstract class ForwardingExtendedXMLStreamReader extends ForwardingXMLStreamReader
    implements ExtendedXMLStreamReader {

  protected ForwardingExtendedXMLStreamReader() {}

  @Override
  public void require(int type) throws XMLStreamException {
    require(type, null, null);
  }

  @Override
  public void require(int type, String localName) throws XMLStreamException {
    require(type, null, localName);
  }
}
