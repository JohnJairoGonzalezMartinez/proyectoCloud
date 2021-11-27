FROM adoptopenjdk/openjdk11:alpine-jre

ADD ./target/app.jar .
ADD start.sh .

RUN ls

ENTRYPOINT ["ls"]
CMD [ "/start.sh" ]