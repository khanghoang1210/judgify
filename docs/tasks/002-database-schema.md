# Task 002: Database Schema

## Goal

Design and implement the initial database schema for Judgify using Flyway migrations.

## Prerequisites

- Task 001 completed (project bootstrapped)
- MySQL container running via docker-compose

## Entities

### users

| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | PK, auto-increment |
| username | VARCHAR(50) | unique, not null |
| email | VARCHAR(255) | unique, not null |
| password_hash | VARCHAR(255) | not null |
| role | ENUM('USER', 'ADMIN') | default USER |
| created_at | TIMESTAMP | not null |
| updated_at | TIMESTAMP | not null |

### problems

| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | PK, auto-increment |
| title | VARCHAR(255) | not null |
| slug | VARCHAR(255) | unique, not null |
| description | TEXT | markdown content |
| difficulty | ENUM('EASY', 'MEDIUM', 'HARD') | not null |
| time_limit_ms | INT | default 1000 |
| memory_limit_mb | INT | default 256 |
| is_published | BOOLEAN | default false |
| created_by | BIGINT | FK -> users.id |
| created_at | TIMESTAMP | not null |
| updated_at | TIMESTAMP | not null |

### test_cases

| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | PK, auto-increment |
| problem_id | BIGINT | FK -> problems.id, not null |
| input | TEXT | not null |
| expected_output | TEXT | not null |
| is_sample | BOOLEAN | default false (sample = visible to users) |
| order_index | INT | for ordering test cases |
| created_at | TIMESTAMP | not null |

### submissions

| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | PK, auto-increment |
| user_id | BIGINT | FK -> users.id, not null |
| problem_id | BIGINT | FK -> problems.id, not null |
| language | ENUM('PYTHON3', 'CPP17') | not null |
| source_code | TEXT | not null |
| status | ENUM(...) | see status list below |
| error_message | TEXT | nullable, for compile/system errors |
| execution_time_ms | INT | nullable |
| memory_used_kb | INT | nullable |
| created_at | TIMESTAMP | not null |
| judged_at | TIMESTAMP | nullable |

**Submission status values:**
- PENDING
- JUDGING
- ACCEPTED
- WRONG_ANSWER
- TIME_LIMIT_EXCEEDED
- MEMORY_LIMIT_EXCEEDED
- RUNTIME_ERROR
- COMPILE_ERROR
- SYSTEM_ERROR

### submission_results

| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | PK, auto-increment |
| submission_id | BIGINT | FK -> submissions.id, not null |
| test_case_id | BIGINT | FK -> test_cases.id, not null |
| status | ENUM(...) | same as submission status |
| execution_time_ms | INT | nullable |
| memory_used_kb | INT | nullable |
| actual_output | TEXT | nullable, hidden from normal users |
| created_at | TIMESTAMP | not null |

## Steps

### Step 1: Create Flyway migration directory

Create `core/src/main/resources/db/migration/` directory.

### Step 2: Create V1 migration 
Create all tables in a single migration file `V1__initial_schema.sql` for simplicity.

### Step 3: Create JPA entities in core module

Create entity classes:
- `User`
- `Problem`
- `TestCase`
- `Submission`
- `SubmissionResult`

### Step 4: Create enums in core module

- `Role` (USER, ADMIN)
- `Difficulty` (EASY, MEDIUM, HARD)
- `Language` (PYTHON3, CPP17)
- `SubmissionStatus` (PENDING, JUDGING, ACCEPTED, etc.)

### Step 5: Create repositories in core module

- `UserRepository`
- `ProblemRepository`
- `TestCaseRepository`
- `SubmissionRepository`
- `SubmissionResultRepository`

### Step 6: Verify migrations

Run `./gradlew clean build` and start api to verify Flyway applies migrations correctly.

## Conventions

- Table names: snake_case, plural (e.g., `users`, `test_cases`)
- Column names: snake_case (e.g., `created_at`, `problem_id`)
- Foreign key naming: `fk_{table}_{referenced_table}`
- Index naming: `idx_{table}_{column}`

## Not Included

- Authentication logic
- API endpoints
- Service layer business logic

## Done When

- All migrations apply without errors
- JPA entities map correctly to tables
- `./gradlew clean build` passes
- API boots and connects to database
