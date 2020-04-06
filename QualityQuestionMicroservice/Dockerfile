FROM maven:3.6.3-jdk-8-slim AS MAVEN

MAINTAINER andrea0postiglione@gmail.com

COPY QualityQuestionService /home/app/src/QualityQuestionService

WORKDIR /home/app/src/QualityQuestionService

RUN mvn clean package

FROM openjdk:8

COPY --from=MAVEN /home/app/src/QualityQuestionService/target/QualityQuestionService-0.0.1-SNAPSHOT.jar /home/app/

WORKDIR /home/app/

CMD ["java", "-jar", "QualityQuestionService-0.0.1-SNAPSHOT.jar"]