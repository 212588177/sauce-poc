package com.yourcompany.Tests;

import com.yourcompany.Pages.PAOIEndpointPage;
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

public class PAOIEndpointTest extends TestBase {

    /**
     * Runs a simple test verifying link can be followed.
     *
     * @throws InvalidElementStateException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void verifyPAOIEndpointTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting Page...");
        PAOIEndpointPage page = PAOIEndpointPage.visitPage(driver);

        this.annotate("Asserting that we are not on a soft 404.");
        Assert.assertFalse(page.isOnSoft404());

    }

}