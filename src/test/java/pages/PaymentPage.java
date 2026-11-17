package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage {
    private final By nameOnCard = By.cssSelector("input[data-qa='name-on-card']");
    private final By cardNumber = By.cssSelector("input[data-qa='card-number']");
    private final By cvc = By.cssSelector("input[data-qa='cvc']");
    private final By expiryMonth = By.cssSelector("input[data-qa='expiry-month']");
    private final By expiryYear = By.cssSelector("input[data-qa='expiry-year']");
    private final By payButton = By.cssSelector("button[data-qa='pay-button']");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage payAndConfirm(String name, String number, String cvcCode,
                                      String month, String year) {
        type(nameOnCard, name);
        type(cardNumber, number);
        type(cvc, cvcCode);
        type(expiryMonth, month);
        type(expiryYear, year);
        click(payButton);
        return new CheckoutPage(driver);
    }
}
