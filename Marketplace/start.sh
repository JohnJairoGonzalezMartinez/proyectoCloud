#!/bin/ash
while ! nc -z eureka 8761 ; do
    echo "Waiting for the Eureka Server"
    sleep 3
done

while ! nc -z dbserver 27017 ; do
    echo "Waiting for the DB Server Server"
    sleep 3
done

java -jar app.jar --eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka/