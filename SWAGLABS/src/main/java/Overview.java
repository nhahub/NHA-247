import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;


public class Overview {

    static TestReports TestReport = new TestReports();

    public static void Overview() throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        //driver.manage().window().maximize();

        try {

            // Run each test case
            TC_001(driver);
            TC_002(driver);
            TC_003(driver);
            TC_004(driver);

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


        System.out.println(" Logged in successfully");
    }

    public static void getProductContainer(WebDriver driver, String productName) {

        WebElement nameEl = driver.findElement(
                By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName + "']"));
        nameEl.click();
    }

    public static void PressButton (WebDriver driver , String name){
        WebElement button = driver.findElement(By.xpath("//button[@name=\""+name+"\"]"));
        button.click();
    }

    //enter firstname\lastname\zipcode
    public static void FillInfos (WebDriver driver, String firstname , String lastname , String zipcode){
        WebElement firstnameEl = driver.findElement(By.xpath("//input[@name=\"firstName\"]"));
        WebElement lastnameEl = driver.findElement(By.xpath("//input[@name=\"lastName\"]"));
        WebElement zipcodeEl = driver.findElement(By.xpath("//input[@name=\"postalCode\"]"));
        firstnameEl.sendKeys(firstname);
        lastnameEl.sendKeys(lastname);
        zipcodeEl.sendKeys(zipcode);
    }

    // TC_001: choose one item and monitor the checkout
    public static void TC_001(WebDriver driver) {
        String testName = "TC_001";
        System.out.println("\n=== Running TC_001 ===");

        String expected = "https://www.saucedemo.com/checkout-complete.html";
        String actual = "";
        String status = "";

        try {

            login(driver);
            PressButton( driver , "add-to-cart-sauce-labs-backpack");
            WebElement cart = driver.findElement(By.xpath("//*[@data-test='shopping-cart-badge']"));
            cart.click();
            PressButton( driver , "checkout");
            FillInfos(driver , "Abdelrahman" , "Mohamed" , "4444" );
            WebElement Continue = driver.findElement(By.xpath("//input[@name=\"continue\"]"));
            Continue.click();
            PressButton( driver , "finish");


            actual = driver.getCurrentUrl();


            status = actual.contains("https://www.saucedemo.com/checkout-complete.html") ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }


        TestReport.add("Overview",testName, status, expected, actual);
    }


    // TC_002: choose two item and monitor check out
    public static void TC_002(WebDriver driver) {
        String testName = "TC_002";
        System.out.println("\n=== Running TC_002 ===");

        String expected = "https://www.saucedemo.com/checkout-complete.html";
        String actual = "";
        String status = "";

        try {

            login(driver);
            PressButton( driver , "add-to-cart-sauce-labs-backpack");
            PressButton( driver , "add-to-cart-sauce-labs-bike-light");
            WebElement cart = driver.findElement(By.className("shopping_cart_badge"));
            cart.click();
            PressButton( driver , "checkout");
            FillInfos(driver , "Abdelrahman" , "Mohamed" , "4444" );
            WebElement Continue = driver.findElement(By.xpath("//input[@name=\"continue\"]"));
            Continue.click();
            PressButton( driver , "finish");


            actual = driver.getCurrentUrl();

            status = actual.contains("https://www.saucedemo.com/checkout-complete.html") ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }


        TestReport.add("Overview",testName, status, expected, actual);
    }


    //TC_003: click on cancel button to navigate to cart page
    public static void TC_003(WebDriver driver) {
        String testName = "TC_003";
        System.out.println("\n=== Running TC_003 ===");

        String expected = "https://www.saucedemo.com/cart.html";
        String actual = "";
        String status = "";

        try {

            login(driver);
            PressButton( driver , "add-to-cart-sauce-labs-backpack");
            WebElement cart = driver.findElement(By.className("shopping_cart_badge"));
            cart.click();
            PressButton( driver , "checkout");
            FillInfos(driver , "Abdelrahman" , "Mohamed" , "4444" );
            WebElement Continue = driver.findElement(By.xpath("//input[@name=\"continue\"]"));
            Continue.click();
            PressButton( driver , "cancel");


            actual = driver.getCurrentUrl();

            status = actual.contains("https://www.saucedemo.com/cart.html") ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }


        TestReport.add("Overview",testName, status, expected, actual);
    }


    //TC_004:  check if payment information differ in two differnet  in two checkout
    public static void TC_004(WebDriver driver) {
        String testName = "TC_004";
        System.out.println("\n=== Running TC_004 ===");

        String expected = "Different";
        String actual = "";
        String status = "";

        try {

            login(driver);
            PressButton( driver , "remove-sauce-labs-backpack");

            PressButton( driver , "add-to-cart-sauce-labs-backpack");
            WebElement cart = driver.findElement(By.className("shopping_cart_badge"));
            cart.click();
            PressButton( driver , "checkout");
            FillInfos(driver , "Abdelrahman" , "Mohamed" , "4444" );
            WebElement Continue = driver.findElement(By.xpath("//input[@name=\"continue\"]"));
            Continue.click();
            WebElement FirstInfo = driver.findElement(By.className("summary_value_label"));
            String SauceCard1 = FirstInfo.getText();
            PressButton( driver , "finish");
            PressButton( driver , "back-to-products");
            PressButton( driver , "add-to-cart-sauce-labs-backpack");
            WebElement cart2 = driver.findElement(By.className("shopping_cart_badge"));
            cart2.click();
            PressButton( driver , "checkout");
            FillInfos(driver , "Abdelrahman" , "Mohamed" , "4444" );
            WebElement Continue2 = driver.findElement(By.xpath("//input[@name=\"continue\"]"));
            Continue2.click();
            WebElement SecondInfo = driver.findElement(By.className("summary_value_label"));
            String SauceCard2 = SecondInfo.getText();
            String Similarity = "";
            if (SauceCard1.equals(SauceCard2)) {
                Similarity = "Same";
            }else {
                Similarity = "Different";
            }


            actual = Similarity ;

            status = actual.contains("Different") ? "PASS" : "FAIL";

        } catch (Exception e) {
            status = "FAIL";
            actual = e.getMessage();
        }


        TestReport.add("Overview",testName, status, expected, actual);
    }








}
