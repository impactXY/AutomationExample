package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    // checkout
    private final By deliveryAddress = By.id("address_delivery");
    private final By commentInput = By.cssSelector("textarea[name='message']");
    private final By placeOrderButton = By.cssSelector("a[href='/payment']");

    // after payment
    private final By orderPlacedHeader = By.cssSelector("h2[data-qa='order-placed']");
    private final By confirmationMessage = By.xpath("//h2[@data-qa='order-placed']/following-sibling::p");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getDeliveryAddress() {
        return getText(deliveryAddress);
    }

    public PaymentPage placeOrder(String comment) {
        type(commentInput, comment);
        click(placeOrderButton);
        return new PaymentPage(driver);
    }


    public boolean isOrderPlacedVisible() {
        return isVisible(orderPlacedHeader);
    }

    public String getConfirmationMessage() {
        return getText(confirmationMessage);
    }

    public HomePage continueAfter() {
        click(continueButton);
        return new HomePage(driver);
    }
}
