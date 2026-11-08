package tests;

import base.BaseTest;
import flows.UserFlows;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class LoginUserTest extends BaseTest {
    @Test
    public void loginWithValidCredentials() {
        String name = "Test User";
        String email = UserFlows.uniqueEmail();
        String password = UserFlows.uniquePassword();

        HomePage home = new UserFlows(getDriver()).registerUser(name, email, password);

        SignupLoginPage signupLogin = home.logoutAccount();
        Assert.assertTrue(signupLogin.isUserLoginVisible(), "'Login to your account' not visible");

        home = signupLogin.login(email, password); //valid email
        Assert.assertTrue(home.getLoggedInText().contains(name), "Not logged in as " + name);

        AccountDeletedPage deleted = home.deleteAccount();
        Assert.assertTrue(deleted.isDeletedAccountVisible(), "'Account Deleted!' not visible");
    }
}
