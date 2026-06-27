
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

ARG APP_VERSION=
ARG BUILD_NUMBER=local

RUN if [ -n "$APP_VERSION" ]; then \
        mvn -B versions:set -DnewVersion=$APP_VERSION -DgenerateBackupPoms=false; \
    fi && \
    mvn -B clean package -DskipTests -Dbuild.number=$BUILD_NUMBER

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/calculator-app-*.jar app.jar
CMD ["java", "-jar", "app.jar"]