package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {
    private final By name = By.cssSelector(".product-information h2");
    private final By category = By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]");
    private final By price = By.cssSelector(".product-information span span");
    private final By availability = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Availability')]]");
    private final By condition = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Condition')]]");
    private final By brand = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Brand')]]");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getName()         { return getText(name); }
    public String getCategory()     { return getText(category); }
    public String getPrice()        { return getText(price); }
    public String getAvailability() { return getText(availability); }
    public String getCondition()    { return getText(condition); }
    public String getBrand()        { return getText(brand); }
}
