public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Call the static method directly using ClassName.methodName()
        Inventory.Inventory();
        ProductPage.ProductPage();
        Cart.Cart();
        CheckoutInfo.CheckoutInfo();
        Overview.Overview();

        TestReports t = new TestReports();
        t.print();

    }
}
