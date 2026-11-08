package base;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static final String BASE_URL = "https://automationexercise.com";

    protected WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {
        ChromeDriver chrome = new ChromeDriver();
        blockAds(chrome);
        driver.set(chrome);
        getDriver().manage().window().maximize();
        getDriver().get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    private void blockAds(ChromeDriver chrome) {
        chrome.executeCdpCommand("Network.enable", Map.of());
        chrome.executeCdpCommand("Network.setBlockedURLs", Map.of("urls", List.of(
                "*googlesyndication.com*",
                "*doubleclick.net*",
                "*googleadservices.com*",
                "*adservice.google.com*",
                "*adtrafficquality.google*"
        )));
    }
}