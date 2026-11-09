package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends BaseTest {
    @Test
    public void submitContactForm() {
        ContactUsPage contactUs = new HomePage(getDriver()).goToContactUs();
        Assert.assertTrue(contactUs.isHeaderVisible(), "'Get In Touch' not visible");

        contactUs.fillForm("Test User", "testuser1@example.com", "Test Subject", "This is the test message")
                .uploadFile("src/test/resources/upload.txt")
                .submitAndAcceptAlert();

        Assert.assertEquals(contactUs.getSuccessMessage(),
                "Success! Your details have been submitted successfully.",
                "Wrong or missing success message");

        HomePage home = contactUs.clickHome();
        Assert.assertEquals(home.getTitle(), "Automation Exercise", "Not back on the home page");
    }
}
