FROM java:8

COPY build/libs/spring-boot-poc.jar /spring_boot_poc/

WORKDIR /spring_boot_poc

EXPOSE 8080

ENTRYPOINT java -jar spring-boot-poc.jar