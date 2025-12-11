package com.webautomation.tests.testcases;

import com.webautomation.base.BaseTest;
import com.webautomation.pages.HomePage;
import com.webautomation.tests.listeners.ExtentListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setUpTest() {
        setUp();  // initialize WebDriver
        homePage = new HomePage(getDriver());
    }

    @Test(description = "Verify home page heading")
    public void verifyTitle() {
        String title = getDriver().getTitle();
        System.out.println("Heading: " + title);
        Assert.assertFalse(title.isEmpty(), "Heading should not be empty");
    }

    @Test(description = "Verify all visible links are present")
    public void verifyAllLinks() {
        List<String> links = homePage.getAllLinkTexts();
        System.out.println("Links: " + links);
        Assert.assertFalse(links.isEmpty(), "There should be at least one link on the page");
    }

    @Test(description = "Perform search operation")
    public void searchTest() {
        homePage.searchFor("Selenium WebDriver");
        String title = getDriver().getTitle();
        Assert.assertTrue(title.toLowerCase().contains("selenium"), "Title should contain 'selenium'");
    }

    @AfterMethod
    public void tearDownTest() {
        tearDown();  // quit WebDriver
    }
}
