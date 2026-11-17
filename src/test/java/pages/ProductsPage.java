package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {
    private final By allProductsHeader = By.xpath("//h2[text()='All Products']");
    private final By searchedProductsHeader = By.xpath("//h2[text()='Searched Products']");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By productNames = By.cssSelector(".productinfo p");
    private final By viewProductLinks = By.cssSelector("a[href^='/product_details/']");
    private final By addToCartButtons = By.cssSelector(".productinfo a.add-to-cart");
    private final By continueShoppingButton = By.cssSelector("#cartModal button.close-modal");
    private final By viewCartLink = By.cssSelector("#cartModal a[href='/view_cart']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDefaultHeaderVisible() {
        return isVisible(allProductsHeader);
    }

    public boolean isSearchHeaderVisible() {
        return isVisible(searchedProductsHeader);
    }

    public ProductDetailsPage viewFirstProduct() {
        click(viewProductLinks);
        return new ProductDetailsPage(driver);
    }

    public ProductsPage search(String product) {
        type(searchInput, product);
        click(searchButton);
        isVisible(searchedProductsHeader);
        return this;
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public ProductsPage addProductToCart(int index) {
        WebElement button = driver.findElements(addToCartButtons).get(index);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        return this;
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueShoppingButton));
        return this;
    }

    public CartPage viewCartPopup() {
        click(viewCartLink);
        return new CartPage(driver);
    }
}
