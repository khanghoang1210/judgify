# Judgify Backend Architecture

## Modules

Judgify backend uses 3 modules:

- api: Spring Boot REST API.
- worker: background judge worker.
- core: core entities, services, repositories, enums, and common exceptions.

## Runtime Flow

Frontend
-> api
-> MySQL
-> worker polls pending submissions
-> Docker sandbox runs submitted code
-> worker saves results to MySQL

## Rule

The API never executes user-submitted code.

Only the worker can execute code, and only through Docker sandbox containers.