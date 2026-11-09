package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.Paths;

public class ContactUsPage extends BasePage {
    private final By getInTouchHeader = By.xpath("//h2[text()='Get In Touch']");
    private final By nameInput = By.cssSelector("input[data-qa='name']");
    private final By emailInput = By.cssSelector("input[data-qa='email']");
    private final By subjectInput = By.cssSelector("input[data-qa='subject']");
    private final By messageInput = By.cssSelector("textarea[data-qa='message']");
    private final By fileInput = By.cssSelector("input[name='upload_file']");
    private final By submitButton = By.cssSelector("input[data-qa='submit-button']");
    private final By successMessage = By.cssSelector("div.status.alert-success");
    private final By homeButton = By.cssSelector("a.btn-success");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isHeaderVisible(){
        return isVisible(getInTouchHeader);
    }

    public ContactUsPage fillForm(String name, String email, String subject, String message) {
        type(nameInput, name);
        type(emailInput, email);
        type(subjectInput, subject);
        type(messageInput, message);
        return this;
    }

    public ContactUsPage uploadFile(String relativePath) {
        String absolutePath = Paths.get(relativePath).toAbsolutePath().toString();
        driver.findElement(fileInput).sendKeys(absolutePath);
        return this;
    }

    public ContactUsPage submitAndAcceptAlert() {
        click(submitButton);
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        return this;
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public HomePage clickHome() {
        click(homeButton);
        return new HomePage(driver);
    }
}
