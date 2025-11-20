


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class ProductPage {

    static TestReports TestReport = new TestReports();
    public static void ProductPage() throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            // Login first
            login(driver);

            // Run each test case
            TC_001(driver);
            TC_002(driver);
            TC_003(driver);
            TC_004(driver);
            TC_005(driver);
            TC_006(driver);
            TC_007(driver);
            TC_008(driver);
            TC_010(driver);
            TC_011(driver);

            System.out.println("\n ALL TESTS COMPLETED!");

        } finally {
            Thread.sleep(2000);
            driver.quit();

        }
    }

    // Login helper method
    public static void login(WebDriver driver) throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        //Thread.sleep(1000);

        WebElement user = driver.findElement(By.id("user-name"));
        WebElement pwd = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        user.sendKeys("standard_user");
        pwd.sendKeys("secret_sauce");
        loginBtn.click();

        // Thread.sleep(1000);
        System.out.println(" Logged in successfully");
    }

    public static WebElement getProductContainer(WebDriver driver, String productName) {

        WebElement nameEl = driver.findElement(
                By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName + "']")
        );

        return nameEl.findElement(By.xpath("./ancestor::div[@data-test='inventory-item']"));
    }

    // Helper method to get price by product name using data attributes
    public static String getProductPrice(WebDriver driver, String productName) {
        WebElement container = getProductContainer(driver, productName);
        return container.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText();
    }

    // Helper method to get product description
    public static String getProductDescription(WebDriver driver, String productName) {
        WebElement container = getProductContainer(driver, productName);
        return container.findElement(By.cssSelector("[data-test='inventory-item-desc']")).getText();
    }


    // TC_001: Inventory Item Page Load Verification
    public static void TC_001(WebDriver driver) {
        String testName = "TC_001";
        System.out.println("\n=== Running TC_001 ===");

        String expected = "https://www.saucedemo.com/inventory-item.html?id=4";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");
            driver.findElement(By.id("item_4_title_link")).click();
            actual = driver.getCurrentUrl();

            status = actual.contains("inventory-item.html?id=4") ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }


        TestReport.add("ProductPage",testName, status, expected, actual);
    }

    // TC_002: Product Image Load Verification
    public static void TC_002(WebDriver driver) {
        String testName = "TC_002";
        System.out.println("\n=== Running TC_002 ===");

        String expected = "Product image loaded";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=4");
            WebElement img = driver.findElement(By.className("inventory_details_img"));

            if (img.isDisplayed() && img.getAttribute("src") != null && !img.getAttribute("src").isEmpty()) {
                status = "PASS";
                actual = img.getAttribute("src");
            } else {
                status = "FAIL";
                actual = "Image not displayed or src empty";
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }
    // TC_003: Product Title Verification
    public static void TC_003(WebDriver driver) {
        String testName = "TC_003";
        System.out.println("\n=== Running TC_003 ===");

        String productName = "Sauce Labs Backpack";
        String expected = productName;
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");
            actual = driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName + "']")).getText();
            driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName + "']")).click();
            actual = driver.findElement(By.className("inventory_details_name")).getText();

            status = actual.equals(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }
    // TC_004: Product Description Verification
    public static void TC_004(WebDriver driver) {
        String testName = "TC_004";
        System.out.println("\n=== Running TC_004 ===");

        String productName = "Sauce Labs Backpack";
        String expected = "";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");
            expected = getProductDescription(driver, productName);

            driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName + "']")).click();
            actual = driver.findElement(By.className("inventory_details_desc")).getText();

            status = actual.equals(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }

    // TC_005: Product Price Format Verification
    public static void TC_005(WebDriver driver) {
        String testName = "TC_005";
        System.out.println("\n=== Running TC_005 ===");

        String expected = "Price format $xx.xx";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=4");
            actual = driver.findElement(By.className("inventory_details_price")).getText();

            boolean hasDollarSign = actual.startsWith("$");
            boolean hasDecimal = actual.contains(".");
            boolean hasTwoDecimals = actual.matches(".*\\.[0-9]{2}");
            status = (hasDollarSign && hasDecimal && hasTwoDecimals) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }

    // TC_006: Product price Verification
    public static void TC_006(WebDriver driver) {
        String testName = "TC_006";
        System.out.println("\n=== Running TC_006 ===");

        String productName = "Sauce Labs Backpack";
        String expected = getProductPrice(driver, productName);
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");
            driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName + "']")).click();
            actual = driver.findElement(By.className("inventory_details_price")).getText();

            status = actual.equals(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }

    //TC_007: Add Product to Cart Functionality
    public static void TC_007(WebDriver driver) {
        String testName = "TC_007";
        System.out.println("\n=== Running TC_007 ===");

        String expected = "Item added to cart";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=4");
            WebElement add = driver.findElement(By.id("add-to-cart"));
            add.click();

            WebElement remove = driver.findElement(By.id("remove"));
            WebElement badge = driver.findElement(By.className("shopping_cart_badge"));

            if (remove.isDisplayed() && badge.getText().equals("1")) {
                status = "PASS";
                actual = "Item added and badge = " + badge.getText();
                remove.click(); // Reset
            } else {
                status = "FAIL";
                actual = "Add to cart failed";
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }

    //TC_008: Remove Product from Cart Functionality
    public static void TC_008(WebDriver driver) {
        String testName = "TC_008";
        System.out.println("\n=== Running TC_008 ===");

        String expected = "Product removed from cart";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=4");
            driver.findElement(By.id("add-to-cart")).click();
            driver.findElement(By.id("remove")).click();

            List<WebElement> badge = driver.findElements(By.className("shopping_cart_badge"));
            if (badge.isEmpty()) {
                status = "PASS";
                actual = "Cart empty";
            } else {
                status = "FAIL";
                actual = "Cart badge still visible";
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }

    //TC_010: Add Same Product from Inventory Page Verification
    public static void TC_010(WebDriver driver) {
        String testName = "TC_010";
        System.out.println("\n=== Running TC_010 ===");

        String expected = "Inventory shows Remove";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=4");
            driver.findElement(By.id("add-to-cart")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("back-to-products")).click();
            WebElement btn = driver.findElement(By.id("remove-sauce-labs-backpack"));

            actual = btn.getText();
            status = actual.equals("Remove") ? "PASS" : "FAIL";
            btn.click(); // Reset

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }
    //TC_011: Verification of Cart Persistence After Page Refresh
    public static void TC_011(WebDriver driver) {
        String testName = "TC_011";
        System.out.println("\n=== Running TC_011 ===");

        String expected = "Cart count persistent after refresh";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=4");
            driver.findElement(By.id("add-to-cart")).click();
            String before = driver.findElement(By.className("shopping_cart_badge")).getText();

            driver.navigate().refresh();
            String after = driver.findElement(By.className("shopping_cart_badge")).getText();

            actual = "Before: " + before + " After: " + after;
            status = before.equals(after) ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add("ProductPage",testName, status, expected, actual);
    }

}






