package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
    private final By cartRows = By.cssSelector("#cart_info_table tbody tr");
    private final By emptyCartMessage = By.id("empty_cart");
    private final By proceedToCheckoutButton = By.cssSelector("a.check_out");

    public record CartItem(String name, int price, int quantity, int total) {}

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int parsePrice(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    public boolean isCartEmpty() {
        return isVisible(emptyCartMessage);
    }

    public List<CartItem> getItems() {
        return driver.findElements(cartRows).stream()
                .map(row -> new CartItem(
                        row.findElement(By.cssSelector(".cart_description h4 a")).getText(),
                        parsePrice(row.findElement(By.cssSelector(".cart_price p")).getText()),
                        Integer.parseInt(row.findElement(By.cssSelector(".cart_quantity button")).getText()),
                        parsePrice(row.findElement(By.cssSelector(".cart_total_price")).getText())))
                .toList();
    }

    public CartPage removeItem(String productName) {
        WebElement row = driver.findElement(By.xpath("//tr[.//h4/a[text()='" + productName + "']]"));
        row.findElement(By.cssSelector("a.cart_quantity_delete")).click();
        wait.until(ExpectedConditions.invisibilityOf(row));
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }
}
