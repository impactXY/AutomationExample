package tests;

import base.BaseTest;
import flows.UserFlows;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;

public class PlaceOrderTest extends BaseTest {
    @Test
    public void placeOrderAsUser() {
        String name = "Test User";
        String email = UserFlows.uniqueEmail();
        String pass = UserFlows.uniquePassword();

        HomePage home = new UserFlows(getDriver()).registerUser(name, email, pass);

        CheckoutPage checkout = home.goToProducts()
                .addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .viewCartPopup()
                .proceedToCheckout();

        String address = checkout.getDeliveryAddress();
        Assert.assertTrue(address.contains("Test User"), "Delivery address has wrong name: " + address);
        Assert.assertTrue(address.contains("Toronto"), "Delivery address has wrong city: " + address);

        checkout = checkout.placeOrder("Please deliver after 5 PM.")
                .payAndConfirm("Test User", "4111111111111111", "123", "12", "2030");


        Assert.assertTrue(checkout.isOrderPlacedVisible(), "'Order Placed!' not visible");
        Assert.assertEquals(checkout.getConfirmationMessage(),
                "Congratulations! Your order has been confirmed!",
                "Wrong or missing order conf message");

        checkout.continueAfter().deleteAccount();
    }
}
