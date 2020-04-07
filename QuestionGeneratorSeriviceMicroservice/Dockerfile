FROM maven:3.6.3-jdk-8-slim AS MAVEN

MAINTAINER andrea0postiglione@gmail.com

COPY QuestionGeneratorSerivice /home/app/src/QuestionGeneratorSerivice

WORKDIR /home/app/src/QuestionGeneratorSerivice

RUN mvn clean package

FROM openjdk:8

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /home/wait
RUN chmod +x /home/wait

COPY --from=MAVEN /home/app/src/QuestionGeneratorSerivice/target/QuestionGeneratorSerivice-0.0.1-SNAPSHOT.jar /home/app/

WORKDIR /home/app/

CMD /home/wait && java -jar QuestionGeneratorSerivice-0.0.1-SNAPSHOT.jar