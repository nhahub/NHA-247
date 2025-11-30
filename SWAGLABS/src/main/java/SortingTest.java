

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortingTest {


    public static List<String> getItemNames(WebDriver driver) {
        List<WebElement> Item_Names = driver.findElements(By.cssSelector("[data-test='inventory-item-name']"));
        return new ArrayList<>(
                Item_Names.stream().map(WebElement::getText)
                        .collect(Collectors.toList()));
    }
    public static List<Double> getItemPrices(WebDriver driver) {
        List<WebElement> itemPrices = driver.findElements(By.cssSelector("[data-test='inventory-item-price']"));
        return itemPrices.stream()
                .map(price -> {
                    String priceText = price.getText().replace("$", "");
                    return Double.parseDouble(priceText);
                })
                .collect(Collectors.toList());
    }
    static TestReports TestReport = new TestReports();


    public static void main(String[] args) {
        Object[][] ValidLoginData = {
                {"standard_user", "secret_sauce"},  // Standard user
                {"problem_user", "secret_sauce"},  // Problem user
                {"performance_glitch_user", "secret_sauce"},  // Performance glitch user
                {"error_user", "secret_sauce"},  // Error user
                {"visual_user", "secret_sauce"},  // Visual user
        };

        for (Object[] data : ValidLoginData) {

            String username = data[0].toString();
            String password = data[1].toString();
            TestSortAtoZ(username, password);
            TestSortZtoA(username, password);
            TestSortLowToHigh(username, password);
            TestSortHighToLow(username, password);

        }
        TestReport.print();
    }

    public static void TestSortAtoZ(String username, String password) {
        WebDriver driver;
        driver = new FirefoxDriver();      // Edit this with your available Drive
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");


        //login
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();


        String expected = "";
        String actual = "";
        String status = "";


        List<String> Items_names_Before_Sort = getItemNames(driver);
        driver.findElement(By.cssSelector("option[value='az']")).click();
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept(); // or dismiss()
        } catch (NoAlertPresentException e) {
            status = "FAIL";
        }
        List<String> Items_names_After_Sort = getItemNames(driver);

        Collections.sort(Items_names_Before_Sort);

        // Assertion
        if (Items_names_Before_Sort.equals(Items_names_After_Sort)) {
            System.out.println("User: "+username+" Items are correctly sorted from A to Z..");
            status = "PASS";
            actual = "User: "+username+" Items are correctly sorted from A to Z";
            expected = "User: "+username+" Items are correctly sorted from A to Z";
        } else {
            System.out.println("User: "+username+" Items are not sorted correctly from A to Z..");
            System.out.println("expected: " + Items_names_Before_Sort);
            System.out.println("Actual: " + Items_names_After_Sort);
            status = "FAIL";
            actual = "User: "+username+" Items are not sorted correctly from A to Z.";
            expected = "User: "+username+" Items are correctly sorted from A to Z";
        }


        // Report
        TestReport.add("sortTest","TestSortAtoZ", status, expected, actual);

        driver.quit();
    }
    public static void TestSortZtoA(String username, String password) {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        String expected = "";
        String actual = "";
        String status = "";

        List<String> Items_names_Before_Sort = getItemNames(driver);

        driver.findElement(By.cssSelector("select.product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='za']")).click();

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ignored) {
            status="FAIL";
        }

        List<String> Items_names_After_Sort = getItemNames(driver);

        Collections.sort(Items_names_Before_Sort, Collections.reverseOrder());

        // assertion
        if (Items_names_Before_Sort.equals(Items_names_After_Sort)) {
            System.out.println("User: "+username+" Items are correctly sorted: Z to A.");
            status = "PASS";
            actual = "User: "+username+" Items are correctly sorted from Z to A";
            expected = "User: "+username+" Items are correctly sorted from Z to A";
        } else {
            System.out.println("User: "+username+" Items are not sorted correctly from A to Z.");
            System.out.println("expected: " + Items_names_Before_Sort);
            System.out.println("actual: " + Items_names_After_Sort);
            status = "FAIL";
            actual = "User: "+username+" Items are not sorted correctly from Z to A .";
            expected = "User: "+username+" Items are correctly sorted from Z to A";
        }

        // Report
       TestReport.add("sortTest","TestSortZtoA", status, expected, actual);

        driver.quit();
    }
    public static void TestSortLowToHigh(String username, String password) {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        String expected = "Price (low to high)";
        String actual = "";
        String status = "";

        List<Double> Items_prices_Before_Sort = getItemPrices(driver);

        driver.findElement(By.cssSelector("select.product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='lohi']")).click();

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ignored) {
            status="FAIL";
        }

        List<Double> Items_prices_After_Sort = getItemPrices(driver);

        Collections.sort(Items_prices_Before_Sort);

        if (Items_prices_Before_Sort.equals(Items_prices_After_Sort)) {
            System.out.println("User: "+username+" Items are correctly sorted by price: low to high.");
            status = "PASS";
            actual = "User: "+username+" Items are correctly sorted by price from low to high";
            expected = "User: "+username+" Items are correctly sorted by price from low to high";
        } else {
            System.out.println("User: "+username+" Items are not sorted correctly by price from low to high.");
            System.out.println("expected: " + Items_prices_Before_Sort);
            System.out.println("actual: " + Items_prices_After_Sort);
            status = "FAIL";
            actual = "User: "+username+" Items are not sorted correctly by price from low to high";
            expected = "User: "+username+" Items are correctly sorted by price from low to high";
        }

        // Report
       TestReport.add("sortTest","TestSortLowToHigh", status, expected, actual);
        driver.quit();
    }

    public static void TestSortHighToLow(String username, String password) {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        String expected = "Price (high to low)";
        String actual = "";
        String status = "";

        List<Double> Items_prices_Before_Sort = getItemPrices(driver);

        driver.findElement(By.cssSelector("select.product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='hilo']")).click();

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ignored) {
            status="FAIL";
        }

        List<Double> Items_prices_After_Sort = getItemPrices(driver);

        Collections.sort(Items_prices_Before_Sort, Collections.reverseOrder());

        if (Items_prices_Before_Sort.equals(Items_prices_After_Sort)) {
            System.out.println("User: "+username+" Items are correctly sorted by price: high to low.");
            status = "PASS";
            actual = "User: "+username+" Items are correctly sorted by price from high to low";
            expected = "User: "+username+" Items are correctly sorted by price from high to low";
        } else {
            System.out.println("User: "+username+" Items are not sorted correctly by price from high to low.");
            System.out.println("expected: " + Items_prices_Before_Sort);
            System.out.println("actual: " + Items_prices_After_Sort);
            status = "FAIL";
            actual = "User: "+username+" Items are not sorted correctly by price from high to low";
            expected = "User: "+username+" Items are correctly sorted by price from high to low";
        }

        // Report
       TestReport.add("sortTest","TestSortHighToLow", status, expected, actual);
        driver.quit();
    }
}