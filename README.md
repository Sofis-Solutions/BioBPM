# BioBPM

**BioBPM** is a fork of [Camunda Platform 7 Community Edition](https://github.com/camunda/camunda-bpm-platform), maintained by [Sofis Solutions](https://www.sofis.lat).

It provides a production-grade BPMN 2.0 process engine and REST API, trimmed for embedding as a microservice component in Sofis products.

## License

BioBPM is distributed under the **Apache License 2.0** — the same license as the original Camunda 7 Community Edition.

See [LICENSE](LICENSE) and [NOTICE](NOTICE) for full attribution details.

> This product is a derivative work of Camunda Platform 7 Community Edition  
> (Copyright 2013–2025 Camunda Services GmbH, Apache License 2.0).

## What is included

| Module | Purpose |
|---|---|
| `engine/` | Core BPMN 2.0 process engine |
| `engine-rest/` | REST API |
| `engine-dmn/` | DMN decision engine |
| `engine-spring/` | Spring Framework integration |
| `engine-cdi/` | CDI integration |
| `engine-plugins/` | Engine plugin framework |
| `model-api/` | BPMN / CMMN / DMN / XML model APIs |
| `spring-boot-starter/` | Spring Boot starter for microservice packaging |
| `commons/`, `juel/`, `spin/`, `connect/` | Core utilities |
| `distro/sql-script/` | Database schema scripts (ACT_* tables) |
| `distro/license-book/` | Third-party license inventory |

## What was removed

- `webapps/` (Cockpit, Tasklist, Admin UI) — contains HeroDevs NES License components
- `distro/tomcat`, `distro/wildfly`, `distro/jboss`, `distro/run` — server distributions
- `clients/java/` — external task client
- `javaee/` — JEE/EJB integrations
- `examples/` — demo applications
- `.ci/`, `Jenkinsfile` — Camunda's internal CI (replaced with `.github/workflows/build.yml`)

## Build

### Requirements

- Java 17+
- Maven 3.8+

### Bootstrap (first build only)

The BOM modules must be installed to the local Maven cache before the first full build:

```bash
mvn install -N
mvn install -f spin/bom/pom.xml
mvn install -f commons/bom/pom.xml
mvn install -f connect/bom/pom.xml
mvn install -f engine-dmn/bom/pom.xml
mvn install -f bom/camunda-only-bom/pom.xml
mvn install -f bom/camunda-bom/pom.xml
mvn install -f parent/pom.xml -N
mvn install -f internal-dependencies/pom.xml -N
```

### Full build

```bash
mvn install -DskipTests
```

### Engine unit tests

```bash
mvn test -pl engine -P check-engine
```

### Validate module tree only

```bash
mvn validate
```

## Maven coordinates

```xml
<dependency>
  <groupId>com.sofis.biobpm</groupId>
  <artifactId>camunda-engine</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Database

The `distro/sql-script/` directory contains the `ACT_*` table scripts for all supported databases (H2, PostgreSQL, MySQL, Oracle, SQL Server, DB2). Database schema compatibility with Camunda 7 is intentionally preserved.

## Upstream

This fork is based on **Camunda Platform 7.24.0** (the last Community Edition release).  
Original repository: https://github.com/camunda/camunda-bpm-platform
