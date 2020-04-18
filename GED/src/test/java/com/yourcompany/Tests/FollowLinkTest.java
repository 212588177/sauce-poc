package com.yourcompany.Tests;

import com.yourcompany.Pages.GuineaPigPage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

/**
 * Edited by Swilliams on 3/30/2020.
 */

public class FollowLinkTest extends TestBase {

    /**
     * Runs a simple test verifying link can be followed.
     *
     * @throws InvalidElementStateException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void verifyContactLinkTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting Page...");
        GuineaPigPage page = GuineaPigPage.visitPage(driver);

//        this.annotate("Accept Cookies");
//        page.acceptCookies();

        this.annotate("Clicking on link...");
        page.followContactLink();

        this.annotate("Asserting that we are on a new page...");
        Assert.assertFalse(page.isOnHomePage());

    }

}