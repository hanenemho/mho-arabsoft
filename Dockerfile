FROM openjdk:8-jdk-alpine
COPY ./target/fidelite_api-0.0.1-SNAPSHOT.jar /app/arabsoft.jar
ENTRYPOINT ["java","-jar","/app/arabsoft.jar"]
