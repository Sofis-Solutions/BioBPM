# BioBPM

**BioBPM** is a fork of [Camunda Platform 7 Community Edition](https://github.com/camunda/camunda-bpm-platform), maintained by [Sofis Solutions](https://www.sofis.lat).

It provides a production-grade BPMN 2.0 process engine and REST API, trimmed for embedding as a microservice component in Sofis products.

## License

BioBPM is distributed under the **Apache License 2.0** — the same license as the original Camunda 7 Community Edition.

See [LICENSE](LICENSE) and [NOTICE](NOTICE) for full attribution details.

> This product is a derivative work of Camunda Platform 7 Community Edition  
> (Copyright 2013–2025 Camunda Services GmbH, Apache License 2.0).

## What is included

### Core engine

| Module | Purpose |
|---|---|
| `engine/` | Core BPMN 2.0 process engine |
| `engine-rest/` | JAX-RS REST API (javax + jakarta variants) |
| `engine-dmn/` | DMN decision engine (including FEEL) |
| `engine-spring/` | Spring Framework integration |
| `engine-cdi/` | CDI integration (javax + jakarta) |
| `engine-plugins/` | Plugin framework (Connect, Spin, LDAP identity) |
| `model-api/` | BPMN / CMMN / DMN / XML model APIs |
| `spring-boot-starter/` | Spring Boot auto-configuration and starter |
| `quarkus-extension/` | Quarkus extension |
| `freemarker-template-engine/` | FreeMarker script engine integration |

### Utilities

| Module | Purpose |
|---|---|
| `commons/` | Logging, typed-values, testing utilities |
| `juel/` | JUEL (Java Unified Expression Language) implementation |
| `spin/` | JSON / XML data-format library |
| `connect/` | HTTP and SOAP connector library |

### Distributions

| Module | Purpose |
|---|---|
| `distro/run/` | Standalone embedded-server distribution (biobpm-run JAR) |
| `distro/tomcat/` | Tomcat server distribution |
| `distro/wildfly/` | WildFly server distribution |
| `distro/wildfly26/` | WildFly 26 server distribution |
| `distro/jboss/` | JBoss server distribution |
| `distro/webjar/` | Web JAR (static assets) |
| `distro/sql-script/` | Database schema scripts (ACT_* tables) |
| `distro/license-book/` | Third-party license inventory |

### Integrations and extras

| Module | Purpose |
|---|---|
| `clients/java/` | External task client |
| `javaee/` | JEE / EJB integrations (javax + jakarta) |
| `examples/` | Example applications (invoice process) |

### Testing and QA

| Module | Purpose |
|---|---|
| `test-utils/` | ArchUnit rules, JUnit 5 extensions, Testcontainers support |
| `qa/` | Integration tests and QA infrastructure |

## What was removed

- `webapps/` UI source (Cockpit, Tasklist, Admin) — contains HeroDevs NES License components; only packaging assembly stubs remain
- `.ci/`, `Jenkinsfile` — Camunda's internal CI (replaced with `.github/workflows/build.yml`)

## Build

### Requirements

- Java 17+
- Maven 3.8+

### Quick build

Use the provided script — it handles the BOM bootstrap and the full build in one step:

```bash
./build.sh
```

Extra Maven flags (e.g. `-T4`) can be appended and are forwarded to the final `mvn install` call.

### Manual bootstrap (first build only)

If you prefer to run the steps yourself, the BOM modules must be installed to the local Maven cache before the first full build:

```bash
mvn install -N
mvn install -f spin/bom/pom.xml
mvn install -f commons/bom/pom.xml
mvn install -f connect/bom/pom.xml
mvn install -f engine-dmn/bom/pom.xml
mvn install -f bom/pom.xml -N
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
  <groupId>lat.sofis.biobpm</groupId>
  <artifactId>camunda-engine</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Database

The `distro/sql-script/` directory contains the `ACT_*` table scripts for all supported databases (H2, PostgreSQL, MySQL, Oracle, SQL Server, DB2). Database schema compatibility with Camunda 7 is intentionally preserved.

## Upstream

This fork is based on **Camunda Platform 7.24.0** (the last Community Edition release).  
Original repository: https://github.com/camunda/camunda-bpm-platform
