FROM openjdk:8
COPY rest-service-validator-0.0.1-SNAPSHOT.jar rest-service-validator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/rest-service-validator-0.0.1-SNAPSHOT.jar"]