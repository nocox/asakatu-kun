FROM maven:3.6.1-amazoncorretto-11

ADD . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean package

CMD ["java", "-jar", "target/gs-rest-service-0.1.0.jar"]