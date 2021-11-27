#!/bin/ash
echo has entered
while ! nc -z eureka 8761 ; do
    echo "Waiting for the Eureka Server"
    sleep 3
done

while ! nc -z dbserver 27017 ; do
    echo "Waiting for the DB Server Server"
    sleep 3
done
echo has started
java -jar app.jar --spring.data.mongodb.host=dbserver --eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka/