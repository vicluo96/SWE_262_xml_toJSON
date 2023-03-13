![Json-Java logo](https://github.com/stleary/JSON-java/blob/master/images/JsonJava.png?raw=true)

<sub><sup>image credit: Ismael Pérez Ortiz</sup></sub>


JSON in Java [package org.json]
===============================

[![Maven Central](https://img.shields.io/maven-central/v/org.json/json.svg)](https://mvnrepository.com/artifact/org.json/json)

**[Click here if you just want the latest release jar file.](https://search.maven.org/remotecontent?filepath=org/json/json/20220924/json-20220924.jar)**


# Overview

[JSON](http://www.JSON.org/) is a light-weight language-independent data interchange format.

The JSON-Java package is a reference implementation that demonstrates how to parse JSON documents into Java objects and how to generate new JSON documents from the Java classes.

Project goals include:
* Reliable and consistent results
* Adherence to the JSON specification 
* Easy to build, use, and include in other projects
* No external dependencies
* Fast execution and low memory footprint
* Maintain backward compatibility
* Designed and tested to use on Java versions 1.6 - 1.11

The files in this package implement JSON encoders and decoders. The package can also convert between JSON and XML, HTTP headers, Cookies, and CDL.

# If you would like to contribute to this project

For more information on contributions, please see [CONTRIBUTING.md](https://github.com/stleary/JSON-java/blob/master/docs/CONTRIBUTING.md)

Bug fixes, code improvements, and unit test coverage changes are welcome! Because this project is currently in the maintenance phase, the kinds of changes that can be accepted are limited. For more information, please read the [FAQ](https://github.com/stleary/JSON-java/wiki/FAQ).

# Build Instructions

The org.json package can be built from the command line, Maven, and Gradle. The unit tests can be executed from Maven, Gradle, or individually in an IDE e.g. Eclipse.
 
**Building from the command line**

*Build the class files from the package root directory src/main/java*
````
javac org/json/*.java
````

*Create the jar file in the current directory*
````
jar cf json-java.jar org/json/*.class
````

*Compile a program that uses the jar (see example code below)*
````
javac -cp .;json-java.jar Test.java (Windows)
javac -cp .:json-java.jar Test.java (Unix Systems)
````

*Test file contents*

````
import org.json.JSONObject;
public class Test {
    public static void main(String args[]){
       JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
       System.out.println(jo.toString());
    }
}
````

*Execute the Test file*
```` 
java -cp .;json-java.jar Test (Windows)
java -cp .:json-java.jar Test (Unix Systems)
````

*Expected output*

````
{"abc":"def"}
````

 
**Tools to build the package and execute the unit tests**

Execute the test suite with Maven:
```
mvn clean test
```

Execute the test suite with Gradlew:

```
gradlew clean build test
```

# Notes

For more information, please see [NOTES.md](https://github.com/stleary/JSON-java/blob/master/docs/NOTES.md)

# Files

For more information on files, please see [FILES.md](https://github.com/stleary/JSON-java/blob/master/docs/FILES.md)

# Release history:

For the release history, please see [RELEASES.md](https://github.com/stleary/JSON-java/blob/master/docs/RELEASES.md)

# Milestone 2 

New Feature: 

Add an overloaded static method to the XML class with the signature <br>
`static JSONObject toJSONObject(Reader reader, JSONPointer path)` 

Use recursion to find the suboject of target path. To improve performance, as soon as the target path is found, the program will stop reading the rest of the xml, and return the target JSON sub object. To do this, I created `Early termination` to stop the recursion process when the sub object is found.<br>

Add an overloaded static method to the XML class with the signature <br>
`static JSONObject toJSONObject(Reader reader, JSONPointer path, JSONObject replacement)`

Use recursion to replace the target sub object with the given object. This function converts the XML file to JSON while it is looking for the target path. Once found, the target object is replaced, and the rest of the file is converted to JSON and returned. 

# Milestone 3

New Feature:

Add an overloaded static method to the XML class with the signature <br>
`static JSONObject toJSONObject(Reader reader, YOURTYPEHERE keyTransformer)`

Use functional programming to transform all keys according to given client function. Performance is increased through this method by transforming keys while the conversion from XML to JSON is being done.This can save time compared to key transformation after conversion because it eliminates the need to make multiple passes over the data. <br>

# Milestone 4

New Feature:

Add streaming method to the library that allow the client code to chain operations on JSON nodes. <br>
`// in client space` <br>
`JSONObject obj = XML.toJSONObject("<Books><book><title>AAA</title><author>ASmith</author></book><book><title>BBB</title><author>BSmith</author></book></Books>");` <br>
`obj.toStream().forEach(node -> do some transformation, possibly based on the path of the node);` <br>
`List<String> titles = obj.toStream().map(node -> extract value for key "title").collect(Collectors.toList());` <br>
`obj.toStream().filter(node -> node with certain properties).forEach(node -> do some transformation);` <br>

These stream operations apply to JSONObject, and are started by transforming those objects into streams with the new toStream() method.

# Milestone 5
New Feature:
Add asynchronous methods to the library that allow the client code to proceed, while specifying what to do when the JSONObject becomes available.
The user can also choose to transform the xml key or not: <br>
`XML.toJSONObject(aReader, (key) -> (newKey), (JSONObject jo) -> {jo.write(aWriter);}, (Exception e) -> {/* something went wrong */});`
This is useful for when reading very large files.

## Build and run instruction:
Same as the process in JSON-Java library, remember change the test file name to M2, M3 or M4Test.java
