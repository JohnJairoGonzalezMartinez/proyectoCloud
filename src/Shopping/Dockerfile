FROM adoptopenjdk/openjdk11:alpine-jre

ADD ./target/app.jar .
ADD start.sh .

ENTRYPOINT ["sh","/start.sh"]