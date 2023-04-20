FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-alpine
ADD target/*.jar app.jar

ENV EUREKA_HOST=localhost
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"

EXPOSE 8085 8787

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Duser.country=RU -Duser.language=ru -Dspring.profiles.active=cloud \
    -jar /app.jar \
    --cloud.eureka-host=$EUREKA_HOST \
    "]