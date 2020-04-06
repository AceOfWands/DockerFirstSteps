FROM maven:3.6.3-jdk-8-slim AS MAVEN

MAINTAINER andrea0postiglione@gmail.com

COPY CustomerServiceExample /home/app/src/CustomerServiceExample

WORKDIR /home/app/src/CustomerServiceExample

RUN mvn clean package

FROM openjdk:8

COPY --from=MAVEN /home/app/src/CustomerServiceExample/target/CustomerServiceExample-0.0.1-SNAPSHOT.jar /home/app/

WORKDIR /home/app/

CMD ["java", "-jar", "CustomerServiceExample-0.0.1-SNAPSHOT.jar"]