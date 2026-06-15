# Task 003: Problem APIs
## Goal

Implement basic problem APIs for Judgify.

This task exposes problem list/detail APIs for users and problem management APIs for admins.

## Scope

Module:
- api

Use existing shared entities/repositories from:
- core
## Public APIs

### Implement:

- GET /api/problems
- GET /api/problems/{slug}

Behavior:

- GET /api/problems returns only PUBLISHED problems.
- GET /api/problems/{slug} returns one published problem by slug.
- Do not expose test cases in this task.
- Return DTOs, not JPA entities.
## Admin APIs

### Implement:

- POST /api/admin/problems
- PUT /api/admin/problems/{id}
- DELETE /api/admin/problems/{id}

Behavior:

- Create problem
- Update problem
- Delete problem
- Admin authorization can be left as TODO for now.

## Service Layer
Create a service in judgify-api:

- ProblemService

Responsibilities:

- create problem
- update problem
- delete problem
- get published problem list
- get published problem detail

## DTOs
Create DTOs in core module:
- ProblemCreateRequest
- ProblemUpdateRequest
- ProblemResponse

You can add more DTOs as needed.
## Error Handling

Handle cases:

- problem not found
- duplicate slug
- invalid request

Use simple exceptions for now if global error handling is not ready.

## Open API
- Create a new OpenAPI spec for problem APIs with swagger-ui.
- Use the existing core entities/repositories/dtos from:
  - core
## Not Included

### Do not implement:

- auth
- admin security
- test case APIs
- submission APIs
- judge logic
- worker logic
- Docker sandbox
### Done When
- Problem APIs compile
- ./gradlew clean build passes
- Public APIs only return published problems
- Admin APIs can create/update/delete problems
- Controllers return DTOs, not entities