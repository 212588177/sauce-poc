package com.yourcompany.Tests;

import java.util.concurrent.TimeUnit;
import com.yourcompany.Pages.ExperientialDemoPage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.UUID;


/**
 * Created by Swilliams on 3/30/2020.
 */

public class ExperientialDemoTest extends TestBase {

    /**
     * Runs a simple test verifying search function.
     * @throws InvalidElementStateException
     */
    @org.testng.annotations.Test(dataProvider = "hardCodedBrowsers")
    public void ExperientialDemoTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting  page...");
        ExperientialDemoPage page = ExperientialDemoPage.visitPage(driver);         

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

        // Attempt to submit form
        page.clickSubmitButton();
        
        this.annotate("Waiting for demo redirect...");

        // Assert that we have been redirected
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlToBe("https://www.ge.com/digital/sd/apm-demo/#/"));
        

    }
    // Push test values to form
    private void setFormFieldValues(ExperientialDemoPage page) {
        String emailTestValue = "test.tester@ge.com";
        page.setFormFieldEmail(emailTestValue);
        String firstNameTestValue = "Test";
        page.setFormFieldFirstName(firstNameTestValue);
        String lastNameTestValue = "Testerson";
        page.setFormFieldLastName(lastNameTestValue);
        page.setFormFieldCountry("United States");
        page.setFormFieldState("KY");        
        String companyTestValue = "GE Digital";
        page.setFormFieldCompany(companyTestValue);
        String phoneTestValue = "540.555.5555";
        page.setFormFieldPhone(phoneTestValue);
        String titleTestValue = "Automated Tester";
        page.setFormFieldTitle(titleTestValue);
        page.getPersonaBoxes();
        page.setAPMPersonaType("Operator");
        page.setFormFieldIndustry("Electronics and Electrical Equipment");


        String commentsTestValue = "This is an automated test.  Please disregard.";
        page.setFormFieldComments(commentsTestValue);
        page.setFormFieldOptOut();
    }

}