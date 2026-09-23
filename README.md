## **Comprehensive Test Automation Framework**

**Technical Stack & Architecture:**

Engineered a robust, enterprise-grade test automation framework leveraging **Selenium 4.40.0** for web browser automation, paired with **TestNG 7.11.0** as the testing framework of choice. The framework is built on industry-standard design patterns and best practices to ensure scalability, maintainability, and efficiency across testing operations.

**Design Patterns & Architecture:**

- **Page Object Model (POM)**: Implemented a scalable page object architecture that encapsulates web element interactions and business logic, ensuring clean separation of concerns and significantly improving code maintainability and reusability across test cases.
- **Factory Design Pattern**: Utilized factory pattern for intelligent driver instantiation and browser/environment management, enabling dynamic configuration and seamless switching between different test environments and browser types.

**Test Execution & Parallel Processing:**

- **Parallel Test Execution**: Leveraged TestNG's native parallel execution capabilities to run multiple test cases concurrently, significantly reducing total test execution time and improving continuous integration efficiency.
- **TestNG XML Configuration**: Configured suite-based test execution through TestNG XML files with global parameters, allowing flexible test grouping, prioritization, and selective test execution based on test groups and dependencies.

**Data-Driven Testing:**

- **TestNG DataProvider**: Implemented DataProvider annotations for parameterized testing, enabling data-driven test scenarios with multiple data inputs.
- **HashMap Integration**: Utilized HashMap data structures for efficient test data management and dynamic parameter handling, providing flexible and reusable test data repositories.

**Reporting & Diagnostics:**

- **ExtentReports Integration (v5.1.2)**: Integrated comprehensive ExtentReports for detailed, interactive HTML test execution reports with rich visualizations, historical trends, and dashboard analytics.
- **Screenshot Capture**: Implemented automated screenshot capture on test failures, providing visual evidence for debugging and defect analysis.

**Resilience & Reliability:**

- **TestNG Listeners**: Developed custom TestNG listeners for event-driven test monitoring, logging, and real-time status tracking throughout the test execution lifecycle.
- **IRetryAnalyzer**: Implemented intelligent retry mechanism using IRetryAnalyzer to automatically re-execute flaky tests, improving test stability and reducing false-negative failures due to transient issues.

**Build & Deployment Automation:**

- **Maven Build System**: Configured Maven for project dependency management, build automation, and plugin orchestration using Maven Surefire Plugin (v3.5.6) for streamlined test execution.
- **Maven Profiles**: Created multiple Maven profiles (All, Groups) enabling dynamic test suite selection and environment-specific configurations through command-line parameters.

**Additional Technologies:**

- **Selenium WebDriver**: Latest Selenium 4 for cross-browser automation
- **Cucumber (v7.34.3)**: BDD framework integration for behavior-driven test scenarios
- **Commons-IO (v2.21.0)**: File I/O operations for test data and report management

---

## Selenium Grid with Docker

The project includes a self-contained Selenium Grid for concurrent Chrome, Edge, and Firefox execution. Each browser has a dedicated node and the Grid TestNG suite starts all three browser runs in parallel.

1. Start Docker Desktop, then bring up the Grid from the project root:

   ```bash
   docker compose up -d
   ```

2. Wait until the Grid shows all three nodes at [http://localhost:4444/ui](http://localhost:4444/ui).

3. Run the parallel cross-browser suite:

   ```bash
   mvn clean test -PGrid
   ```

The Grid endpoint defaults to `http://localhost:4444`. Override it for a remote Grid with `-Dgrid.url=http://host:4444`. Set `-Dheadless=false` if visible browser sessions are required.

To add capacity for more simultaneous tests, scale a node before running the suite, for example:

```bash
docker compose up -d --scale chrome=2 --scale edge=2 --scale firefox=2
```

Stop the Grid when finished:

```bash
docker compose down
```

