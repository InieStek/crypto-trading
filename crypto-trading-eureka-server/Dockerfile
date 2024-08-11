# Build stage
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy the parent pom.xml and modules
COPY crypto-trading-parent /app/crypto-trading-parent
COPY crypto-trading-eureka-server /app/crypto-trading-eureka-server

# Resolve dependencies and build the application
WORKDIR /app/crypto-trading-eureka-server
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/crypto-trading-eureka-server/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
