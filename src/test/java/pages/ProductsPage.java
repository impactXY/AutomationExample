package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {
    private final By allProductsHeader = By.xpath("//h2[text()='All Products']");
    private final By searchedProductsHeader = By.xpath("//h2[text()='Searched Products']");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By productNames = By.cssSelector(".productinfo p");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDefaultHeaderVisible() {
        return isVisible(allProductsHeader);
    }

    public boolean isSearchHeaderVisible() {
        return isVisible(searchedProductsHeader);
    }

    public ProductsPage search(String product) {
        type(searchInput, product);
        click(searchButton);
        return this;
    }

    public List<String> getSearchedProductNames() {
        isVisible(searchedProductsHeader);
        return driver.findElements(productNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
