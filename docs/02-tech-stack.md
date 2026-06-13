# Tech Stack

## Backend

- Java 21
- Spring Boot 4.x
- Gradle Groovy DSL
- Spring Web
- Spring Data JPA
- Spring Validation
- MySQL
- Flyway

## Judge Worker

- Java
- Spring Boot
- MySQL polling for MVP
- Docker CLI for sandbox execution

## Infrastructure

- Docker Compose for local development
- MySQL container
- Separate containers for API and worker later

## MVP Queue Strategy

Use MySQL polling first.

The worker polls PENDING submissions and locks them before judging.

A message queue such as Redis Streams or RabbitMQ or Kafka can be added later.