FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-alpine
ADD target/*.jar app.jar

ENV EUREKA_HOST=localhost
ENV PROCESSING_URL="http://localhost:8090"
ENV CURRENCY_URL="http://localhost:8085"

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java -jar /app.jar \
    --cloud.eureka-host=$EUREKA_HOST \
    --cloud.processing-service-url=$PROCESSING_URL \
    --cloud.currency-service-url=$CURRENCY_URL \
    "]