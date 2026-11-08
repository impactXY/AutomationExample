package tests;

import base.BaseTest;
import flows.UserFlows;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;

public class WrongLoginUserTest extends BaseTest {
    @Test
    public void loginWithInvalidCredentials() {
        SignupLoginPage signupLogin = new HomePage(getDriver()).goToLogin();
        Assert.assertTrue(signupLogin.isUserLoginVisible(), "'Login to your account' not visible");

        signupLogin.login(UserFlows.uniqueEmail(), "Password123"); //invalid email

        Assert.assertEquals(signupLogin.getLoginErrorMessage(), "Your email or password is incorrect!",
                "Wrong or missing login error message");
        Assert.assertTrue(signupLogin.isUserLoginVisible(), "User left the login page after invalid login");
    }
}
