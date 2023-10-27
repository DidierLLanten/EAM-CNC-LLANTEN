FROM openjdk:17-jdk-alpine

COPY target/api-0.0.1-SNAPSHOT.jar java-app-delete.jar

ENTRYPOINT ["java", "-jar", "java-app-delete.jar"]