#!/bin/bash
docker run -it --name eureka-service -p 8761:8761 \
    -d eureka-service
