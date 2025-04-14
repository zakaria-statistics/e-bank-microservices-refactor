# Use Maven image for building the application
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build

# Set the working directory
WORKDIR /app

# Copy POM file first to leverage caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use a lightweight JRE image for running the application
FROM eclipse-temurin:17-jre-alpine

# Install curl for runtime debugging or health checks
RUN apk add --no-cache curl

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar /app/account-service.jar

# Expose the application port
EXPOSE 8082

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/account-service.jar"]