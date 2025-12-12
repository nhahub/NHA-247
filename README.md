# NHA-247
---

## 📌 **Project Overview**

This repository contains a complete **manual + automated testing project** designed to validate the functionality, reliability, and stability of the **SwagLabs e-commerce demo application** along with a **mini API testing module using GoRest**.

The project covers full UI test automation using **Java + Selenium + TestNG + POM**, and API validation using **Postman** for CRUD operations.

It follows clean architecture principles, modular design, and industry-standard testing practices.

---

## ✨ **Key Features**

### 🔍 **Manual Testing**

* Exploratory testing of SwagLabs user flows
* Verification of product pages, cart behavior, and checkout
* Documentation of defects, observations, and expected vs actual results

### 🤖 **Web Automation Testing**

* Login validation
* Product inventory validation
* Sorting checks
* Product detail validation (name, price, description)
* Cart operations (add / remove / persistence)
* Checkout info + overview validation
* Test execution with TestNG
* Allure-ready reporting structure
* Centralized setup using BaseClass

### 🌐 **API Testing (GoRest API)**

* POST – create user
* GET – retrieve user
* PUT – full update
* PATCH – partial update
* DELETE – remove user
* Full validation of:

  * status codes
  * payload structure
  * response data correctness

---

## 🧰 **Technologies Used**

* **Java**
* **Selenium WebDriver**
* **TestNG**
* **Page Object Model (POM)**
* **Maven**
* **Postman**
* **Log4j2**
* **JSON test data**
* **Git / GitHub**

---

## 📁 **Project Structure**

```
├── src
│   └── main
│       └── java
│           └── Pages
│               ├── CartPage.java
│               ├── CheckoutPage.java
│               ├── InventoryPage.java
│               ├── LoggerClass.java
│               ├── LoginPage.java
│               ├── Logout.java
│               ├── OverviewPage.java
│               ├── ProductPage.java
│               └── SortingPage.java
│       └── resources
│           └── log4j2.xml
│
├── src
│   └── test
│       └── java
│           └── tests
│               ├── BaseClass.java
│               ├── CartTest.java
│               ├── CheckoutInfoTest.java
│               ├── InventoryTest.java
│               ├── LoginTest.java
│               ├── LogoutTest.java
│               ├── OverviewTest.java
│               ├── ProductPageTest.java
│               └── SortingTest.java
│       └── resources
│           └── testdata
│               ├── CheckoutData.json
│               └── ProductList.json
│
├── target/
├── pom.xml
├── testng.xml
└── .gitignore
 
```

---

## 🧪 **Testing Approach**

### **📌 Manual Testing**

* Performed exploratory testing on core user flows
* Validated login, product listing, product details, sorting, cart & checkout
* Logged findings, defects, and detailed steps

### **🤖 Automation Testing**

Using **Selenium + Java + TestNG**:

* Automated key end-to-end flows
* Implemented Page Object Model (POM)
* Used DataProvider for multiple login scenarios
* Added logs using `log.info()`
* Generated structured test reports

---

### ▶️ Running the Test Suite

You can execute all tests using Maven.
The framework is already configured to run through **testng.xml**.

#### **✔️ Run All Tests**

```
mvn clean test
```

---

### 📊 Generating & Viewing the Allure Report

You can generate and open the Allure report using Maven’s Allure plugin.

#### **✔️ Run Tests + Automatically Open Allure Report**

```
mvn allure:serve
```

This command will:

* Execute all tests
* Generate the Allure report
* Launch it automatically in the browser

---

### 🔄 Alternative: Run Tests Only

If you want to run tests without opening the report:

```
mvn test
```

---

### **🌐 API Testing (GoRest via Postman)**

Endpoints tested:

| Method | Endpoint      |
| ------ | ------------- |
| POST   | `/users`      |
| GET    | `/users/{id}` |
| PUT    | `/users/{id}` |
| PATCH  | `/users/{id}` |
| DELETE | `/users/{id}` |

**API Test Flow:**
1️⃣ Create user → validate response
2️⃣ Fetch created user → verify fields
3️⃣ Update user (PUT)
4️⃣ Partially update (PATCH)
5️⃣ Delete user
6️⃣ Confirm deletion

---

## 📅 **Project Timeline**

### **Sprint 1 – Manual Testing**

* Exploratory testing
* Coverage of login, inventory, product details, cart, checkout
* Documentation of test cases and defects

### **Sprint 2 – Automation Testing**

* Built automation framework
* Implemented POM structure
* Automated core functional flows
* Added logs + screenshots
* Generated execution reports

### **Sprint 3 – API Testing**

* Identified endpoints
* Created scenarios
* Performed CRUD tests in Postman
* Documented responses and validations

---

## 📦 **Deliverables**

* ✔️ Manual test cases & defect reports
* ✔️ Selenium automated test suite
* ✔️ JSON-based test data
* ✔️ Test execution reports
* ✔️ Postman collection for API tests

---

## 📚 **Resources Needed**

* **Java JDK**
* **Selenium WebDriver**
* **Chrome/Firefox/Edge**
* **Maven**
* **Postman**
* **Standard PC / Laptop with internet**
* **Testers: NHA-247 Bug Busters Team**

---

## 🏁 **Success Criteria**

* All core SwagLabs flows pass manual & automated testing
* API CRUD operations return correct status & data
* No major functional blockers
* Clear, reproducible test documentation and reports

---

## 🔐 **Environment Variables (API Testing)**

| Variable | Description              |
| -------- | ------------------------ |
| token    | API authentication token |
| url      | Base API URL             |
| userID   | Dynamic user ID          |

Example Token (Do NOT expose in production):
`c6b9c604e82d3ea1551dd051dbe3912fa760c2425261f000e6c71c2ef7d7236c`

API Docs:
[https://documenter.getpostman.com/view/34803337/2sB3WyKGKJ](https://documenter.getpostman.com/view/34803337/2sB3WyKGKJ)

---

## 🐞 **About This Project**

This repository was developed as part of the **NHA-247 Web & API Testing Program**.
It demonstrates foundational QA engineering skills in:

* UI Manual Testing
* UI Automation
* API Testing
* Framework Design
* Reporting & Documentation

--
