#!/bin/bash
docker run -it --name gateway-service -p 8080:8080 \
    --link currency-rate-service-1:currency-rate-service-1 --link currency-rate-service-2:currency-rate-service-2 \
    -e EUREKA_HOST="172.17.0.1" \
    -e PROCESSING_URL="http://172.17.0.1:8090" \
    -e CURRENCY_URL="lb://currency-rate-service" \
    -d gateway-service

#docker run -it --name gateway-service -p 8080:8080 \
#    -e EUREKA_HOST="172.17.0.1" \
#    -e PROCESSING_URL="http://172.17.0.1:8090" \
#    -e CURRENCY_URL="http://172.17.0.1:8085" \
#    -d gateway-service