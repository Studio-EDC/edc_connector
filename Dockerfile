FROM gradle:7.6-jdk17 AS build

WORKDIR /app
COPY . /app

# Build un único módulo genérico
RUN ./gradlew :connector:build

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=build /app/connector/build/libs/connector.jar /app/connector.jar

CMD ["java", "-Dedc.keystore=/app/certs/cert.pfx", "-Dedc.keystore.password=${EDC_KEYSTORE_PASSWORD}", "-Dedc.fs.config=/app/configuration/config.properties", "-jar", "/app/connector.jar"]