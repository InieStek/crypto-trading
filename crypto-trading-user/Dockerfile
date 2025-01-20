FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /build

# Kopiujemy tylko potrzebne komponenty dla user service
COPY ./crypto-trading-parent /build/crypto-trading-parent
COPY ./crypto-trading-common /build/crypto-trading-common
COPY ./crypto-trading-user /build/crypto-trading-user

# Najpierw instalujemy parent
WORKDIR /build/crypto-trading-parent
RUN mvn clean install -DskipTests -N

# Potem common
WORKDIR /build/crypto-trading-common
RUN mvn clean install -DskipTests

# Na końcu budujemy user service
WORKDIR /build/crypto-trading-user
RUN mvn clean package -DskipTests

# Etap uruchomieniowy
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /build/crypto-trading-user/target/*.jar app.jar

ENV MONGODB_HOST=mongo \
    MONGODB_PORT=27017 \
    MONGODB_DATABASE=cryptotrading_users \
    EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]