import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CheckoutInfo {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add item to cart and go to checkout
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();

        String[][] testData = {
                {"test", "test2", "24576", "https://www.saucedemo.com/checkout-step-two.html"},
                {"65489", "test2", "24576", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "", "", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "35357", "34567", "https://www.saucedemo.com/checkout-step-one.html"},
                {"", "test2", "", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "hkgjf", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "1", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "12", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "176458903257", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "22@2", "https://www.saucedemo.com/checkout-step-one.html"},
                {"@$%@", "test2", "6538", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "@$%!", "8754", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "!@$@", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "000", "https://www.saucedemo.com/checkout-step-one.html"},
                {"test", "test2", "0m654", "https://www.saucedemo.com/checkout-step-one.html"},
                {"", "", "", "https://www.saucedemo.com/checkout-step-one.html"}
        };

        for (int i = 0; i < testData.length; i++) {
            driver.get("https://www.saucedemo.com/checkout-step-one.html");

            driver.findElement(By.id("first-name")).clear();
            driver.findElement(By.id("last-name")).clear();
            driver.findElement(By.id("postal-code")).clear();

            driver.findElement(By.id("first-name")).sendKeys(testData[i][0]);
            driver.findElement(By.id("last-name")).sendKeys(testData[i][1]);
            driver.findElement(By.id("postal-code")).sendKeys(testData[i][2]);
            driver.findElement(By.id("continue")).click();

            String actualUrl = driver.getCurrentUrl();
            String status = actualUrl.equals(testData[i][3]) ? "PASS" : "FAIL";

            // Add to TestReports
            TestReports.add("Checkout Information", "TestCase " + (i + 1), status, testData[i][3], actualUrl);
        }

        // Print formatted report
        TestReports.print();

        driver.quit();
    }
}