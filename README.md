# Demo MDC Logging Service

Spring Boot sample that demonstrates MDC-aware logging and streams JSON logs into the Elastic stack.

## Prerequisites
- Java 17 JDK
- Maven 3.9+
- Docker and Docker Compose v2 for the ELK environment

## Build and Test
```bash
mvn clean package
```
The command compiles the app, runs the unit tests, and produces `target/demo-mdc-0.0.1-SNAPSHOT.jar` with the Spring Boot launcher manifest.

## Run the Application Locally
```bash
mvn spring-boot:run
```
The service listens on `http://localhost:8080`. Invoke the sample endpoint with trace headers:
```bash
curl -H "X-Trace-Id: t-1234" -H "X-User-Id: u-42" "http://localhost:8080/hello?name=xiaojin"
```
Logs are written in JSON format under `logs/` by default.

## Run with Docker
Build the container image locally:
```bash
docker build -t demo-mdc .
```
Start the app:
```bash
docker run --rm -p 8080:8080 demo-mdc
```

## Run the Full Service + ELK Stack
The `docker/elk/docker-compose.yml` file orchestrates the Spring app, Elasticsearch, Logstash, Filebeat, and Kibana.

```bash
cd docker/elk
docker compose up -d --build
```

### Verifying the Stack
- Application: `http://localhost:8080/hello?name=test`
- Elasticsearch: `http://localhost:9200/_cat/indices?v`
- Kibana: `http://localhost:5601`

Logs from the app flow to `logs/spring-log-demo-*` indices in Elasticsearch via Filebeat and Logstash.

### Tearing Down
```bash
docker compose down -v
```
This stops the containers and removes the attached `es-data` and `app-logs` volumes.

