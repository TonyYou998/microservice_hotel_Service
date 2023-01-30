FROM openjdk:latest as build
MAINTAINER uit.com
copy target/microservice_host_service-0.0.1-SNAPSHOT.jar microservice_host_service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/microservice_host_service-0.0.1-SNAPSHOT.jar"]