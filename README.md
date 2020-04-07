# DockerFirstSteps


# Spring Application Build

You need a Maven installation. Run

```
cd < Microservice directory >
mvn clean package
```

# DOCKER Build & Upload 

Build the image and give it a name, or tag.

For Customer Service Example Microservice run

```
cd CustomerServiceExampleMicroservice
docker build -t customer_service_example .
```

For Question Generator Service Microservice run

```
cd QuestionGeneratorSeriviceMicroservice
docker build -t question_generator_service .
```

For Quality Question Microservice run

```
cd QualityQuestionMicroservice
docker build -t quality_question_service .
```
You can build without cache running
```
docker build --no-cache=true -t ... .
```

WARNING! The trailing dot "." is very important! Don't forget it!

Tag the image with a repo name \<username\>/\<reponame\>:\<version\>

```
docker tag customer_service_example aceofwands/customer_service_example:0.1
docker tag question_generator_service aceofwands/question_generator_service:0.1
docker tag quality_question_service aceofwands/quality_question_service:0.1
```

Push it to the docker-hub
```
docker push aceofwands/customer_service_example:0.1
docker push aceofwands/question_generator_service:0.1
docker push aceofwands/quality_question_service:0.1

```

Then move to each microservice directory and run:
```
docker-compose up
```

Enter the application container with 
```
docker exec -it <container-name> bash
```

Then look at the os version:
```
cat /etc/os-release
```
