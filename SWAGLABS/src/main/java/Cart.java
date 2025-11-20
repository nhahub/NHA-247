import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Cart {
    static WebDriver driver = new EdgeDriver();
    static TestReports TestReport = new TestReports();

    public static void Cart() {


        // Run all test methods
        TC_001();
        TC_002();
        TC_003();
        TC_004();
        TC_005();
        TC_006();
        TC_007();
        TC_008();
        TC_010();
        TC_011();

        driver.quit();

    }

    public static void login() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
    }

    // 1) add to cart
    public static void TC_001() {
        String name;
        String status;
        String expected;
        String actual;
        System.out.println("=== Test Case 1: Add Sauce Labs Backpack to Cart ===");
        driver.navigate().to("https://www.saucedemo.com/");
        name ="TC_001";
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        expected = "1";
        actual = driver.findElement(By.className("shopping_cart_badge")).getText();
        status = expected.equals(actual) ? "PASS" : "FAIL";
        System.out.println("Add to Cart Test: " + status);
        TestReport.add("Cart",name, status, "Add to cart", actual);
    }

    //2 remove
    public static void TC_002() {
        System.out.println("=== Test Case 2: Remove Product from Cart ===");
        String name="TC_002";
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        String expected = "";
        String actual = "";
        try {
            actual = driver.findElement(By.className("shopping_cart_badge")).getText();
        } catch (Exception e) {
            actual = "";
        }
        String status = expected.equals(actual) ? "PASS" : "FAIL";
        System.out.println("Remove from Cart Test: " + status);
        TestReport.add("Cart",name, status, "Remove from cart", actual);
    }

    //3 test cases
    public static void TC_003() {
        System.out.println("=== Test Case 3: Cart Badge Updates ===");
        String name="TC_003";
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        String expected = "2";
        String actual = driver.findElement(By.className("shopping_cart_badge")).getText();
        String status = expected.equals(actual) ? "PASS" : "FAIL";
        System.out.println("Cart Badge Number Test: " + status);
        TestReport.add("Cart",name, status, "Cart badge updates", actual);
    }

    //4 test cases
    public static void TC_004() {
        System.out.println("=== Test Case 4: Verify Products in Cart Page ===");
        String name="TC_004";
        driver.findElement(By.className("shopping_cart_link")).click();
        String expectedProduct1 = "Sauce Labs Backpack";
        String expectedProduct2 = "Sauce Labs Bike Light";

        String actualProduct1 = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText();
        String actualProduct2 = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']")).getText();
        String status1 = expectedProduct1.equals(actualProduct1) ? "PASS" : "FAIL";
        String status2 = expectedProduct2.equals(actualProduct2) ? "PASS" : "FAIL";

        System.out.println("Product 1 in Cart Test: " + status1);
        System.out.println("Product 2 in Cart Test: " + status2);
        TestReport.add("Cart", name, status1, "Products in cart", actualProduct1 + ", " + actualProduct2);
    }

    //5 test cases
    public static void TC_005() {
        System.out.println("=== Test Case 5: Continue Shopping Button ===");
        String name="TC_005";
        driver.findElement(By.id("continue-shopping")).click();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();

        String status = expectedUrl.equals(actualUrl) ? "PASS" : "FAIL";
        System.out.println("Continue Shopping Test: " + status);
        TestReport.add("Cart",name, status, "Continue shopping", actualUrl);
    }

    // 6 test cases
    public static void TC_006() {
        System.out.println("=== Test Case 6:add more than one product. ===");
        String name="TC_006";
        String expected = "2";
        String actual = driver.findElement(By.className("shopping_cart_badge")).getText();
        String status = expected.equals(actual) ? "PASS" : "FAIL";
        System.out.println("Cart Badge Test: " + status);
        TestReport.add("Cart",name, status, "Multiple products", actual);
        // remove 1 product
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
    }

    //7 test cases
    public static void TC_007() {
        System.out.println("=== Test Case 7: Verify Product Details in Cart ===");
        String name="TC_007";
        driver.findElement(By.className("shopping_cart_link")).click();
        String expectedName1 = "Sauce Labs Backpack";
        String expectedPrice1 = "$29.99";
        String expectedQuantity1 = "1";
        String actualName1 = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText();
        String actualPrice1 = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/following::div[@class='inventory_item_price'][1]")).getText();
        String actualQuantity1 = driver.findElement(By.className("cart_quantity")).getText();
        String statusName = expectedName1.equals(actualName1) ? "PASS" : "FAIL";
        String statusPrice = expectedPrice1.equals(actualPrice1) ? "PASS" : "FAIL";
        String statusQuantity = expectedQuantity1.equals(actualQuantity1) ? "PASS" : "FAIL";

        System.out.println("Product Name Test: " + statusName);
        System.out.println("Product Price Test: " + statusPrice);
        System.out.println("Product Quantity Test: " + statusQuantity);
        TestReport.add("Cart",name, statusName, "Product details", "Name: " + actualName1 + ", Price: " + actualPrice1 + ", Qty: " + actualQuantity1);

        // remove 1 product
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    // 8 test cases
    public static void TC_008() {
        System.out.println("=== Test Case 8: Add same product twice ===");
        String name="TC_008";
        String status;
        try {
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            status = "PASS: Product added successfully";
        } catch (NoSuchElementException e) {
            status = "FAIL: Add button not found, cannot add more units";
        }
        System.out.println("Cart Badge Test: " + status);
        TestReport.add("Cart",name, status.contains("PASS") ? "PASS" : "FAIL", "Add same product twice", status);
    }

    // 10 test cases
    public static void TC_010() {
        System.out.println("=== Test Case 10: Empty Cart Verification ===");
        String name="TC_0010";
        int cartItems = driver.findElements(By.className("shopping_cart_badge")).size();

        String status = (cartItems == 0) ? "PASS: Cart is empty" : "FAIL: Cart is not empty";
        System.out.println("Empty Cart Test: " + status);
        TestReport.add("Cart",name, status.contains("PASS") ? "PASS" : "FAIL", "Empty cart", status);
    }

    // 11 test case
    public static void TC_011() {
        System.out.println("=== Test Case 11: Cart Persistence After Refresh ===");
        String name="TC_0011";
        driver.navigate().to("https://www.saucedemo.com/inventory.html");

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        String expectedName = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']")).getText();
        String expectedPrice = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']/following::div[@class='inventory_item_price'][1]")).getText();
        String expectedQuantity = driver.findElement(By.className("cart_quantity")).getText();
        driver.navigate().refresh();
        driver.findElement(By.className("shopping_cart_link")).click();
        String actualName = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']")).getText();
        String actualPrice = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']/following::div[@class='inventory_item_price'][1]")).getText();
        String actualQuantity = driver.findElement(By.className("cart_quantity")).getText();

        String statusName = expectedName.equals(actualName) ? "PASS" : "FAIL";
        String statusPrice = expectedPrice.equals(actualPrice) ? "PASS" : "FAIL";
        String statusQuantity = expectedQuantity.equals(actualQuantity) ? "PASS" : "FAIL";
        System.out.println("Product Name Test: " + statusName);
        System.out.println("Product Price Test: " + statusPrice);
        System.out.println("Product Quantity Test: " + statusQuantity);
        TestReport.add("Cart",name, statusName, "Cart persistence", "Name: " + statusName + ", Price: " + statusPrice + ", Qty: " + statusQuantity);
    }
}
