# Build stage
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy the parent pom.xml
COPY ./crypto-trading-parent /app/crypto-trading-parent
COPY ./crypto-trading-market-data /app/crypto-trading-market-data


WORKDIR /app/crypto-trading-market-data
RUN mvn dependency:resolve
RUN mvn clean package -DskipTests


# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/crypto-trading-market-data/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]