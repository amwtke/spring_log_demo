# Repository Guidelines

## Project Structure & Module Organization
- Application sources live under `src/main/java/com/example/mdc`; `async` holds executor wiring, `web` contains HTTP filters/interceptors, and `DemoMdcApplication.java` hosts the Spring Boot entry point.
- Shared assets (logging templates, `application.yml`, etc.) belong in `src/main/resources`. Runtime logs default to the `logs/` folder, and Maven output lands in `target/`.

## Build, Test, and Development Commands
- `mvn spring-boot:run` boots the service with hot reload for quick feedback.
- `mvn clean package` produces the runnable JAR in `target/demo-mdc-0.0.1-SNAPSHOT.jar`.
- `mvn test` executes the JUnit/Spring Boot test suite; add `-Dspring.profiles.active=local` when exercising profile-specific config.

## Coding Style & Naming Conventions
- Use Java 25 language level (see `pom.xml` compiler `release` setting) with 4-space indentation and braces on the same line.
- Package classes by feature (e.g., `async`, `web`) and favor descriptive names like `TraceMdcFilter` over abbreviations.
- Apply Spring annotations over explicit XML, and keep logging structured via `log.info("message", kv("userId", id))`.

## Testing Guidelines
- Co-locate tests in `src/test/java` mirroring the main package (`com.example.mdc`). Name test classes `<ComponentName>Test` and favor `@SpringBootTest` only when slice tests will not cover behaviour.
- Target high coverage on MDC propagation paths—mock async executors when needed. Run `mvn test` before every push.

## Commit & Pull Request Guidelines
- This repo currently lacks historical commits; default to Conventional Commits (`feat:`, `fix:`, `chore:`) with concise scopes (e.g., `feat(web): add TraceMdcFilter`).
- Keep branches rebased on `main`, reference related issue IDs in the body, and document behavioural changes or new log fields.
- Pull requests should include a summary, testing evidence (`mvn test` output or screenshots for log pipelines), and highlight any configuration updates.

## Logging & Configuration Notes
- Logback is configured for JSON output via `logstash-logback-encoder`; verify new log keys render correctly in `logs/application.log`.
- Use `application-<profile>.yml` per environment and avoid committing secrets; prefer Spring Config import from vault solutions when available.
