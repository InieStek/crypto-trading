# Build stage
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy the parent POM and module-specific files
COPY ./crypto-trading-parent /app/crypto-trading-parent
COPY ./crypto-trading-order /app/crypto-trading-order

# Resolve dependencies and build the application
WORKDIR /app/crypto-trading-order
RUN mvn dependency:resolve
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/crypto-trading-order/target/*.jar app.jar

# Environment variables (optional, can be overridden at runtime)
ENV MONGODB_HOST=mongo
ENV MONGODB_PORT=27017
ENV MONGODB_DATABASE=cryptotrading
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/
ENV ORDER_EXPIRATION_TIME=30m

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
