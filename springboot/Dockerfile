FROM maven:3.6.1-amazoncorretto-11

ADD pom.xml /usr/src/mymaven/
WORKDIR /usr/src/mymaven
RUN mvn dependency:go-offline

ADD . /usr/src/mymaven
RUN mvn clean package

CMD ["java", "-jar", "target/asakatu-kun-service-0.1.0.jar"]
