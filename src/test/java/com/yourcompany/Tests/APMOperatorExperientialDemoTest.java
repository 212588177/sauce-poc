package com.yourcompany.Tests;

import java.util.concurrent.TimeUnit;
import com.yourcompany.Pages.ExperientialDemoPage;
import com.yourcompany.CustomObjects.ExperientialDemoForm;
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

public class APMOperatorExperientialDemoTest extends TestBase {

    /**
     * Runs a simple test verifying search function.
     * @throws InvalidElementStateException
     */
    @org.testng.annotations.Test(dataProvider = "hardCodedBrowsers")
    public void APMOperatorExperientialDemoTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting  page...");
        //ExperientialDemoPage page = ExperientialDemoPage.visitPage(driver,"https://www.ge.com/digital/lp/apm-demo");         
        ExperientialDemoPage page = new ExperientialDemoPage(driver, "https://www.ge.com/digital/lp/apm-demo");

        if(page.acceptCookies()) {
            this.annotate("Accepting cookies.");
        }

        if(page.closeDriftChat()) {
            this.annotate("Closing Drift chat.");
        }
        
        this.annotate("Setting form field values");
        ExperientialDemoForm theForm = new ExperientialDemoForm(driver, 4671, true, "demoPersona", "Operator");
        theForm.setRedirectBehavior(true);
        theForm.setRedirectDestination("https://www.ge.com/digital/sd/apm-demo/#/");
        theForm.enterTestData();
        theForm.submit();
        
        this.annotate("Asserting that we have been redirected");
        Assert.assertEquals(theForm.hasRedirectedSuccessfully(), true, "Successful Redirect");
        // Assert that we have been redirected
        
        

    }


}