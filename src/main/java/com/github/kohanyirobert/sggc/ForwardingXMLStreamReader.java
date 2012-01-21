package com.github.kohanyirobert.sggc;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

// @do-not-check MethodCount
abstract class ForwardingXMLStreamReader implements XMLStreamReader {

  protected ForwardingXMLStreamReader() {}

  @Override
  public void close() throws XMLStreamException {
    delegate().close();
  }

  @Override
  public int getAttributeCount() {
    return delegate().getAttributeCount();
  }

  @Override
  public String getAttributeLocalName(int index) {
    return delegate().getAttributeLocalName(index);
  }

  @Override
  public QName getAttributeName(int index) {
    return delegate().getAttributeName(index);
  }

  @Override
  public String getAttributeNamespace(int index) {
    return delegate().getAttributeNamespace(index);
  }

  @Override
  public String getAttributePrefix(int index) {
    return delegate().getAttributePrefix(index);
  }

  @Override
  public String getAttributeType(int index) {
    return delegate().getAttributeType(index);
  }

  @Override
  public String getAttributeValue(int index) {
    return delegate().getAttributeValue(index);
  }

  @Override
  public String getAttributeValue(String namespaceURI, String localName) {
    return delegate().getAttributeValue(namespaceURI, localName);
  }

  @Override
  public String getCharacterEncodingScheme() {
    return delegate().getCharacterEncodingScheme();
  }

  @Override
  public String getElementText() throws XMLStreamException {
    return delegate().getElementText();
  }

  @Override
  public String getEncoding() {
    return delegate().getEncoding();
  }

  @Override
  public int getEventType() {
    return delegate().getEventType();
  }

  @Override
  public String getLocalName() {
    return delegate().getLocalName();
  }

  @Override
  public Location getLocation() {
    return delegate().getLocation();
  }

  @Override
  public QName getName() {
    return delegate().getName();
  }

  @Override
  public NamespaceContext getNamespaceContext() {
    return delegate().getNamespaceContext();
  }

  @Override
  public int getNamespaceCount() {
    return delegate().getNamespaceCount();
  }

  @Override
  public String getNamespacePrefix(int index) {
    return delegate().getNamespacePrefix(index);
  }

  @Override
  public String getNamespaceURI() {
    return delegate().getNamespaceURI();
  }

  @Override
  public String getNamespaceURI(int index) {
    return delegate().getNamespaceURI(index);
  }

  @Override
  public String getNamespaceURI(String prefix) {
    return delegate().getNamespaceURI(prefix);
  }

  @Override
  public String getPIData() {
    return delegate().getPIData();
  }

  @Override
  public String getPITarget() {
    return delegate().getPITarget();
  }

  @Override
  public String getPrefix() {
    return delegate().getPrefix();
  }

  @Override
  public Object getProperty(String name) {
    return delegate().getProperty(name);
  }

  @Override
  public String getText() {
    return delegate().getText();
  }

  @Override
  public char[] getTextCharacters() {
    return delegate().getTextCharacters();
  }

  @Override
  public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws XMLStreamException {
    return delegate().getTextCharacters(sourceStart, target, targetStart, length);
  }

  @Override
  public int getTextLength() {
    return delegate().getTextLength();
  }

  @Override
  public int getTextStart() {
    return delegate().getTextStart();
  }

  @Override
  public String getVersion() {
    return delegate().getVersion();
  }

  @Override
  public boolean hasName() {
    return delegate().hasName();
  }

  @Override
  public boolean hasNext() throws XMLStreamException {
    return delegate().hasNext();
  }

  @Override
  public boolean hasText() {
    return delegate().hasText();
  }

  @Override
  public boolean isAttributeSpecified(int index) {
    return delegate().isAttributeSpecified(index);
  }

  @Override
  public boolean isCharacters() {
    return delegate().isCharacters();
  }

  @Override
  public boolean isEndElement() {
    return delegate().isEndElement();
  }

  @Override
  public boolean isStandalone() {
    return delegate().isStandalone();
  }

  @Override
  public boolean isStartElement() {
    return delegate().isStartElement();
  }

  @Override
  public boolean isWhiteSpace() {
    return delegate().isWhiteSpace();
  }

  @Override
  public int next() throws XMLStreamException {
    return delegate().next();
  }

  @Override
  public int nextTag() throws XMLStreamException {
    return delegate().nextTag();
  }

  @Override
  public void require(int type, String namespaceURI, String localName) throws XMLStreamException {
    delegate().require(type, namespaceURI, localName);
  }

  @Override
  public boolean standaloneSet() {
    return delegate().standaloneSet();
  }

  protected abstract XMLStreamReader delegate();
}
