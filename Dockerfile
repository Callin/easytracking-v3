FROM amazoncorretto:11

WORKDIR /usr/local

COPY ./target/easytracking-v3-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "easytracking-v3-0.0.1-SNAPSHOT.jar"]

