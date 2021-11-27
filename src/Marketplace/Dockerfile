FROM adoptopenjdk/openjdk11:alpine-jre

ADD ./target/app.jar .
ADD start.sh .

EXPOSE 11000/tcp

ENTRYPOINT ["/start.sh"]