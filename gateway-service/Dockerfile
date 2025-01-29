# Step 1: Use Maven or JDK image to build the JAR
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the module's source code and pom.xml into the container
COPY . /app

# Step 4: Build the project using Maven
RUN mvn clean package -DskipTests

# Step 5: Use a smaller JDK image for running the Spring Boot application
FROM openjdk:17-jdk-slim

# Step 6: Install curl in the runtime environment
RUN apt-get update && apt-get install -y curl \
    && rm -rf /var/lib/apt/lists/*


# Step 7: Set the working directory in the runtime image
WORKDIR /app

# Step 8: Copy the JAR file from the build image to the runtime image
COPY --from=build /app/target/*.jar /app/gateway-service.jar

# Step 9: Expose port 8081 for the application
EXPOSE 8888

# Step 10: Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/gateway-service.jar"]