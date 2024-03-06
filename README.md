# UISpec4J 3.x

UISpec4J is an Open Source functional and/or unit testing library for Swing-based Java applications.

If you are writing a Swing application, you will appreciate UISpec4J above all for its simplicity: UISpec4J's APIs are 
designed to hide as much as possible the complexity of Swing, resulting in easy to write and easy to read test scripts. 
This is especially true when comparing UISpec4J tests with those produced using Swing or low-level, event-based 
testing libraries.

The master branch now includes support for Java 17.

How to use with Maven:

```
<dependency>
    <groupId>org.uispec4j</groupId>
    <artifactId>uispec4j</artifactId>
    <version>3.0</version>
    <scope>test</scope>
</dependency>
```

Gradle:

```
testImplementation 'org.uispec4j:uispec4j:3.0'
```
