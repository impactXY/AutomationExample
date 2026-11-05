# Automation Exercise – UI Test Automation

Automated end-to-end tests for [Automation Exercise](https://automationexercise.com), a demo e-commerce site built for practicing test automation.

The project is built with a focus on maintainability: tests use the Page Object Model, browser setup is centralized, and driver handling is thread-safe so the suite can run in parallel.

# Tech stack

- Java 21
- Selenium WebDriver 4 (browser drivers managed automatically by Selenium Manager)
- TestNG for test execution and assertions
- Maven for build and dependency management

# Test coverage

| Test | Description |
|------|-------------|
| Home page loads | Opens the site and verifies the page title |
| Register and delete user | Signs up a new user with a unique email, fills in the account form, verifies the user is logged in, then deletes the account |

# Design decisions

- **Page Object Model.** Locators and page actions live in page classes; tests describe the flow and the expected results only.
- **Explicit waits only.** All interactions go through `BasePage`, which waits for elements to be visible or clickable. No `Thread.sleep`.
- **`data-qa` selectors preferred.** The site exposes `data-qa` attributes intended for testing, which are more stable than layout-based selectors.
- **Unique test data.** Each run registers a new user with a timestamped email, so tests don't depend on each other or on leftover data.
- **Consent popup handled optionally.** The cookie consent dialog only appears for EU visitors, so it is dismissed if present and ignored otherwise. This keeps tests stable both locally and in CI.

# Running the tests

Requirements: JDK 21, Maven, Google Chrome.

Run the whole suite:

bash
mvn test

Or run a single test class from IntelliJ using the green arrow next to the class name.

# Planned

- CI pipeline with GitHub Actions (run on every push, publish test reports)
- API tests for the site's public endpoints
- Test reports (Allure)
- Parallel execution
- Additional flows: login, product search, cart, checkout
