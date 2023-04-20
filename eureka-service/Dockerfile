FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-alpine
ADD target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT [ "java", "-jar", "/app.jar"]