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
    > docker run -itd -p80:8080 singals/spring-boot-poc

### Integrating with Jenkins:
- Run Jenkins as a Docker container, using following command:
    > docker run -itd -p 8080:8080 -p 50000:50000 --name=jenkins -v {user_dir}:/var/jenkins_home jenkins:2.60.3

    Be sure to swap out `{user_dir}` by intended directory.
- To get the admin password for Jenkins, execute following command:
    > docker exec -it jenkins cat /var/jenkins_home/secrets/initialAdminPassword

    Use this password to login to Jenkins (http://localhost:8080) and install suggested Plugins.