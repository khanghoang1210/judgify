CREATE TABLE users
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    username      VARCHAR(50)  NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role          ENUM ('USER','ADMIN') NOT NULL DEFAULT 'USER',
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uq_users_username UNIQUE (username),
    CONSTRAINT uq_users_email UNIQUE (email)
);

CREATE TABLE problems
(
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    title           VARCHAR(255) NOT NULL,
    slug            VARCHAR(255) NOT NULL,
    description     TEXT,
    difficulty      ENUM ('EASY','MEDIUM','HARD') NOT NULL,
    time_limit_ms   INT          NOT NULL DEFAULT 1000,
    memory_limit_mb INT          NOT NULL DEFAULT 256,
    is_published    BOOLEAN      NOT NULL DEFAULT FALSE,
    created_by      BIGINT,
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uq_problems_slug UNIQUE (slug),
    CONSTRAINT fk_problems_users FOREIGN KEY (created_by) REFERENCES users (id)
);

CREATE INDEX idx_problems_is_published ON problems (is_published);

CREATE TABLE test_cases
(
    id              BIGINT    NOT NULL AUTO_INCREMENT,
    problem_id      BIGINT    NOT NULL,
    input           TEXT      NOT NULL,
    expected_output TEXT      NOT NULL,
    is_sample       BOOLEAN   NOT NULL DEFAULT FALSE,
    order_index     INT,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_test_cases_problems FOREIGN KEY (problem_id) REFERENCES problems (id)
);

CREATE INDEX idx_test_cases_problem_id ON test_cases (problem_id);

CREATE TABLE submissions
(
    id                BIGINT    NOT NULL AUTO_INCREMENT,
    user_id           BIGINT    NOT NULL,
    problem_id        BIGINT    NOT NULL,
    language          ENUM ('PYTHON3','CPP17') NOT NULL,
    source_code       TEXT      NOT NULL,
    status            ENUM ('PENDING','JUDGING','ACCEPTED','WRONG_ANSWER','TIME_LIMIT_EXCEEDED','MEMORY_LIMIT_EXCEEDED','RUNTIME_ERROR','COMPILE_ERROR','SYSTEM_ERROR') NOT NULL DEFAULT 'PENDING',
    error_message     TEXT,
    execution_time_ms INT,
    memory_used_kb    INT,
    created_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    judged_at         TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_submissions_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_submissions_problems FOREIGN KEY (problem_id) REFERENCES problems (id)
);

CREATE INDEX idx_submissions_status ON submissions (status);
CREATE INDEX idx_submissions_user_id ON submissions (user_id);
CREATE INDEX idx_submissions_problem_id ON submissions (problem_id);

CREATE TABLE submission_results
(
    id                BIGINT    NOT NULL AUTO_INCREMENT,
    submission_id     BIGINT    NOT NULL,
    test_case_id      BIGINT    NOT NULL,
    status            ENUM ('PENDING','JUDGING','ACCEPTED','WRONG_ANSWER','TIME_LIMIT_EXCEEDED','MEMORY_LIMIT_EXCEEDED','RUNTIME_ERROR','COMPILE_ERROR','SYSTEM_ERROR') NOT NULL,
    execution_time_ms INT,
    memory_used_kb    INT,
    actual_output     TEXT,
    created_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_submission_results_submissions FOREIGN KEY (submission_id) REFERENCES submissions (id),
    CONSTRAINT fk_submission_results_test_cases FOREIGN KEY (test_case_id) REFERENCES test_cases (id)
);

CREATE INDEX idx_submission_results_submission_id ON submission_results (submission_id);
