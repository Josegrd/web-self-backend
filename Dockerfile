# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copy the Maven project's pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and package the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built .jar file from the build stage to the runtime stage
COPY --from=build /app/target/web-self-api-0.0.1-SNAPSHOT.jar /app/web-self-api-0.0.1-SNAPSHOT.jar

# Expose the application's port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/web-self-api-0.0.1-SNAPSHOT.jar"]
