import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class Overview {

    static List<Product> products = ProductExporter.importProducts();

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            // Run each test case
            TC_001(driver);
            driver.quit();
            driver = new EdgeDriver();
            TC_002(driver);
            driver.quit();
            driver = new EdgeDriver();
            TC_003(driver);
            driver.quit();
            driver = new EdgeDriver();
            TC_004(driver);

            System.out.println("\nALL TESTS COMPLETED!");

        } finally {
            Thread.sleep(2000);
            driver.quit();
            TestReports.print();
        }
    }

    public static WebElement findElement(WebDriver driver, String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public static void fillInfos(WebDriver driver, String firstName, String lastName, String zip) {
        findElement(driver, "//input[@id='first-name']").sendKeys(firstName);
        findElement(driver, "//input[@id='last-name']").sendKeys(lastName);
        findElement(driver, "//input[@id='postal-code']").sendKeys(zip);
    }

    public static void navigateTillCheckout(WebDriver driver, Product product) {
        // Click the Add to Cart button
        findElement(driver, "//button[@id='" + product.getAddButtonId() + "']").click();

        // Go to cart
        findElement(driver, "//a[@class='shopping_cart_link']").click();

        // Checkout
        findElement(driver, "//button[@id='checkout']").click();

        // Fill user info
        fillInfos(driver, "Abdelrahman", "Mohamed", "4444");
        findElement(driver, "//input[@id='continue']").click();
    }

    public static void login(WebDriver driver) {
        driver.get("https://www.saucedemo.com/");
        findElement(driver, "//input[@id='user-name']").sendKeys("standard_user");
        findElement(driver, "//input[@id='password']").sendKeys("secret_sauce");
        findElement(driver, "//input[@id='login-button']").click();
        System.out.println("Logged in successfully");
    }

    // TC_001: Add first product and checkout
    public static void TC_001(WebDriver driver) {
        String testName = "TC_001";
        System.out.println("\n=== Running TC_001 ===");

        String expected = "https://www.saucedemo.com/checkout-complete.html";
        String actual = "";
        String status = "";

        try {
            login(driver);
            navigateTillCheckout(driver, products.get(0));
            findElement(driver, "//button[@id='finish']").click();

            actual = driver.getCurrentUrl();
            status = actual.contains(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReports.add("Overview", testName, status, expected, actual);
    }

    // TC_002: Add two products and checkout
    public static void TC_002(WebDriver driver) {
        String testName = "TC_002";
        System.out.println("\n=== Running TC_002 ===");

        String expected = "https://www.saucedemo.com/checkout-complete.html";
        String actual = "";
        String status = "";

        try {
            login(driver);

            // Add first product
            findElement(driver, "//button[@id='" + products.get(0).getAddButtonId() + "']").click();

            // Add second product and checkout
            navigateTillCheckout(driver, products.get(1));

            findElement(driver, "//button[@id='finish']").click();
            actual = driver.getCurrentUrl();

            status = actual.contains(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReports.add("Overview", testName, status, expected, actual);
    }

    // TC_003: Cancel checkout
    public static void TC_003(WebDriver driver) {
        String testName = "TC_003";
        System.out.println("\n=== Running TC_003 ===");

        String expected = "https://www.saucedemo.com/cart.html";
        String actual = "";
        String status = "";

        try {
            login(driver);

            // Navigate to checkout for first product
            navigateTillCheckout(driver, products.get(0));

            // Click Cancel
            findElement(driver, "//button[@id='cancel']").click();

            actual = driver.getCurrentUrl();
            status = actual.contains(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReports.add("Overview", testName, status, expected, actual);
    }

    // TC_004: Compare payment info between two checkouts
    public static void TC_004(WebDriver driver) {
        String testName = "TC_004";
        System.out.println("\n=== Running TC_004 ===");

        String expected = "Different";
        String actual = "";
        String status = "";

        try {
            login(driver);

            // First checkout
            navigateTillCheckout(driver, products.get(0));
            String firstInfo = findElement(driver, "//div[@class='summary_value_label']").getText();
            findElement(driver, "//button[@id='finish']").click();
            findElement(driver, "//button[@id='back-to-products']").click();

            // Second checkout with another product
            navigateTillCheckout(driver, products.get(1));
            String secondInfo = findElement(driver, "//div[@class='summary_value_label']").getText();

            actual = firstInfo.equals(secondInfo) ? "Same" : "Different";
            status = actual.equals(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReports.add("Overview", testName, status, expected, actual);
    }

}
