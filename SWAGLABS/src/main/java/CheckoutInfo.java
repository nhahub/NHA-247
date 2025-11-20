import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;


public class CheckoutInfo {
    static TestReports TestReport = new TestReports();
    public static void CheckoutInfo() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://www.saucedemo.com/");

        try{
            log_in(driver);

            testcase1(driver);

            resetCheckoutPage(driver);
            testcase2(driver);

            resetCheckoutPage(driver);
            testcase3(driver);
            resetCheckoutPage(driver);
            testcase4(driver);
            resetCheckoutPage(driver);
            testcase5(driver);
            resetCheckoutPage(driver);
            testcase6(driver);
            resetCheckoutPage(driver);
            testcase7(driver);
            resetCheckoutPage(driver);
            testcase8(driver);
        }finally {
            Thread.sleep(2000);
            driver.quit();

        }}

    public static void log_in(WebDriver driver) {
        /////////////////////login////////////////////////////////
        WebElement user_name = driver.findElement(By.id("user-name"));
        user_name.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement log_in = driver.findElement(By.id("login-button"));
        log_in.click();

        /// ////////////////inventory_page//////////////////////
        WebElement add_element = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        add_element.click();
        WebElement shopping_cart = driver.findElement(By.id("shopping_cart_container"));
        shopping_cart.click();

        /// //////////////////////cartpage///////////////////////
        WebElement check_out = driver.findElement(By.id("checkout"));
        check_out.click();
    }

    public static void resetCheckoutPage(WebDriver driver) throws InterruptedException {
        // Navigate to inventory page
        driver.get("https://www.saucedemo.com/inventory.html");

        // Remove all items from cart
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[data-test^='remove']"));
        for (WebElement remove : removeButtons) {
            remove.click();
        }

        // Add the product again (needed to go to checkout)
        WebElement addElement = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addElement.click();

        // Go to cart
        WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
        shoppingCart.click();

        // Click checkout
        WebElement checkout = driver.findElement(By.id("checkout"));
        checkout.click();

        // Wait a second for page to load
        Thread.sleep(500);
    }


    /// ///////////////////checkoutinfo///////////////////////
    //=====================tc1=======================//
    public static void testcase1(WebDriver driver) {
        WebElement first1 = driver.findElement(By.id("first-name"));
        first1.sendKeys("test");
        WebElement second1 = driver.findElement(By.id("last-name"));
        second1.sendKeys("test2");
        WebElement code2 = driver.findElement(By.id("postal-code"));
        code2.sendKeys("24576");
        WebElement cont1 = driver.findElement(By.id("continue"));
        cont1.click();
        String expected="https://www.saucedemo.com/checkout-step-two.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_001", status, expected, actual);
    }
    //==========================tc2======================//
    public static void testcase2(WebDriver driver) {
        driver.navigate().to("https://www.saucedemo.com/checkout-step-one.html");

        WebElement first2 = driver.findElement(By.id("first-name"));
        first2.sendKeys("65489");
        WebElement second2 = driver.findElement(By.id("last-name"));
        second2.sendKeys("test2");
        WebElement code2 = driver.findElement(By.id("postal-code"));
        code2.sendKeys("24576");
        WebElement cont2 = driver.findElement(By.id("continue"));
        cont2.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_002", status, expected, actual);
    }
    //=====================tc3=======================//
    public static void testcase3(WebDriver driver) {
        WebElement first3 = driver.findElement(By.id("first-name"));
        first3.sendKeys("test");
        WebElement cont3 = driver.findElement(By.id("continue"));
        cont3.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_003", status, expected, actual);
    }
    //=====================tc4=======================//
    public static void testcase4(WebDriver driver) {
        WebElement first4 = driver.findElement(By.id("first-name"));
        first4.sendKeys("test");
        WebElement second4 = driver.findElement(By.id("last-name"));
        second4.sendKeys("35357");
        WebElement code4 = driver.findElement(By.id("postal-code"));
        code4.sendKeys("34567");
        WebElement cont4 = driver.findElement(By.id("continue"));
        cont4.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_004", status, expected, actual);
    }
    //=====================tc5=======================//
    public static void testcase5(WebDriver driver) {
        WebElement second5 = driver.findElement(By.id("last-name"));
        second5.sendKeys("test2");
        WebElement cont5 = driver.findElement(By.id("continue"));
        cont5.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_005", status, expected, actual);
    }
    //=====================tc6=======================//
    public static void testcase6(WebDriver driver) {
        WebElement first6 = driver.findElement(By.id("first-name"));
        first6.sendKeys("test");
        WebElement second6 = driver.findElement(By.id("last-name"));
        second6.sendKeys("test2");
        WebElement cont6 = driver.findElement(By.id("continue"));
        cont6.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_006", status, expected, actual);
    }
    //=====================tc7=======================//
    public static void testcase7(WebDriver driver){
        WebElement first7 = driver.findElement(By.id("first-name"));
        first7.sendKeys("test");
        WebElement second7 = driver.findElement(By.id("last-name"));
        second7.sendKeys("test2");
        WebElement code7 = driver.findElement(By.id("postal-code"));
        code7.sendKeys("hkgjf");
        WebElement cont7 = driver.findElement(By.id("continue"));
        cont7.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_007", status, expected, actual);
    }
    //===================tc8====================//
    public static void testcase8(WebDriver driver){
        WebElement first7 = driver.findElement(By.id("first-name"));
        first7.sendKeys("test");
        WebElement second7 = driver.findElement(By.id("last-name"));
        second7.sendKeys("test2");
        WebElement code7 = driver.findElement(By.id("postal-code"));
        code7.sendKeys("1");
        WebElement cont7 = driver.findElement(By.id("continue"));
        cont7.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_008", status, expected, actual);
    }
    //====================tc9==================//
    public static void testcase9(WebDriver driver){
        WebElement first9= driver.findElement(By.id("first-name"));
        first9.sendKeys("test");
        WebElement second9= driver.findElement(By.id("last-name"));
        second9.sendKeys("test2");
        WebElement code9= driver.findElement(By.id("postal-code"));
        code9.sendKeys("12");
        WebElement cont9= driver.findElement(By.id("continue"));
        cont9.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_009", status, expected, actual);
    }
    //=========================tc10====================//
    public static void testcase10(WebDriver driver){
        WebElement first10= driver.findElement(By.id("first-name"));
        first10.sendKeys("test");
        WebElement second10= driver.findElement(By.id("last-name"));
        second10.sendKeys("test2");
        WebElement code10= driver.findElement(By.id("postal-code"));
        code10.sendKeys("176458903257");
        WebElement cont10= driver.findElement(By.id("continue"));
        cont10.click();
    }
    //======================tc11===================//
    public static void testcase11(WebDriver driver){
        WebElement first11= driver.findElement(By.id("first-name"));
        first11.sendKeys("test");
        WebElement second11= driver.findElement(By.id("last-name"));
        second11.sendKeys("test2");
        WebElement code11= driver.findElement(By.id("postal-code"));
        code11.sendKeys("22@2");
        WebElement cont11= driver.findElement(By.id("continue"));
        cont11.click();
    }
    //=========================tc12================//
    public static void testcase12(WebDriver driver){
        WebElement first12= driver.findElement(By.id("first-name"));
        first12.sendKeys("@$%@");
        WebElement second12= driver.findElement(By.id("last-name"));
        second12.sendKeys("test2");
        WebElement code12= driver.findElement(By.id("postal-code"));
        code12.sendKeys("6538");
        WebElement cont12 = driver.findElement(By.id("continue"));
        cont12.click();
    }
    //======================tc13==================//
    public static void testcase13(WebDriver driver){
        WebElement first13= driver.findElement(By.id("first-name"));
        first13.sendKeys("test");
        WebElement second13= driver.findElement(By.id("last-name"));
        second13.sendKeys("@$%!");
        WebElement code13= driver.findElement(By.id("postal-code"));
        code13.sendKeys("8754");
        WebElement cont13 = driver.findElement(By.id("continue"));
        cont13.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_013", status, expected, actual);
    }
    //=======================tc14===========================//
    public static void testcase14(WebDriver driver){
        WebElement first14= driver.findElement(By.id("first-name"));
        first14.sendKeys("test");
        WebElement second14= driver.findElement(By.id("last-name"));
        second14.sendKeys("test2");
        WebElement code14= driver.findElement(By.id("postal-code"));
        code14.sendKeys("!@$@");
        WebElement cont14= driver.findElement(By.id("continue"));
        cont14.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        if(expected.equals(actual)){
            System.out.println("testcase14:passed");
        }else{
            System.out.println("testcase14:failed");
            System.out.println("expected: "+expected);
            System.out.println("actual: "+actual);
        }
    }
    //======================tc15=========================//
    public static void testcase15(WebDriver driver){
        WebElement first15 = driver.findElement(By.id("first-name"));
        first15.sendKeys("test");
        WebElement second15 = driver.findElement(By.id("last-name"));
        second15.sendKeys("test2");
        WebElement code15 = driver.findElement(By.id("postal-code"));
        code15.sendKeys("000");
        WebElement cont15 = driver.findElement(By.id("continue"));
        cont15.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_015", status, expected, actual);
    }
    //===========================tc16=======================//
    public static void testcase16(WebDriver driver){
        WebElement first16 = driver.findElement(By.id("first-name"));
        first16.sendKeys("test");
        WebElement second16 = driver.findElement(By.id("last-name"));
        second16.sendKeys("test2");
        WebElement code16 = driver.findElement(By.id("postal-code"));
        code16.sendKeys("0m654");
        WebElement cont16 = driver.findElement(By.id("continue"));
        cont16.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_016", status, expected, actual);
    }
    //=============================tc17===========================//
    public static void testcase17(WebDriver driver){
        WebElement cont17= driver.findElement(By.id("continue"));
        cont17.click();
        String expected="https://www.saucedemo.com/checkout-step-one.html";
        String actual= driver.getCurrentUrl();
        String status ="";
        if(expected.equals(actual)){
            status = "Pass";

        }else{
            status = "Fail";
        }
        TestReport.add("CheckoutInfo","TC_017", status, expected, actual);
    }
}



