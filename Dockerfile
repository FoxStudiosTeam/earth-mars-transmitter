FROM gradle:8.4.0-jdk21-alpine AS build
LABEL authors="Senko-san"
LABEL authors="AgniaEndie"
LABEL authors="GekkStr"
LABEL authors="xxlegendzxx22"

WORKDIR /earth-mars-transmitter
COPY . /earth-mars-transmitter
RUN gradle bootJar
ENTRYPOINT ["java","-XX:+UseZGC","-Dotel.instrumentation.tomcat.enabled=false", "-jar", "/earth-mars-transmitter/build/libs/earth-mars-transmitter-0.0.1-SNAPSHOT.jar"]
