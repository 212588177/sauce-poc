package com.yourcompany.Tests;

import com.yourcompany.Pages.ContactPage;
import com.yourcompany.CustomObjects.ContactUsForm;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.UUID;


/**
 * Created by Swilliams on 3/30/2020.
 */

public class ContactFormTest extends TestBase {

    /**
     * Runs a simple test verifying search function.
     * @throws InvalidElementStateException
     */
    @org.testng.annotations.Test(dataProvider = "hardCodedBrowsers")
    public void contactFormTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting  page...");
        ContactPage page = ContactPage.visitPage(driver);         

        this.annotate("Giving Evidon a chance to load...");        
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        this.annotate("Accept Cookies");
        page.acceptCookies();
        
        this.annotate("Giving Drift a chance to load...");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        this.annotate("Close Drift Chat");
        page.closeDriftChat();           
        
        ContactUsForm theForm = new ContactUsForm(driver, 2189);

        this.annotate("Setting form field values");
        theForm.enterTestData();

        // Attempt to submit form
        theForm.submit();

        // The form should no longer be visible
        this.annotate("Asserting that form is no longer visible");
        Assert.assertEquals(theForm.isVisible(), false, "Form visible = false");

    }

}