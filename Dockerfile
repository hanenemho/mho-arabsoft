FROM openjdk:8-jdk-alpine
COPY ./target/Facturation-Back-0.0.1-SNAPSHOT.jar /app/arabsoft.jar
ENTRYPOINT ["java","-jar","/app/arabsoft.jar"]
