# Build stage
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy the parent POM and current module
COPY ./crypto-trading-parent /app/crypto-trading-parent
COPY ./crypto-trading-api-gateway /app/crypto-trading-api-gateway

# Resolve dependencies and build the application
WORKDIR /app/crypto-trading-api-gateway
RUN mvn dependency:resolve
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/crypto-trading-api-gateway/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
