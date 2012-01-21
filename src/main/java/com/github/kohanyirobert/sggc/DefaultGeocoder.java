package com.github.kohanyirobert.sggc;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

// @do-not-check Class(DataAbstractionCoupling|FanOutComplexity)
final class DefaultGeocoder extends ForwardingExtendedXMLStreamReader implements Geocoder<Request, Response> {

  private static final String GEOCODE_RESPONSE = "GeocodeResponse";
  private static final String STATUS = "status";
  private static final String RESULT = "result";
  private static final String TYPE = "type";
  private static final String FORMATTED_ADDRESS = "formatted_address";
  private static final String ADDRESS_COMPONENT = "address_component";
  private static final String GEOMETRY = "geometry";
  private static final String PARTIAL_MATCH = "partial_match";
  private static final String LOCATION = "location";
  private static final String LAT = "lat";
  private static final String LNG = "lng";
  private static final String LOCATION_TYPE = "location_type";
  private static final String VIEWPORT = "viewport";
  private static final String SOUTHWEST = "southwest";
  private static final String NORTHEAST = "northeast";
  private static final String BOUNDS = "bounds";
  private static final String LONG_NAME = "long_name";
  private static final String SHORT_NAME = "short_name";

  private static final StreamFilter FILTER = new StreamFilter() {

    @Override
    public boolean accept(XMLStreamReader reader) {
      return reader.isWhiteSpace()
          ? false
          : reader.isStartElement()
              || reader.isEndElement()
              || reader.isCharacters();
    }
  };

  private static final ThreadLocal<XMLStreamReader> READER = new ThreadLocal<XMLStreamReader>();

  DefaultGeocoder() {}

  @Override
  public Response geocode(Request request) throws IOException {
    InputStream stream = null;
    try {
      stream = InputStreams.get(request.url());
      READER.set(XMLStreamReaders.create(stream, FILTER));
      return readResponse();
    } catch (XMLStreamException ex) {
      throw new RuntimeException(String.format("failed to "
          + "read xml resource at '%s'", request.url()), ex);
    } finally {
      XMLStreamReaders.close(READER.get());
      InputStreams.close(stream);
      READER.remove();
    }
  }

  @Override
  protected XMLStreamReader delegate() {
    return READER.get();
  }

  private Response readResponse() throws XMLStreamException {
    Status status = null;
    List<Result> results = Lists.newArrayList();

    require(START_ELEMENT, GEOCODE_RESPONSE);
    next();
    // @formatter:off
    for (String localName = getLocalName();
        !GEOCODE_RESPONSE.equals(localName); 
        next(), localName = getLocalName()) 
    // @formatter:on
      if (STATUS.equals(localName))
        status = readStatus();
      else if (RESULT.equals(localName))
        results.add(readResult());
      else
        throw new IllegalStateException();

    return new DefaultResponse(status, results);
  }

  private Status readStatus() throws XMLStreamException {
    Status status = null;
    require(START_ELEMENT, STATUS);
    next();
    require(CHARACTERS);
    status = Status.get(getText());
    next();
    require(END_ELEMENT, STATUS);
    return status;
  }

  // @do-not-check CyclomaticComplexity
  private DefaultResult readResult() throws XMLStreamException {
    List<Type> types = Lists.newArrayList();
    String fomattedAddress = null;
    List<AddressComponent> addressComponents = Lists.newArrayList();
    DefaultGeometry geometry = null;
    Boolean partialMatch = null;

    require(XMLStreamConstants.START_ELEMENT, RESULT);
    next();
    // @formatter:off
    for (String localName = getLocalName();
        !RESULT.equals(localName);
        next(), localName = getLocalName())
    // @formatter:on
      if (TYPE.equals(localName))
        types.add(readType());
      else if (FORMATTED_ADDRESS.equals(localName))
        fomattedAddress = readFormattedAddress();
      else if (ADDRESS_COMPONENT.equals(localName))
        addressComponents.add(readAddressComponent());
      else if (GEOMETRY.equals(localName))
        geometry = readGeometry();
      else if (PARTIAL_MATCH.equals(localName))
        partialMatch = readPartialMatch();
      else
        throw new IllegalStateException();

    return new DefaultResult(types, fomattedAddress, addressComponents, geometry, partialMatch);
  }

  private Type readType() throws XMLStreamException {
    Type type = null;
    require(START_ELEMENT, TYPE);
    next();
    require(CHARACTERS);
    type = Type.get(getText());
    next();
    require(END_ELEMENT, TYPE);
    return type;
  }

  private String readFormattedAddress() throws XMLStreamException {
    String formattedAddress = null;
    require(START_ELEMENT, FORMATTED_ADDRESS);
    next();
    require(CHARACTERS);
    formattedAddress = getText();
    next();
    require(END_ELEMENT, FORMATTED_ADDRESS);
    return formattedAddress;
  }

  // @do-not-check CyclomaticComplexity
  private DefaultAddressComponent readAddressComponent() throws XMLStreamException {
    String longName = null;
    String shortName = null;
    List<Type> types = Lists.newArrayList();

    require(START_ELEMENT, ADDRESS_COMPONENT);
    next();
    // @formatter:off
    for (String localName = getLocalName();
        !ADDRESS_COMPONENT.equals(localName);
        next(), localName = getLocalName())
    // @formatter:on
      if (LONG_NAME.equals(localName))
        longName = readLongName();
      else if (SHORT_NAME.equals(localName))
        shortName = readShortName();
      else if (TYPE.equals(localName))
        types.add(readType());
      else
        throw new IllegalStateException();

    return new DefaultAddressComponent(longName, shortName, types);
  }

  private String readLongName() throws XMLStreamException {
    String longName = null;
    require(START_ELEMENT, LONG_NAME);
    next();
    require(CHARACTERS);
    longName = getText();
    next();
    require(END_ELEMENT, LONG_NAME);
    return longName;
  }

  private String readShortName() throws XMLStreamException {
    String shortName = null;
    require(START_ELEMENT, SHORT_NAME);
    next();
    require(CHARACTERS);
    shortName = getText();
    next();
    require(END_ELEMENT, SHORT_NAME);
    return shortName;
  }

  // @do-not-check CyclomaticComplexity
  private DefaultGeometry readGeometry() throws XMLStreamException {
    DefaultLocation location = null;
    LocationType locationType = null;
    DefaultBounds viewport = null;
    DefaultBounds bounds = null;

    require(START_ELEMENT, GEOMETRY);
    next();
    // @formatter:off
    for (String localName = getLocalName();
        !GEOMETRY.equals(localName);
        next(), localName = getLocalName())
    // @formatter:on
      if (LOCATION.equals(localName))
        location = readLocation();
      else if (LOCATION_TYPE.equals(localName))
        locationType = readLocationType();
      else if (VIEWPORT.equals(localName))
        viewport = readViewport();
      else if (BOUNDS.equals(localName))
        bounds = readBounds();
      else
        throw new IllegalStateException();

    return new DefaultGeometry(location, locationType, viewport, bounds);
  }

  // @do-not-check ExecutableStatementCount
  private DefaultLocation readLocation() throws XMLStreamException {
    BigDecimal latitude = null;
    BigDecimal longitude = null;
    require(START_ELEMENT, LOCATION);
    next();
    require(START_ELEMENT, LAT);
    next();
    require(CHARACTERS);
    latitude = new BigDecimal(getText());
    next();
    require(END_ELEMENT, LAT);
    next();
    require(START_ELEMENT, LNG);
    next();
    require(CHARACTERS);
    longitude = new BigDecimal(getText());
    next();
    require(END_ELEMENT, LNG);
    next();
    require(END_ELEMENT, LOCATION);
    return new DefaultLocation(latitude, longitude);
  }

  private LocationType readLocationType() throws XMLStreamException {
    LocationType locationType = null;
    require(START_ELEMENT, LOCATION_TYPE);
    next();
    require(CHARACTERS);
    locationType = LocationType.get(getText());
    next();
    require(END_ELEMENT, LOCATION_TYPE);
    return locationType;
  }

  private DefaultBounds readViewport() throws XMLStreamException {
    SouthWest southWest = null;
    NorthEast northEast = null;
    require(START_ELEMENT, VIEWPORT);
    next();
    southWest = readSouthWest();
    next();
    northEast = readNorthEast();
    next();
    require(END_ELEMENT, VIEWPORT);
    return new DefaultBounds(southWest, northEast);
  }

  // @do-not-check ExecutableStatementCount
  private SouthWest readSouthWest() throws XMLStreamException {
    BigDecimal latitude = null;
    BigDecimal longitude = null;
    require(START_ELEMENT, SOUTHWEST);
    next();
    require(START_ELEMENT, LAT);
    next();
    require(CHARACTERS);
    latitude = new BigDecimal(getText());
    next();
    require(END_ELEMENT, LAT);
    next();
    require(START_ELEMENT, LNG);
    next();
    require(CHARACTERS);
    longitude = new BigDecimal(getText());
    next();
    require(END_ELEMENT, LNG);
    next();
    require(END_ELEMENT, SOUTHWEST);
    return new DefaultLocation(latitude, longitude);
  }

  // @do-not-check ExecutableStatementCount
  private NorthEast readNorthEast() throws XMLStreamException {
    BigDecimal latitude = null;
    BigDecimal longitude = null;
    require(START_ELEMENT, NORTHEAST);
    next();
    require(START_ELEMENT, LAT);
    next();
    require(CHARACTERS);
    latitude = new BigDecimal(getText());
    next();
    require(END_ELEMENT, LAT);
    next();
    require(START_ELEMENT, LNG);
    next();
    require(CHARACTERS);
    longitude = new BigDecimal(getText());
    next();
    require(END_ELEMENT, LNG);
    next();
    require(END_ELEMENT, NORTHEAST);
    return new DefaultLocation(latitude, longitude);
  }

  private DefaultBounds readBounds() throws XMLStreamException {
    SouthWest southWest = null;
    NorthEast northEast = null;
    require(START_ELEMENT, BOUNDS);
    next();
    southWest = readSouthWest();
    next();
    northEast = readNorthEast();
    next();
    require(END_ELEMENT, BOUNDS);
    return new DefaultBounds(southWest, northEast);
  }

  private Boolean readPartialMatch() throws XMLStreamException {
    Boolean partialMatch = null;
    require(START_ELEMENT, PARTIAL_MATCH);
    next();
    require(CHARACTERS);
    partialMatch = Boolean.valueOf(getText());
    next();
    require(END_ELEMENT, PARTIAL_MATCH);
    return partialMatch;
  }
}
