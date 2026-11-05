package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IExpectedExceptionsHolder;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By acceptButton = By.cssSelector("button.fc-cta.consent");
    private final By signupLogin = By.cssSelector("a[href='/login']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HomePage open(String url) {
        driver.get(url);
        acceptButtonIfExists();
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickSignupLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLogin)).click();
    }

    private void acceptButtonIfExists() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(acceptButton))
                    .click();
        } catch (TimeoutException ignored) {

        }
    }
}
