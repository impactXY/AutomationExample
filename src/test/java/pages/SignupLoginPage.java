package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage extends BasePage {
    private final By newUserSignupHeader = By.xpath("//h2[text()='New User Signup!']");
    private final By nameInput = By.cssSelector("input[data-qa='signup-name']");
    private final By emailInput = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("button[data-qa='signup-button']");

    private final By userLoginHeader = By.xpath("//h2[text()='Login to your account']");
    private final By userLoginEmail = By.cssSelector("input[data-qa='login-email']");
    private final By userLoginPassword = By.cssSelector("input[data-qa='login-password']");
    private final By userLoginButton = By.cssSelector("button[data-qa='login-button']");

    private final By loginErrorMessage = By.xpath("//form[@action='/login']/p");

    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNewUserLoginVisible() {
        return isVisible(newUserSignupHeader);
    }

    public boolean isUserLoginVisible() {
        return isVisible(userLoginHeader);
    }

    public String getLoginErrorMessage() {
        return getText(loginErrorMessage);
    }

    public AccountInfoPage signup(String name, String email) {
        type(nameInput, name);
        type(emailInput, email);
        click(signupButton);
        return new AccountInfoPage(driver);
    }

    public HomePage login(String email, String password) {
        type(userLoginEmail, email);
        type(userLoginPassword, password);
        click(userLoginButton);
        return new HomePage(driver);
    }
}
