# Judge Engine

## Submission Flow

1. API creates a submission with PENDING status.
2. Worker picks a PENDING submission.
3. Worker marks it as JUDGING.
4. Worker creates a temporary workspace.
5. Worker writes source code to file.
6. Worker compiles code if needed.
7. Worker runs each test case inside Docker.
8. Worker compares actual output with expected output.
9. Worker saves per-test results.
10. Worker updates final submission status.

## MVP Languages

- python3
- cpp17

## Output Checker

Start with normalized output comparison:

- normalize CRLF to LF
- trim final whitespace
- trim trailing whitespace per line

## Hidden Test Case Rule

For hidden test cases, normal users must not see:

- input
- expected output
- actual output