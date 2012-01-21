package com.github.kohanyirobert.sggc;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

interface ExtendedXMLStreamReader extends XMLStreamReader {

  void require(int type) throws XMLStreamException;

  void require(int type, String localName) throws XMLStreamException;
}
