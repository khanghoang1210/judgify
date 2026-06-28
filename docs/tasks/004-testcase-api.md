# Task 004: Test Case APIs

## Goal

Implement test case management APIs for Judgify.

This task allows admins to create, update, list, and delete test cases for problems.

## Scope

Module:

* api

Use existing shared entities/repositories from:

* core

## Admin APIs

### Implement:

* GET /api/admin/problems/{problemId}/test-cases
* POST /api/admin/problems/{problemId}/test-cases
* PUT /api/admin/test-cases/{id}
* DELETE /api/admin/test-cases/{id}

Behavior:

* GET returns all test cases of a problem ordered by `orderIndex`.
* POST creates a new test case for a problem.
* PUT updates an existing test case.
* DELETE deletes an existing test case.
* Admin authorization can be left as TODO for now.

## Service Layer

Create a service in api module:

* TestCaseService

Responsibilities:

* get test cases by problem id
* create test case
* update test case
* delete test case
* validate problem exists before creating test case

## DTOs

Create DTOs in core module:

* TestCaseCreateRequest
* TestCaseUpdateRequest
* TestCaseResponse

You can add more DTOs as needed.

## Validation

Handle validation for:

* input is required
* expectedOutput is required
* orderIndex must be greater than or equal to 0
* problem must exist before creating test case

## Error Handling

Handle cases:

* problem not found
* test case not found
* invalid request

Use simple exceptions for now if global error handling is not ready.

## Open API

* Add OpenAPI documentation for test case admin APIs.
* Expose request/response schemas in swagger-ui.
* Use the existing core entities/repositories/dtos from:

    * core

## Important Rule

Hidden test cases must only be exposed through admin APIs.

Do not add test cases to public problem APIs in this task.

## Not Included

### Do not implement:

* auth
* admin security
* public test case APIs
* submission APIs
* judge logic
* worker logic
* Docker sandbox

## Done When

* Test case APIs compile
* ./gradlew clean build passes
* Admin APIs can create/update/delete/list test cases
* Test cases are associated with a problem
* Public problem APIs still do not expose test cases
* Controllers return DTOs, not entities
