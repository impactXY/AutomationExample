package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage {
    private final By accountDeletedHeader = By.cssSelector("h2[data-qa='account-deleted']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDeletedAccountVisible() {
        return isVisible(accountDeletedHeader);
    }
}
