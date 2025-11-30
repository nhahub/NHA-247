import java.util.ArrayList;
import java.util.List;

public class ProductExporter {

    public static List<Product> importProducts() {
        List<Product> products = new ArrayList<>();

        // Format: Product(name, price, description, addBtnId, removeBtnId, imageUrl)
        products.add(new Product(
                "Sauce Labs Backpack",
                "$29.99",
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "add-to-cart-sauce-labs-backpack",
                "remove-sauce-labs-backpack",
                "https://www.saucedemo.com/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg"
        ));

        products.add(new Product(
                "Sauce Labs Bike Light",
                "$9.99",
                "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                "add-to-cart-sauce-labs-bike-light",
                "remove-sauce-labs-bike-light",
                "https://www.saucedemo.com/static/media/bike-light-1200x1500.1c9a4f0d.jpg"
        ));

        products.add(new Product(
                "Sauce Labs Bolt T-Shirt",
                "$15.99",
                "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                "add-to-cart-sauce-labs-bolt-t-shirt",
                "remove-sauce-labs-bolt-t-shirt",
                "https://www.saucedemo.com/static/media/bolt-t-shirt-1200x1500.c0dae290.jpg"
        ));

        products.add(new Product(
                "Sauce Labs Fleece Jacket",
                "$49.99",
                "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                "add-to-cart-sauce-labs-fleece-jacket",
                "remove-sauce-labs-fleece-jacket",
                "https://www.saucedemo.com/static/media/fleece-jacket-1200x1500.0f1a2d3.jpg"
        ));

        products.add(new Product(
                "Sauce Labs Onesie",
                "$7.99",
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                "add-to-cart-sauce-labs-onesie",
                "remove-sauce-labs-onesie",
                "https://www.saucedemo.com/static/media/onesie-1200x1500.0e3f4a2.jpg"
        ));

        products.add(new Product(
                "Test.allTheThings() T-Shirt (Red)",
                "$15.99",
                "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.",
                "add-to-cart-test.allthethings()-t-shirt-(red)",
                "remove-test.allthethings()-t-shirt-(red)",
                "https://www.saucedemo.com/static/media/red-tshirt-1200x1500.2b1c3d4.jpg"
        ));
        return products;
    }
}