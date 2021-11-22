FROM openjdk:11

ADD ./target/app.jar .
EXPOSE 8761/tcp
ENTRYPOINT [ "java", "-jar", "app.jar"]