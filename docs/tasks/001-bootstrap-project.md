# Task 001: Bootstrap Backend Project

## Goal

Create the initial Java Spring Boot Gradle multi-module backend project.

## Modules

- api
- worker
- core

## Requirements

### Root project

Create:

- settings.gradle
- build.gradle
- .gitignore
- docker-compose.yml

### api

Create a Spring Boot application.

This module will later expose REST APIs for frontend.

### worker

Create a Spring Boot application.

This module will later poll and judge submissions.

### core

Create a plain Java core module.

This module will later contain:

- entities
- services
- repositories
- enums
- common exceptions

## Dependencies

Use:

- Java 21
- Spring Boot 4.x
- Gradle Groovy DSL
- MySQL
- Flyway
- Spring Data JPA

## Not Included

Do not implement:

- auth
- problem APIs
- submission APIs
- judge logic
- Docker sandbox logic

## Done When

- ./gradlew clean build passes
- api can boot
- worker can boot
- docker-compose.yml can start MySQL