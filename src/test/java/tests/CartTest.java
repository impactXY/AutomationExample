package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;
import pages.CartPage.CartItem;

import java.util.List;

public class CartTest extends BaseTest {
    @Test
    public void addProductsToCart() {
        ProductsPage products = new HomePage(getDriver()).goToProducts();
        List<String> names = products.getProductNames();

        CartPage cart = products.addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .viewCartPopup();

        List<CartItem> items = cart.getItems();
        Assert.assertEquals(items.size(), 2, "Cart should contain 2 products");
        Assert.assertEquals(items.get(0).name(), names.get(0), "First product in cart is inaccurate");
        Assert.assertEquals(items.get(1).name(), names.get(1), "Second product in cart is inaccurate");

        for(CartItem item : items) {
            Assert.assertEquals(item.quantity(), 1, "Wrong quantity for " + item.name());
            Assert.assertEquals(item.total(), item.price() * item.quantity(),
                    "Total doesn't match price x quantity for " + item.name());
        }
    }

    @Test
    public void removeProductFromCart() {
        ProductsPage products = new HomePage(getDriver()).goToProducts();
        List<String> names = products.getProductNames();

        CartPage cart = products.addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .viewCartPopup();

        cart.removeItem(names.get(0));

        List<CartItem> items = cart.getItems();
        Assert.assertEquals(items.size(), 1, "Cart should contain 1 product after removal");
        Assert.assertEquals(items.get(0).name(), names.get(1), "Wrong product left in cart");
    }
}
