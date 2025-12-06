import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Inventory {

    static TestReports TestReport = new TestReports();



    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        testInventoryWithoutLogin(driver);

        // Login
        login(driver);

        // Run inventory verification
        runInventoryTests(driver);

        // Run multiple add/remove test
        runMultipleAddRemoveTest(driver);
        driver.quit();



        // Print report
        TestReport.print();


    }

    public static void runInventoryTests(WebDriver driver) {

        String className = "Inventory";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {


                List<Product> products = ProductExporter.importProducts();
                for (Product  product : products) {
                    String name = product.getName();
                    String price = product.getPrice();
                    String desc = product.getDescription();
                    String addBtnId = product.getAddButtonId();
                    String removeBtnId = product.getRemoveButtonId();

                // Navigate to inventory page
                driver.get("https://www.saucedemo.com/inventory.html");

                // Wait for product name
                WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'inventory_item_name') and text()='" + name + "']")));


                // Add to cart
                // Click Add to Cart using ID
                driver.findElement(By.id(addBtnId)).click();

                // Check if button changed to Remove using ID
                String buttonText = driver.findElement(By.id(removeBtnId)).getText();
                List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));

                String status1 = "";
                String actual1 ="";
                if (buttonText.equals("Remove") && !cartBadges.isEmpty() && cartBadges.get(0).getText().equals("1")) {
                    status1 = "PASS";
                    actual1 = "Item added to cart";
                } else {
                    status1 = "FAIL";
                    actual1 = "Button: " + buttonText + ", Badge: " + (cartBadges.isEmpty() ? "empty" : cartBadges.get(0).getText());
                }
                TestReport.add(className, "Add to Cart: " + name, status1, "Item added to cart", actual1);

                // Remove from cart
                driver.findElement(By.id(removeBtnId)).click();

                String status2 = "";
                String actual2 ="";
                // Check if button changed back to Add to Cart
                String buttonText2 = driver.findElement(By.id(addBtnId)).getText();
                List<WebElement> cartBadges2 = driver.findElements(By.className("shopping_cart_badge"));

                if (buttonText2.equals("Add to cart") && cartBadges2.isEmpty()) {
                    status2 = "PASS";
                    actual2 = "Item removed from cart";
                } else {
                    status2 = "FAIL";
                    actual2 = "Button: " + buttonText + ", Badge empty: " + cartBadges.isEmpty();
                }
                TestReport.add(className, "Remove from Cart: " + name, status2, "Item removed from cart", actual2);
            }
        } catch (Exception e) {
            System.out.println("Error during inventory tests: " + e.getMessage());
        }
    }

    // Method to add 2 items from the array, then remove them
    public static void runMultipleAddRemoveTest(WebDriver driver) {
        String className = "Inventory";
        String expected = "Add 2 items and remove them successfully";
        String actual = "";
        String status = "";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            // Get products
            List<Product> products = ProductExporter.importProducts();

            // Add first 2 items
            for (int i = 0; i < 2; i++) {
                Product product = products.get(i);
                WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(product.getAddButtonId())));
                addBtn.click();
            }

            // Verify badge
            List<WebElement> badgeAfterAdd = driver.findElements(By.className("shopping_cart_badge"));
            if (!badgeAfterAdd.isEmpty() && badgeAfterAdd.get(0).getText().equals("2")) {
                status = "PASS";
                actual = "2 items added to cart, badge = 2";
            } else {
                status = "FAIL";
                actual = "Badge count incorrect: " + (badgeAfterAdd.isEmpty() ? "0" : badgeAfterAdd.get(0).getText());
            }

            // Remove first 2 items
            for (int i = 0; i < 2; i++) {
                Product product = products.get(i);
                WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(product.getRemoveButtonId())));
                removeBtn.click();
            }

            // Verify badge empty
            List<WebElement> badgeAfterRemove = driver.findElements(By.className("shopping_cart_badge"));
            if (badgeAfterRemove.isEmpty()) {
                actual += " | Items removed, cart empty";
            } else {
                actual += " | Badge still visible after removal";
                status = "FAIL";
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReport.add(className, "Multiple Add/Remove Test", status, expected, actual);
    }

    //Test without Login
    public static void testInventoryWithoutLogin(WebDriver driver) {
        String className = "Inventory";
        String testName = "Access Inventory without login";

        String expected = "Redirect to login page";
        String actual = "";
        String status = "";

        try {
            // Navigate directly to inventory page without logging in
            driver.get("https://www.saucedemo.com/inventory.html");

            // Wait a bit for redirect (can adjust timeout if needed)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains("saucedemo.com"));

            String currentUrl = driver.getCurrentUrl();

            if (!currentUrl.contains("inventory.html")) {
                status = "PASS";
                actual = "Redirected to: " + currentUrl;
            } else {
                status = "FAIL";
                actual = "Still on inventory page: " + currentUrl;
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = "Error: " + e.getMessage();
        }

        TestReport.add(className, testName, status, expected, actual);
    }


    public static void login(WebDriver driver) {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        System.out.println("Logged in successfully");
    }
}
