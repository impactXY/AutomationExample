package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AccountInfoPage extends BasePage {
    private final By header = By.xpath("//b[text()='Enter Account Information']");
    private final By titleMr = By.id("id_gender1");
    private final By password = By.cssSelector("input[data-qa='password']");
    private final By days = By.cssSelector("select[data-qa='days']");
    private final By months = By.cssSelector("select[data-qa='months']");
    private final By years = By.cssSelector("select[data-qa='years']");
    private final By newsletter = By.id("newsletter");
    private final By specialOffers = By.id("optin");
    private final By firstName = By.cssSelector("input[data-qa='first_name']");
    private final By lastName = By.cssSelector("input[data-qa='last_name']");
    private final By company = By.cssSelector("input[data-qa='company']");
    private final By address = By.cssSelector("input[data-qa='address']");
    private final By country = By.cssSelector("select[data-qa='country']");
    private final By state = By.cssSelector("input[data-qa='state']");
    private final By city = By.cssSelector("input[data-qa='city']");
    private final By zipcode = By.cssSelector("input[data-qa='zipcode']");
    private final By mobile = By.cssSelector("input[data-qa='mobile_number']");
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");


    public AccountInfoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountInfoVisible() {
        return isVisible(header);
    }

    public AccountCreatedPage fillFormAndCreate(String pass, String first, String last) {
        click(titleMr);
        type(password, pass);
        new Select(driver.findElement(days)).selectByValue("10");
        new Select(driver.findElement(months)).selectByValue("5");
        new Select(driver.findElement(years)).selectByValue("1995");
        click(newsletter);
        click(specialOffers);
        type(firstName, first);
        type(lastName, last);
        type(company, "Test Company");
        type(address, "Strada Testului 1");
        new Select(driver.findElement(country)).selectByVisibleText("Canada");
        type(state, "Ontario");
        type(city, "Toronto");
        type(zipcode, "M5V 2T6");
        type(mobile, "0712345678");
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }
}
