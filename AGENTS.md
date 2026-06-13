# Judgify Agent Context

Judgify is a coding problem platform backend.

Frontend already exists.

Main development workflow:

1. Read docs.
2. Plan briefly.
3. Implement one small task.
4. Run build/tests.
5. Review diff.
6. Fix only relevant issues.

Do not:
- execute user code in the API process
- expose hidden test cases
- introduce microservices too early
- refactor unrelated code
- implement all features in one task

## General Engineering Rules

* Restate the requirement before coding.
* Ask clarifying questions when ambiguity affects core behavior.
* Record assumptions when information is missing.
* Inspect only files relevant to the task.
* Follow existing code patterns before introducing new ones.
* Make small, reviewable changes.
* Do not refactor unrelated code.
* Do not invent APIs, fields, configs, modules, or product behavior.
* Do not claim verification unless it was actually checked.
* Do not commit or push without explicit permission.