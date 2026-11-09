package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class RegisterUserTest extends BaseTest {
    @Test
    public void registerAndDeleteUser() {
        String name = "Test User";
        String email = "testuser" + System.currentTimeMillis() + "@example.com";

        HomePage home = new HomePage(getDriver()).open(BASE_URL);

        SignupLoginPage singupLogin = home.goToLogin();
        Assert.assertTrue(singupLogin.isNewUserSignupVisible(), "'New User Signup!' not visible");

        AccountInfoPage accountInfo = singupLogin.signup(name, email);
        Assert.assertTrue(accountInfo.isAccountInfoVisible(), "Account info form not visible");

        AccountCreatedPage accountCreate = accountInfo.fillFormAndCreate("Test123!", "Test", "User");
        Assert.assertTrue(accountCreate.isCreatedAccountVisible(), "'Account Created!' not visible");

        home = accountCreate.clickContinue();
        Assert.assertTrue(home.getLoggedInText().contains(name), "Not logged in as " + name);

        AccountDeletedPage accountDelete = home.deleteAccount();
        Assert.assertTrue(accountDelete.isDeletedAccountVisible(), "'Account Deleted!' not visible");
    }
}
