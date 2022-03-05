# syntax=docker/dockerfile:1
FROM java:8
MAINTAINER Mabel.Chen
VOLUME /tmp
COPY target/helper.jar api-gateway.jar
EXPOSE 8020
ENTRYPOINT java -jar api-gateway.jar