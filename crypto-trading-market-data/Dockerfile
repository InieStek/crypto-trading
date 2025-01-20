FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /build

# Kopiujemy tylko potrzebne komponenty dla market-data service
COPY ./crypto-trading-parent /build/crypto-trading-parent
COPY ./crypto-trading-i18n /build/crypto-trading-i18n
COPY ./crypto-trading-market-data /build/crypto-trading-market-data

# Najpierw instalujemy parent
WORKDIR /build/crypto-trading-parent
RUN mvn clean install -DskipTests -N

# Następnie i18n
WORKDIR /build/crypto-trading-i18n
RUN mvn clean install -DskipTests

# Na końcu budujemy market-data service
WORKDIR /build/crypto-trading-market-data
RUN mvn clean package -DskipTests

# Etap uruchomieniowy
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /build/crypto-trading-market-data/target/*.jar app.jar

ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]