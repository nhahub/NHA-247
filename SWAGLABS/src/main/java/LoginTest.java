import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.NoSuchElementException;

public class LoginTest {

    public static void main(String[] args) {

        Object[][] loginData = {
                {new User("standard_user", "secret_sauce", null)},
                {new User("invalid_user", "invalid_user", "Epic sadface: Username and password do not match any user in this service")},
                {new User("standard_user", "", "Epic sadface: Password is required")},
                {new User("locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out.")},
                {new User("problem_user", "secret_sauce", null)},
                {new User("performance_glitch_user", "secret_sauce", null)},
                {new User("error_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service")},
                {new User("visual_user", "secret_sauce", null)}
        };

        for (Object[] data : loginData) {
            User user = (User) data[0];
            testLogin(user);
        }
        TestReports.print();
    }

    public static void testLogin(User user) {
        TestReports TestReport = new TestReports();


        WebDriver driver;
        driver = new FirefoxDriver();      // Edit this with your available Drive
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        String Status ="";



        driver.findElement(By.id("user-name")).sendKeys(user.username);
        driver.findElement(By.id("password")).sendKeys(user.password);
        driver.findElement(By.id("login-button")).click();



        List<WebElement> errorElements = driver.findElements(By.cssSelector("h3[data-test='error']"));
        String errorMessage = null;

        if (errorElements.isEmpty()) {
            // List is empty, so set errorMessage to null
            errorMessage = null;
        } else {
            // List has elements, so get the first one
            errorMessage = errorElements.get(0).getText();
        }

        String actual =driver.getCurrentUrl();
        String expected="https://www.saucedemo.com/inventory.html";

        if (errorMessage == null) {
            if (!actual.equals(expected)) {
                System.out.println("Test Failed: The current URL is not as expected.");
                Status = "FAIL";
            } else {
                System.out.println("Test Passed: User : "+user.username+ " logged in successfully.");
                Status = "PASS";
            }
        } else {
            if (user.errorMessage != null && !user.errorMessage.equals(errorMessage)) {
                System.out.println("Test Failed: Error message mismatch. Expected: " + user.errorMessage + " but found: " + errorMessage);
                Status = "FAIL";
                actual = errorMessage;
                expected = user.errorMessage;
            } else {
                System.out.println("Test Passed: Correct error message displayed. User:" + user.username);
                Status = "PASS";
                expected = user.errorMessage;
                actual = errorMessage;
            }

        }
        TestReport.add("Login", user.username, Status, expected,actual);

        driver.quit();


    }
}