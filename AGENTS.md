# AGENTS.md

## What this repository is
- Spring Boot 4.0.0 + Java 25 interview exercise with intentionally incomplete code.
- This repository is used as a technical interview assignment for candidates.
- The same product use-cases are implemented twice: `jdbc/*` and `jpa/*`.
- Database is in-memory H2, created and seeded by Flyway migrations at startup.

## What an agent should help with
- Treat this as a candidate exercise where the candidate implements the missing logic.
- Help the candidate identify failing tests, understand expected behavior, and implement minimal correct changes.
- Prefer incremental guidance (repository first, then service, then controller) so changes stay easy to review.
- Keep JDBC and JPA implementations behaviorally aligned while using their respective data-access styles.
- Prioritize getting tests green over adding extra features or refactoring unrelated code.
- Call out edge cases covered by tests (ordering, null handling, map iteration order, and endpoint behavior).

## Big-picture architecture
- Entry point: `src/main/java/com/catalystone/iv/IvApplication.java`.
- Shared flow in both stacks: Controller -> Service -> Repository.
- JDBC API surface starts at `src/main/java/com/catalystone/iv/jdbc/controller/JdbcProductController.java` (`/product`).
- JPA API surface starts at `src/main/java/com/catalystone/iv/jpa/Controller/JpaProductController.java` (`/jpa/product`).
- Persistent model is a single `PRODUCT` table plus `PURCHASE_ORDER_PRODUCT` in `src/main/resources/db/migration/V1__create_tables.sql`.

## Where requirements really live
- Treat tests as executable spec; many production methods intentionally return `null`/empty values.
- Candidate success criteria: implement the necessary production logic so the targeted tests pass.
- Primary target tests:
  - `src/test/java/com/catalystone/iv/jdbc/repository/JdbcProductRepoTest.java`
  - `src/test/java/com/catalystone/iv/jpa/repository/JpaProductRepoTest.java`
  - `src/test/java/com/catalystone/iv/jdbc/controller/JdbcProductControllerIntegrationTests.java`
  - `src/test/java/com/catalystone/iv/jpa/controller/JpaProductControllerIntegrationTests.java`
- Service tests also contain TODO-style helper methods (`doSomething(...)`) that must be implemented.

## Project-specific conventions and gotchas
- Keep JDBC and JPA behavior aligned (same semantics, different data-access technology).
- `application.properties` sets `server.servlet.context-path=/iv`, but MockMvc tests call paths like `/product/1` and `/jpa/product/1`.
- JPA repository methods use `@Query(nativeQuery = true)` placeholders (`"write your query"`): fill with SQL against `PRODUCT`.
- `allIdsAlphabeticalOrder` requires sorting by product name ascending, then price descending (see test comment).
- `getIdPriceMappingInAscOrder` assertions are order-sensitive; return IDs sorted ascending and preserve iteration order (e.g., `LinkedHashMap`).
- Category is persisted as `String` even though enum types exist (`ProductCategory`); follow existing model shape.
- Package naming is not fully conventional (`jpa/Controller` directory uses uppercase `Controller`); keep consistency with existing imports.

## Developer workflows
```bash
./gradlew test
./gradlew test --tests "*JdbcProductRepoTest"
./gradlew test --tests "*JpaProductRepoTest"
./gradlew bootRun
```
- H2 console is enabled (`spring.h2.console.enabled=true`), typically reachable under `/iv/h2-console` when app is running.
- Flyway migrations are loaded from `classpath:db/migration`; add new `V<next>__*.sql` files for schema/data changes.

## Integration points and dependencies
- Spring Data JPA (`JpaRepository`) and Spring JDBC (`NamedParameterJdbcTemplate`) coexist by design.
- Jackson `ObjectMapper` is provided explicitly in `src/main/java/com/catalystone/iv/config/ProjectConfig.java` and used in controller integration tests.
- Core dependencies are declared in `build.gradle.kts` (Spring Web/JDBC/JPA, H2, Flyway, Lombok, JUnit/AssertJ/Mockito).

