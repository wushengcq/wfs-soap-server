#! /usr/bin/env sh
mvn clean
mvn -D maven.test.skip=true package
mvn tomcat:run
