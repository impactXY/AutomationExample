package tests;

import base.BaseTest;
import flows.UserFlows;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;

public class ExistingRegisteredUserTest extends BaseTest {
    @Test
    public void registerWithExistingEmail() {
        String name = "Test User";
        String email = UserFlows.uniqueEmail();
        String password = UserFlows.uniquePassword();

        HomePage home = new UserFlows(getDriver()).registerUser(name, email, password);

        SignupLoginPage signupRegister = home.logoutAccount();
        Assert.assertTrue(signupRegister.isNewUserSignupVisible(), "'New User Signup!' not visible");

        signupRegister = signupRegister.signupExpectingError(name, email);
        Assert.assertEquals(signupRegister.getRegisterErrorMessage(), "Email Address already exist!", "Wrong or missing signup error message");

        home = signupRegister.login(email, password); //for deletion of account
        home.deleteAccount();
    }
}
