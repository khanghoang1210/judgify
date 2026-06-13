# Judgify Copilot Instructions

You are working on Judgify, a coding problem platform similar to LeetCode.

## Current Scope

Frontend already exists. This repository focuses on backend only:
- Java Spring Boot backend
- MySQL database
- custom judge worker
- Docker-based sandbox execution

## Architecture

Use a Gradle Groovy DSL multi-module project:

- api: REST API used by the frontend
- worker: background judge engine
- core: core entities, services, repositories, enums, exceptions

Do not create unnecessary microservices.

## Backend Rules

- Use Java 21.
- Use Spring Boot 4.x.
- Use Gradle Groovy DSL.
- Use constructor injection.
- Use DTOs for request/response.
- Do not expose JPA entities directly from controllers.
- Use MySQL.
- Use Flyway for database migrations.
- Use snake_case table and column names.
- Keep controller, service, repository boundaries clean.
- Prefer records for simple request/response DTOs where appropriate.
- Prefer Lombok only if the project already uses it; otherwise write explicit constructors/getters/setters.

## Judge Engine Rules

- Never execute submitted user code inside the API process.
- User code must run only inside Docker sandbox containers.
- Worker polls PENDING submissions and marks them JUDGING before execution.
- Supported MVP languages:
    - python3
    - cpp17

Submission statuses:
- PENDING
- JUDGING
- ACCEPTED
- WRONG_ANSWER
- TIME_LIMIT_EXCEEDED
- MEMORY_LIMIT_EXCEEDED
- RUNTIME_ERROR
- COMPILE_ERROR
- SYSTEM_ERROR

## Sandbox Rules

Docker execution must use:
- no network
- memory limit
- CPU limit
- process limit
- timeout
- output size limit
- non-root user where possible

Do not expose hidden test case input, expected output, or actual output to normal users.

## Workflow

Before editing code:
1. Read relevant docs.
2. Produce a short implementation plan.
3. Make small, reviewable changes.
4. Do not rewrite unrelated files.
5. After changes, summarize modified files and how to test.