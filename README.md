# sggc
*sggc* is a simple [Google Geocoding API][] client written in and for Java. It
features fast response parsing &hellip; and that's it! It's simple like that!
Its single dependency is the [Guava libraries][] by Google.

## License
Released under the permissive [MIT License][].

## Author
[Kohányi Róbert][].

## Download
Add the library as a dependency in your project's *pom.xml* like this.

```xml
<dependency>
  <groupId>com.github.kohanyirobert</groupId>
  <artifactId>sggc</artifactId>
  <version>...</version>
</dependency>
```

Releases and snapshots are deployed to [Sonatype's][] [OSS repository][] (and
synced to the [Central Maven Repository][] from there). To download JARs from
Sonatype's repository include the following repository tag inside your Maven
installation's *settings.xml* or your project's *pom.xml*.

```xml
<repository>
  <id>sonatype-oss<id>
  <url>https://oss.sonatype.org/content/groups/public</url>
</repository>
```

## Build
As the project is managed with [Maven][] you simply clone it and issue *mvn
install* or *mvn package* inside the clone's directory.

```
git clone git://github.com/kohanyirobert/sggc.git
cd sggc/
mvn package
# and/or
mvn install
```

## Usage
### Geocoding
```java
// acquire a geocoder instance
Geocoder<Request, Response> geocoder = Geocoders.geocoder();

// create a geocoding request
String address = "1600 Amphitheatre Parkway Mountain View, CA 94043";
Request request = Requests.create(address);

// then you pass this to the geocoder to get a response
Response response = geocoder.geocode(request);

// inspect the response
if (response.status() == Status.OK)
  for (Result result : response.results())
     ...
```

### Generics
```java
// define a *packer* function
Function<String, Request> packer = new Function<String, Request>() {
  @Override public Request apply(String input) {
    return Requests.create(input);
  }
};

// and an *unpacker*
Function<Response, List<String>> unpacker = new Function<Response, List<String>>() {
  @Override public List<String> apply(Response input) {
    return Lists.transform(input.results(), new Function<Result, String>() {
      @Override public String apply(Result input) {
        return input.formattedAddress();
      }
    });
  }
};

// then obtain the custom geocoder
Geocoder<String, List<String>> geocoder = Geocoders.geocoder(packer, unpacker);
```

[Google Geocoding API]: http://code.google.com/apis/maps/documentation/geocoding
[Guava libraries]: http://code.google.com/p/guava-libraries
[Kohányi Róbert]: http://kohanyirobert.github.com
[MIT License]: https://raw.github.com/kohanyirobert/sggc/master/LICENSE.txt
[Sonatype's]: http://sonatype.com
[OSS repository]: https://oss.sonatype.org
[Central Maven Repository]: http://search.maven.org
[Maven]: http://maven.apache.org
