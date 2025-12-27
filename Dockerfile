FROM openjdk:17-oracle
MAINTAINER Miras
COPY target/*.jar university-app.jar
ENTRYPOINT [ "java", "-jar", "university-app.jar" ]ar"]