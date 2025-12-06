import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;


public class ProductPage {

    static TestReports TestReport = new TestReports();

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Login
        login(driver);

        // Run product verification
        runProductTests(driver);

        // Print report
        TestReport.print();

        driver.quit();
    }

    // --- RUN TESTS FOR ALL PRODUCTS ---
    public static void runProductTests(WebDriver driver) {
        String className = "ProductPage";

        List<Product> products = ProductExporter.importProducts();
        for (Product  product : products) {
            String name = product.getName();
            String price = product.getPrice();
            String desc = product.getDescription();


            try {
                //  Verify product details
                verifyProductDetails(driver, className, name, price, desc);

                // Test add and remove from cart
                testAddRemoveCart(driver, className, name);

            } catch (Exception e) {
                System.out.println("Error testing product '" + name + "': " + e.getMessage());
            }
        }
    }

    // Simple login method
    public static void login(WebDriver driver) {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        System.out.println("Logged in successfully");
    }




    // --- VERIFY PRODUCT DETAILS ---
    public static void verifyProductDetails(WebDriver driver, String className, String name,
                                            String expectedPrice, String expectedDesc) {


        // Open product details
        WebElement product = driver.findElement(
                By.xpath("//div[@data-test='inventory-item-name' and text()='" + name + "']"));
        product.click();


        // Title check
        String actualTitle = driver.findElement(By.className("inventory_details_name")).getText();
        TestReport.add(className, "Title Verification: " + name,
                actualTitle.equals(name) ? "PASS" : "FAIL", name, actualTitle);

        // Description check
        String actualDesc = driver.findElement(By.className("inventory_details_desc")).getText();
        TestReport.add(className, "Description Verification: " + name,
                actualDesc.equals(expectedDesc) ? "PASS" : "FAIL", expectedDesc, actualDesc);

        // Price check
        String actualPrice = driver.findElement(By.className("inventory_details_price")).getText();
        TestReport.add(className, "Price Verification: " + name,
                actualPrice.equals(expectedPrice) ? "PASS" : "FAIL", expectedPrice, actualPrice);

        driver.findElement(By.id("back-to-products")).click();
    }

    // --- ADD AND REMOVE PRODUCT FROM CART ---
    public static void testAddRemoveCart(WebDriver driver, String className, String name) {

        WebElement product = driver.findElement(
                By.xpath("//div[@data-test='inventory-item-name' and text()='" + name + "']"));
        product.click();

        // Add to cart

        String expected = "Item added to cart";
        String actual = "";
        String status = "";
        WebElement add = driver.findElement(By.id("add-to-cart"));
        add.click();

        WebElement remove = driver.findElement(By.id("remove"));
        WebElement badge = driver.findElement(By.className("shopping_cart_badge"));

        if (remove.isDisplayed() && badge.getText().equals("1")) {
            status = "PASS";
            actual = "Item added and badge = " + badge.getText();

        } else {
            status = "FAIL";
            actual = "Add to cart failed";
        }

        TestReport.add(className,"Add to cart", status, expected, actual);
        // Remove from cart
        String expected1 = "Product removed from cart";
        String actual1 = "";
        String status1 = "";


        driver.findElement(By.id("remove")).click();

        List<WebElement> badge1 = driver.findElements(By.className("shopping_cart_badge"));
        if (badge1.isEmpty()) {
            status1 = "PASS";
            actual1 = "Cart empty";
        } else {
            status1 = "FAIL";
            actual1 = "Cart badge still visible";
        }
        TestReport.add(className,"Removed from cart", status1, expected1, actual1);

        // Back to inventory
        driver.findElement(By.id("back-to-products")).click();}

}