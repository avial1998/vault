# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

RUN echo "Staring Maven build stage..."
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN echo "Maven build completed successfully."

# Run stage
RUN echo "Starting Run stage..."
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/vault-*.jar vault.jar
EXPOSE 8081
RUN echo "Run stage completed successfully."
# Entrypoint
ENTRYPOINT ["java", "-jar", "vault.jar"]