# spring-boot-poc

This is a very basic Spring Boot POC. I usually use this when I want to quickly test something or when I've to
create a new project.

At this point of time it only exposes a `/ping` controller, that responds back with `/pong`.

### Prerequisites
1. Java 1.8

### Building App
- Execute the following commands from the root directory of project:
    > ./gradlew clean build
- To build a Docker image, execute the following commands from the root directory of project:
    > docker build -t singals/spring-boot-poc .

### Running App
- Execute the following commands from the root directory of project:
    > ./gradlew bootRun
- Alternatively if you have built locally or pushed docker image to hub:
    > docker run -itd -p8080:8080 singals/spring-boot-poc
