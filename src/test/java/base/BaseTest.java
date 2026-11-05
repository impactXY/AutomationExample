package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static final String BASE_URL = "https://automationexercise.com";

    protected WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {
        driver.set(new ChromeDriver());
        getDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}