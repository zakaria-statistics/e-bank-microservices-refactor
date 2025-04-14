FROM maven:3.9.4-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copy POM first to leverage caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Then copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

RUN apk add --no-cache curl

WORKDIR /app
COPY --from=build /app/target/*.jar /app/gateway-service.jar

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app/gateway-service.jar"]