Project Overview: Page Object Model with ThreadLocal
Introduction:
This project leverages the Page Object Model (POM) design pattern along with ThreadLocal for parallel testing. It aims to enhance test automation efficiency and maintainability.

Key Features:
1)Page Object Model (POM): Each page in the application is represented as a separate class with its associated elements and actions. This modular approach simplifies test maintenance and readability.
2)ThreadLocal: We use ThreadLocal to manage WebDriver instances. This ensures that each test thread has its isolated WebDriver, preventing interference between parallel test executions.
Data Providers:
3)JSON Files: We store test data in JSON files. These files contain structured data that can be easily read and parsed by our test scripts.
4)Excel Files: Additionally, we use Excel files as another data source. Excel provides tabular data organization, making it suitable for storing test data.
5)Writing Tests:
    Create test classes using the POM structure.
    Utilize ThreadLocal to manage WebDriver instances.
    Fetch test data from JSON or Excel files as needed.
