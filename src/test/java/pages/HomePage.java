package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By signupLogin = By.cssSelector("a[href='/login']");
    private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private final By logoutAccountLink = By.cssSelector("a[href='/logout']");
    private final By contactUsLink = By.cssSelector("a[href='/contact_us']");
    private final By productsLink = By.cssSelector("a[href='/products']");

    private final By loggedInAs = By.xpath("//a[contains(text(), 'Logged in as')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open(String url) {
        driver.get(url);
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public SignupLoginPage goToLogin() {
        click(signupLogin);
        return new SignupLoginPage(driver);
    }

    public String getLoggedInText() {

        return getText(loggedInAs);
    }

    public boolean isLoggedIn() {
        return !driver.findElements(loggedInAs).isEmpty();
    }

    public SignupLoginPage logoutAccount() {

        click(logoutAccountLink);
        return new SignupLoginPage(driver);
    }

    public AccountDeletedPage deleteAccount() {
        click(deleteAccountLink);
        return new AccountDeletedPage(driver);
    }

    public ContactUsPage goToContactUs() {
        click(contactUsLink);
        return new ContactUsPage(driver);
    }

    public ProductsPage goToProducts() {
        click(productsLink);
        return new ProductsPage(driver);
    }
}
