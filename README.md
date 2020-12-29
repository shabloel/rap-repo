# rap-repo
repository for RAP project

This is an application that validates an incoming message from a client.

It checks the following:
1. Is the incoming file a zip-file?
2. Does the zipfile contain a XML-file?
3. Is the xml-file readable?
4. Is the xml-file correct according to the XSD?

To start and run the application in Docker run the following commands
in CMD:
gradlew docker          -> creates image
gradlew dockerRun       -> runs image in container

Stack:
Gradle
Java 8
Spring boot
Docker
