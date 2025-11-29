# NHA-247

Project Planning for SwagLabs Testing Project
1. Project Overview
•	Project Name: SwagLabs Testing Project + Mini API Testing Project
•	Objective: To ensure the functionality and reliability of the SwagLabs e-commerce platform through manual and automated testing. 
To verify the functionality, reliability, and performance of an API by executing automated and/or manual API tests.
•	Scope:
o	Functional testing of login, inventory, product pages, sorting, and cart operations.
o	Validation of product details (name, price, description).
o	Verification of cart behavior (add, remove, persistence).
o	Verification of checkout information.
o	Testing key endpoints (GET, POST, PUT, DELETE) of the chosen API.
o	Validating responses: status codes, payload structure, data correctness.
o	No UI/UX redesign or database integration included.
________________________________________
2. Project Goals
1.	Identify and document defects in the SwagLabs site through manual testing.
2.	Automate repetitive functional tests to save time and ensure consistency.
3.	Ensure critical user flows (login, product selection, cart operations) work correctly.
4.	Provide clear test reports and documentation.
5.	Ensure all API endpoints behave as expected.
________________________________________
3. Testing Approach
1.	Manual Testing:
o	Exploratory testing of the website.
o	Step-by-step verification of product pages, cart, and checkout flows.
o	Recording test cases, results, and any observed defects or bugs.
2.	Automation Testing:
o	Using Selenium WebDriver with Java to automate key flows:
o	Login verification
o	Inventory and sort verification
o	Product details validation (title, description, price, image)
o	Cart functionality (add, remove, persistence)
o	Checkout information/overview validation
o	Automated test reports generated for each run.
3.  API Testing 
This project tests CRUD operations on the GoRest API using Postman.
Endpoints Covered:
-   POST /users — create new user
-   GET /users/{id} — retrieve user details
-   PUT /users/{id} — full update
-   PATCH /users/{id} — partial update
-   DELETE /users/{id} — delete user

Test Flow:
1.  Create a user and validate response.
2.  Get created user and verify fields.
3.  Update user using PUT.
4.  Patch user status using PATCH.
5.  Delete user.
6.  Validate user is deleted.
________________________________________
4. Timeline / Milestones
Sprint 1 – Manual Testing
- Exploratory Testing.	
- Manual testing of login, inventory, product pages, and cart operations, checkout information/overview.	
- Document test cases, expected results, actual result, and observed defects
  
Sprint 2 – Automation Testing
- Set up automation framework (Java, Selenium WebDriver)
- Implement automated test cases for login, inventory, product pages, and cart operations, checkout information/overview.	
- Generate automated test reports
  
Sprint 3 – API Testing
- Identify API endpoints and create test scenarios
- Perform manual API testing using Postman
	
________________________________________
5. Deliverables
•	Manual test case documentation and results.
•	Selenium automation scripts for major functional tests.
•	Test execution reports showing pass/fail status for automated tests.
________________________________________
6. Resources
•	Software: Java JDK, Selenium WebDriver, Edge/Chrome/Firefox browser, Maven, Postman
•	Hardware: Standard PC with internet access.
•	Human Resources: Testers (Bug busters team).
________________________________________
7. Success Criteria
•	Manual test results confirm no major defects in core flows.
•	Automated tests run reliably and validate all critical functionality.
•	Test reports and documentation are clear, complete, and reproducible.
________________________________________
Notes:
-   Use environment variables for token ,url and userId. Token: (c6b9c604e82d3ea1551dd051dbe3912fa760c2425261f000e6c71c2ef7d7236c)
-   Check negative cases: invalid token, invalid email, missing fields.

