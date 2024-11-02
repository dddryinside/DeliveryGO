FROM openjdk:21-jdk-slim

WORKDIR /DeliveryGO

COPY target/DeliveryGO-1.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]