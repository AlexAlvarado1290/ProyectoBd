# syntax=docker/dockerfile:1.6

FROM gradle:8.10-jdk17 AS build
WORKDIR /home/gradle/project
COPY build.gradle ./
COPY src ./src
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

