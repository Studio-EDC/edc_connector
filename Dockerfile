FROM gradle:8.10-jdk17 AS build

WORKDIR /app
COPY . /app

RUN sed -i 's/\r$//' gradlew && chmod +x gradlew && ./gradlew :connector:shadowJar

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=build /app/connector/build/libs/connector.jar /app/connector.jar

ENTRYPOINT ["sh", "-c", "java -Dedc.keystore=/app/certs/cert.pfx -Dedc.keystore.password=\"$EDC_KEYSTORE_PASSWORD\" -Dedc.fs.config=/app/configuration/config.properties -jar /app/connector.jar"]
