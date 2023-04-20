#!/bin/bash
docker run -it --name currency-rate-service-1 -p 8086:8085 -h currency-rate-service-1 \
    -e EUREKA_HOST="172.17.0.1" \
    -d currency-rate-service

docker run -it --name currency-rate-service-2 -p 8087:8085 -h currency-rate-service-2 \
    -e EUREKA_HOST="172.17.0.1" \
    -d currency-rate-service