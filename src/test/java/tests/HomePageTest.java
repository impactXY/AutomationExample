package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class HomePageTest {
    private WebDriver driver;


    //accept cookies
    private void acceptConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement consentButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("button.fc-cta-consent")));
            consentButton.click();
        } catch (TimeoutException e) {
            // Popup didn't appear (e.g. outside the EU) - nothing to do
        }
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void homePageLoad() {
        driver.get("https://automationexercise.com");
        acceptConsent();
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
