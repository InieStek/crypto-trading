# Build stage
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy the parent POM and module-specific files
COPY ./crypto-trading-parent /app/crypto-trading-parent
COPY ./crypto-trading-i18n /app/crypto-trading-i18n

# Build the application
WORKDIR /app/crypto-trading-i18n
RUN mvn dependency:resolve
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/crypto-trading-i18n/target/*.jar app.jar

# Environment variables (optional, can be overridden at runtime)
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/
ENV CRYPTO_I18N_TRANSLATION_SERVICE_URL=http://translation-service:5001/translate

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
