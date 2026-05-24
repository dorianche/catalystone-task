# Catalystone Interview Exercise

> **⚠️ Public demo/interview repository** — This repository is maintained by [CatalystOne](https://github.com/CatalystOne) as a technical interview exercise. It is **not connected to any production system**. It contains no real customer data, no production credentials, and no internal infrastructure details. See [SECURITY.md](SECURITY.md) to report security concerns.

This project is an interview exercise focused on code reading, test understanding, and bug fixing.

## What You Need To Do

- Run tests.
- See failing tests.
- Fix **one test at a time**.
- Start from the **controller layer** tests first.
- Keep JDBC and JPA behavior aligned.

## Project Shape (Quick)

- Two implementations of similar use cases:
  - `jdbc/*`
  - `jpa/*`
- Flow: Controller -> Service -> Repository.
- Database: in-memory H2, seeded by Flyway on startup.

## Run Tests

```bash
./gradlew test
```

Run focused test groups when needed:

```bash
./gradlew test --tests "*JdbcProductControllerIntegrationTests"
./gradlew test --tests "*JpaProductControllerIntegrationTests"
./gradlew test --tests "*JdbcProductServiceTest"
./gradlew test --tests "*JpaProductServiceTest"
./gradlew test --tests "*JdbcProductRepoTest"
./gradlew test --tests "*JpaProductRepoTest"
```

## Recommended Order

1. `JdbcProductControllerIntegrationTests`
2. `JpaProductControllerIntegrationTests`
3. `JdbcProductServiceTest`
4. `JpaProductServiceTest`
5. `JdbcProductRepoTest`
6. `JpaProductRepoTest`

## Guidance

- Treat tests as the source of truth.
- Some methods are intentionally incomplete (`null` / empty / placeholder query).
- Make small changes, rerun tests, and commit progress incrementally to your local repo.

## Run the Application
Runt the application locally to verify it starts up correctly and the endpoints work as expected.

```bash
./gradlew bootRun
```

### API Endpoints
- `GET /api/jdbc/products/{id}` - Get product by ID (JDBC)
- `GET /api/jpa/products/{id}` - Get product by ID (JPA)
- `GET /actuator/health` - Health check

## Ownership & License

- **Maintainer:** CatalystOne Engineering
- **Security issues:** See [SECURITY.md](SECURITY.md)
- **License:** [MIT](LICENSE)

