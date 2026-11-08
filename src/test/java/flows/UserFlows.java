package flows;

import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.util.Random;

public class UserFlows {
    private final WebDriver driver;

    public UserFlows(WebDriver driver) {
        this.driver = driver;
    }

    public static String uniqueEmail() {
        return "testuser" + System.currentTimeMillis() + "@example.com";
    }

    public static String uniquePassword() {
        return (int)(Math.random() * 101) + "!";
    }

    public HomePage registerUser(String name, String email, String password) {
        return new HomePage(driver)
                .goToLogin()
                .signup(name, email)
                .fillFormAndCreate(password, "Test", "User")
                .clickContinue();
    }
}