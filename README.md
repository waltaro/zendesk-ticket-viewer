# Zendesk Ticket Viewer
A command line interface ticket viewer for the Zendesk 2016 internship coding challenge.

## Prerequisites
- [Java 8](https://java.com/en/download/)
- [JSON In Java (org.json library)](http://central.maven.org/maven2/org/json/json/20160212/json-20160212.jar)
- [JUnit 4](http://junit.org/junit4/)

## Installation
1. Clone the git repository into a working directory of your choice.
2. Create a folder called "lib" in the working directory.
3. Create a folder called "out" in the working directory.
4. Download and move the JSON In Java library into the folder "lib".

## Compilation
In the working directory, compile the java program with this command:

`javac -sourcepath src -d out -cp lib/json-20160212.jar src/Main.java`

##Usage
To run the compiled program execute this command in the command line.

`java -cp out:lib/json-20160212.jar Main`

##License
MIT
