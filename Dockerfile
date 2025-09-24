FROM maven:4.0.0-rc-4-amazoncorretto-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre-ubi9-minimal
WORKDIR /app
COPY --from=build /workspace/target/demo-mdc-0.0.1-SNAPSHOT.jar app.jar
ENV LOG_PATH=/app/logs
RUN mkdir -p "$LOG_PATH"
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
