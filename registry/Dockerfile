# syntax=docker/dockerfile:1
FROM java:8
MAINTAINER Mabel.Chen
VOLUME /tmp
COPY target/helper.jar registry.jar
EXPOSE 8010
ENTRYPOINT java -jar registry.jar