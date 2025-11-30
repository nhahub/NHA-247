import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class Cart {
    static WebDriver driver = new EdgeDriver();
    static TestReports TestReport = new TestReports();
    static List<Product> products = ProductExporter.importProducts();


    public static void main(String[] args) {



        login();
        CartTestCases();
        MultipleItems(driver, products);
        driver.quit();
        TestReports.print();
    }

    public static void login() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
    }



    public static void CartTestCases(){

        String className = "ProductPage";

        try {

                for (Product  product : products) {
                    String name = product.getName();
                    String expectedPrice = product.getPrice();
                    String expectedDesc = product.getDescription();
                    String addbutton = product.getAddButtonId();
                    String removebutton = product.getRemoveButtonId();
                String status;
                String expected;
                String actual;
                driver.findElement(By.id(addbutton)).click();
                expected = "1";
                actual = driver.findElement(By.className("shopping_cart_badge")).getText();
                status = expected.equals(actual) ? "PASS" : "FAIL";
                System.out.println("Add to Cart Test: " + status);
                TestReport.add("Cart","TC_1 "+name, status, "Add to cart", "Cart number=" +actual);
                //t2
                driver.findElement(By.className("shopping_cart_link")).click();
                String actualProduct2 = driver.findElement(By.xpath("//div[text()='" + name + "']")).getText();
                String status2 = name.equals(actualProduct2) ? "PASS" : "FAIL";
                System.out.println("Product 2 in Cart Test: " + status2);
                TestReport.add("Cart",name, status2, "Products in cart", actualProduct2 + ", " + actualProduct2);
                //t3


                driver.findElement(By.className("shopping_cart_link")).click();

                String status5 = "";

                String expectedQuantity1 = "1";
                String actualName1 =  driver.findElement(By.className("inventory_item_name")).getText();
                String actualPrice1 = driver.findElement(By.className("inventory_item_price")).getText();
                String actualQuantity1 = driver.findElement(By.className("cart_quantity")).getText();

                if(name.equals(actualName1) && expectedPrice.equals(actualPrice1) && expectedQuantity1.equals(actualQuantity1))
                {
                    status5 = "PASS";
                }else {
                    status5 = "FAIL";
                }
                String statusName = name.equals(actualName1) ? "PASS" : "FAIL";
                String statusPrice = expectedPrice.equals(actualPrice1) ? "PASS" : "FAIL";
                String statusQuantity = expectedQuantity1.equals(actualQuantity1) ? "PASS" : "FAIL";
                TestReport.add("Cart",name, status5, "Product details", "Name: " + actualName1 + ", Price: " + actualPrice1 + ", Qty: " + actualQuantity1);
                driver.navigate().to("https://www.saucedemo.com/inventory.html");

                //t4
                driver.findElement(By.className("shopping_cart_link")).click();
                String expectedName = driver.findElement(By.className("inventory_item_name")).getText();
                String expectedPrice2 = driver.findElement(By.className("inventory_item_price")).getText();
                String expectedQuantity = driver.findElement(By.className("cart_quantity")).getText();
                driver.navigate().refresh();
                driver.findElement(By.className("shopping_cart_link")).click();
                String actualName = driver.findElement(By.className("inventory_item_name")).getText();
                String actualPrice = driver.findElement(By.className("inventory_item_price")).getText();
                String actualQuantity = driver.findElement(By.className("cart_quantity")).getText();
                String status3 ="";

                if(expectedName.equals(actualName) && expectedPrice2.equals(actualPrice) && expectedQuantity.equals(actualQuantity)){
                    status3 = "PASS";
                }else {
                    status3 = "FAIL";
                }
                TestReport.add("Cart",name, status3, "Cart persistence", "Name: " + actualName + ", Price: " + actualPrice + ", Qty: " + actualQuantity);


                //t5

                driver.findElement(By.id(removebutton)).click();
                String expected1 = "";
                String actual1 = "";
                try {
                    actual1 = driver.findElement(By.className("shopping_cart_badge")).getText();
                } catch (Exception e) {
                    actual1 = "";
                }
                String status1 = expected.equals(actual) ? "PASS" : "FAIL";
                System.out.println("Remove from Cart Test: " + status);
                TestReport.add("Cart","TC_2 "+name, status1, "Remove","Cart number=" +actual1);

                //t6
                driver.findElement(By.className("shopping_cart_link")).click();
                int cartItems = driver.findElements(By.className("cart_item")).size();
                String status4 = (cartItems == 0) ? "PASS" : "FAIL";
                TestReport.add("Cart",name, status4, "Empty cart", String.valueOf(cartItems));
                driver.navigate().to("https://www.saucedemo.com/inventory.html");

                //t6

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }




    // multiple items
    public static void MultipleItems(WebDriver driver, List<Product> products) {
        System.out.println("=== Test Case: Add and remove multiple products ===");

        for (Product product : products) {
            String addButtonId = product.getAddButtonId();
            String removeButtonId = product.getRemoveButtonId();
            String name = product.getName();
            String status;

            try {
                // Add product
                driver.findElement(By.id(addButtonId)).click();

                // Remove product
                driver.findElement(By.id(removeButtonId)).click();

                status = "PASS: Product '" + name + "' added and removed successfully";
            } catch (Exception e) {
                status = "FAIL: Could not add/remove product '" + name + "' - " + e.getMessage();
            }

            System.out.println("Cart Badge Test: " + status);
            TestReport.add("Cart", "MultipleItems - " + name, status.contains("PASS") ? "PASS" : "FAIL",
                    "Add and remove product", status);
        }
    }



}
