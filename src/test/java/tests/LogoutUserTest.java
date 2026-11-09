package tests;

import base.BaseTest;
import flows.UserFlows;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountDeletedPage;
import pages.HomePage;
import pages.SignupLoginPage;

public class LogoutUserTest extends BaseTest {
    @Test
    public void logoutRegisteredUser() {
        String name = "Test User";
        String email = UserFlows.uniqueEmail();
        String password = UserFlows.uniquePassword();

        HomePage home = new UserFlows(getDriver()).registerUser(name, email, password);

        SignupLoginPage signupLogin = home.logoutAccount();
        Assert.assertFalse(new HomePage(getDriver()).isLoggedIn(), "User still shown as logged in after logout");

        home = signupLogin.login(email, password);
        home.deleteAccount();
    }
}
