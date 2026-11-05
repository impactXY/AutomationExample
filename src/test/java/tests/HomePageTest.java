package tests;

import base.BaseTest;
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
import pages.HomePage;

import java.time.Duration;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageLoad() {
        HomePage home = new HomePage(getDriver()).open(BASE_URL);
        Assert.assertEquals(home.getTitle(), "Automation Exercise");
    }
}
