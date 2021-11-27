#!/bin/ash

while ! nc -z eureka 8761 ; do
    sleep 3
done

while ! nc -z dbserver 27017 ; do
    sleep 3
done

java -jar app.jar --spring.data.mongodb.host=dbserver --eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka/
