import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class Inventory {

    static TestReports TestReport = new TestReports();
    public static void Inventory() throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        try {
            TC_006(driver);
            // Login first
            login(driver);

            // Run each test case
            TC_001(driver);
            resetCart(driver);
            TC_002(driver);
            resetCart(driver);
            TC_003(driver);
            resetCart(driver);
            TC_004(driver);
            resetCart(driver);
            TC_005(driver);
            resetCart(driver);
            TC_007(driver);
            resetCart(driver);
            TC_008(driver);
            resetCart(driver);
            TC_009(driver);
            resetCart(driver);
            TC_010(driver);


            System.out.println("\n ALL TESTS COMPLETED!");

        } finally {
            Thread.sleep(2000);
            driver.quit();


        }
    }

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


    // Helper method to reset cart
    public static void resetCart(WebDriver driver) {
        List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));
        if (!cartBadges.isEmpty()) {
            driver.findElement(By.className("shopping_cart_link")).click();
            List<WebElement> removeButtons = driver.findElements(By.cssSelector("button.cart_button"));
            for (WebElement removeBtn : removeButtons) {
                removeBtn.click();
            }
            driver.findElement(By.id("continue-shopping")).click();
        }
    }

    // TC_INVENTORY_001: Add To Cart Functionality
    public static void TC_001(WebDriver driver) {
        String testName = "TC_001";
        System.out.println("\n=== Running TC_001 ===");

        String expected = "Product added to cart";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            // Click Add to Cart using ID
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

            // Check if button changed to Remove using ID
            String buttonText = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
            List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));

            if (buttonText.equals("Remove") && !cartBadges.isEmpty() && cartBadges.get(0).getText().equals("1")) {
                status = "PASS";
                actual = "Item added to cart";
            } else {
                status = "FAIL";
                actual = "Button: " + buttonText + ", Badge: " + (cartBadges.isEmpty() ? "empty" : cartBadges.get(0).getText());
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }


        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_002: Remove button functionality
    public static void TC_002(WebDriver driver) {
        String testName = "TC_002";
        System.out.println("\n=== Running TC_002 ===");

        String expected = "Product removed from cart";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            // Add item first
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

            // Remove item
            driver.findElement(By.id("remove-sauce-labs-backpack")).click();

            // Check if button changed back to Add to Cart
            String buttonText = driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText();
            List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));

            if (buttonText.equals("Add to cart") && cartBadges.isEmpty()) {
                status = "PASS";
                actual = "Item removed from cart";
            } else {
                status = "FAIL";
                actual = "Button: " + buttonText + ", Badge empty: " + cartBadges.isEmpty();
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }


        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_003: Adding multiple items to cart
    public static void TC_003(WebDriver driver) {
        String testName = "TC_003";
        System.out.println("\n=== Running TC_003 ===");

        String expected = "Multiple items added to cart";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            // Add two items using their IDs
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

            // Check buttons changed to Remove and cart count
            String btn1Text = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
            String btn2Text = driver.findElement(By.id("remove-sauce-labs-bike-light")).getText();
            List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));

            if (!cartBadges.isEmpty() && cartBadges.get(0).getText().equals("2") &&
                    btn1Text.equals("Remove") && btn2Text.equals("Remove")) {
                status = "PASS";
                actual = "2 items added to cart";
            } else {
                status = "FAIL";
                actual = "Count: " + (cartBadges.isEmpty() ? "0" : cartBadges.get(0).getText());
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_004: Removing multiple items from cart
    public static void TC_004(WebDriver driver) {
        String testName = "TC_004";
        System.out.println("\n=== Running TC_004 ===");

        String expected = "Multiple items removed from cart";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            // Add two items
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

            // Remove both items
            driver.findElement(By.id("remove-sauce-labs-backpack")).click();
            driver.findElement(By.id("remove-sauce-labs-bike-light")).click();

            // Check buttons changed back and cart is empty
            String btn1Text = driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText();
            String btn2Text = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).getText();
            List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));

            if (cartBadges.isEmpty() && btn1Text.equals("Add to cart") && btn2Text.equals("Add to cart")) {
                status = "PASS";
                actual = "All items removed from cart";
            } else {
                status = "FAIL";
                actual = "Badge empty: " + cartBadges.isEmpty() + ", Buttons: " + btn1Text + "," + btn2Text;
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }


        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_005: Cart button functionality
    public static void TC_005(WebDriver driver) {
        String testName = "TC_005";
        System.out.println("\n=== Running TC_005 ===");

        String expected = "Cart page opens";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");
            driver.findElement(By.className("shopping_cart_link")).click();

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("cart.html")) {
                status = "PASS";
                actual = "Navigated to cart page";
            } else {
                status = "FAIL";
                actual = "Current URL: " + currentUrl;
            }

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_006: Access inventory without login
    public static void TC_006(WebDriver driver) throws InterruptedException {
        String testName = "TC_006";
        System.out.println("\n=== Running TC_006 ===");
//        driver.get("https://www.saucedemo.com/inventory.html");
//        Thread.sleep(2000);
//        driver.findElement(By.id("react-burger-menu-btn")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("logout_sidebar_link")).click();
//        Thread.sleep(2000);
        String expected = "Redirect to login page";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            boolean isRedirected = !currentUrl.contains("inventory.html");


            if (isRedirected) {
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

        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_007: Sorting Ascending Alphabetical
    public static void TC_007(WebDriver driver) {
        String testName = "TC_007";
        System.out.println("\n=== Running TC_007 ===");

        String expected = "Products sorted A to Z";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
            Select select = new Select(sortDropdown);
            select.selectByValue("az");

            List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));
            boolean isSorted = true;

            for (int i = 0; i < productNames.size() - 1; i++) {
                String current = productNames.get(i).getText();
                String next = productNames.get(i + 1).getText();
                if (current.compareTo(next) > 0) {
                    isSorted = false;
                    break;
                }
            }

            status = isSorted ? "PASS" : "FAIL";
            actual = isSorted ? "Sorted A to Z" : "Not sorted properly";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_006: Sorting Descending Alphabetical
    public static void TC_008(WebDriver driver) {
        String testName = "TC_008";
        System.out.println("\n=== Running TC_008 ===");

        String expected = "Products sorted Z to A";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
            Select select = new Select(sortDropdown);
            select.selectByValue("za");

            List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));
            boolean isReverseSorted = true;

            for (int i = 0; i < productNames.size() - 1; i++) {
                String current = productNames.get(i).getText();
                String next = productNames.get(i + 1).getText();
                if (current.compareTo(next) < 0) {
                    isReverseSorted = false;
                    break;
                }
            }

            status = isReverseSorted ? "PASS" : "FAIL";
            actual = isReverseSorted ? "Sorted Z to A" : "Not sorted properly";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }
        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_009: Sorting Ascending Price
    public static void TC_009(WebDriver driver) {
        String testName = "TC_009";
        System.out.println("\n=== Running TC_009 ===");

        String expected = "Products sorted low to high price";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
            Select select = new Select(sortDropdown);
            select.selectByValue("lohi");

            List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
            boolean isSorted = true;

            for (int i = 0; i < productPrices.size() - 1; i++) {
                double current = Double.parseDouble(productPrices.get(i).getText().replace("$", ""));
                double next = Double.parseDouble(productPrices.get(i + 1).getText().replace("$", ""));
                if (current > next) {
                    isSorted = false;
                    break;
                }
            }

            status = isSorted ? "PASS" : "FAIL";
            actual = isSorted ? "Sorted low to high" : "Not sorted properly";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }
        TestReports.add("Inventory",testName, status, expected, actual);
    }

    // TC_INVENTORY_010: Sorting Descending Price
    public static void TC_010(WebDriver driver) {
        String testName = "TC_010";
        System.out.println("\n=== Running TC_010 ===");

        String expected = "Products sorted high to low price";
        String actual = "";
        String status = "";

        try {
            driver.get("https://www.saucedemo.com/inventory.html");

            WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
            Select select = new Select(sortDropdown);
            select.selectByValue("hilo");

            List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
            boolean isSorted = true;

            for (int i = 0; i < productPrices.size() - 1; i++) {
                double current = Double.parseDouble(productPrices.get(i).getText().replace("$", ""));
                double next = Double.parseDouble(productPrices.get(i + 1).getText().replace("$", ""));
                if (current < next) {
                    isSorted = false;
                    break;
                }
            }

            status = isSorted ? "PASS" : "FAIL";
            actual = isSorted ? "Sorted high to low" : "Not sorted properly";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }

        TestReports.add("Inventory",testName, status, expected, actual);
    }


}

