# Build stage
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Kopiowanie źródeł common
COPY ./ /app

# Instalowanie artefaktów w lokalnym repozytorium
RUN mvn clean install -DskipTests
