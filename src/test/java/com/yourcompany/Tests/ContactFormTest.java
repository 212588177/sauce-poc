package com.yourcompany.Tests;

import java.util.concurrent.TimeUnit;
import com.yourcompany.Pages.ContactPage;
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

        this.annotate("Setting form field values");
        this.setFormFieldValues(page);
        // Waiting for state field to appear
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        page.setFormFieldState("KY");

        // Attempt to submit form
        page.clickSubmitButton();

        // Wait a couple seconds, then assert that form is no longer visible (Successful submit)
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        this.annotate("Asserting that form is no longer visible");
        Assert.assertEquals(page.formIsVisible(), false, "Form visible = false");
        //Assert.assertTrue(page.emailInForm() == emailText);
        String emailText = "test.tester@ge.com";
        Assert.assertEquals(page.emailInForm(), emailText, "Email in Form Element");
    }
    // Push test values to form
    private void setFormFieldValues(ContactPage page) {
        page.setFormFieldContactType("Sales Assistance");
        String emailTestValue = "test.tester@ge.com";
        page.setFormFieldEmail(emailTestValue);
        String firstNameTestValue = "Test";
        page.setFormFieldFirstName(firstNameTestValue);
        String lastNameTestValue = "Testerson";
        page.setFormFieldLastName(lastNameTestValue);
        String companyTestValue = "GE Digital";
        page.setFormFieldCompany(companyTestValue);
        String phoneTestValue = "540.555.5555";
        page.setFormFieldPhone(phoneTestValue);
        String titleTestValue = "Automated Tester";
        page.setFormFieldTitle(titleTestValue);
        page.setFormFieldIndustry("Electronics and Electrical Equipment");
        page.setFormFieldCountry("United States");
        String commentsTestValue = "This is an automated test.  Please disregard.";
        page.setFormFieldComments(commentsTestValue);
        page.setFormFieldOptOut();
    }

}