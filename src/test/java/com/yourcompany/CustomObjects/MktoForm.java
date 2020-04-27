//
// MktoForm provides an extensible class representing form classes found throuhgout
// GE Digital's web infrastructure.  All Marketo Forms contain basic fields; while
// subclasses may contain additional fields.
//

package com.yourcompany.CustomObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select; // Added for select box functionality
import org.openqa.selenium.TimeoutException;
import java.util.ArrayList;

public class MktoForm {

    protected int id; // Marketo Form ID
    protected WebDriver driver; // Will be handed down by test.  This is the instantiated page.
    protected boolean redirect; // Is this form expected to redirect post-submit?
    protected String redirectDestination; // (For redirect forms) -- End redirect result
    protected WebElement container; // Marketo form container in DOM

    private WebElement emailBox; 
    private WebElement firstNameBox; 
    private WebElement lastNameBox;
    private WebElement countryBox;
    private WebElement stateBox;
    private WebElement companyBox;
    private WebElement industryBox;
    private WebElement phoneBox;
    private WebElement titleBox;
    private WebElement optOutButton;
    private WebElement submitButton;



    public MktoForm(WebDriver driver, int id) {
        this.id = id;
        this.driver = driver;
        this.container = driver.findElement(By.id("mktoForm_"+id));        
        buildFormElements();
    }

    // load each form element from page instance
    protected void buildFormElements() {
        buildEmailElement();
        buildFirstNameElement();
        buildLastNameElement();
        buildCountryElement();
        buildCompanyElement();
        buildIndustryElement();
        buildPhoneElement();
        buildTitleElement();
        buildOptOutElement();
        buildSubmitButton();
    }

    // Push default test data to form
    public void enterTestData() {
        emailBox.sendKeys("test.tester@ge.com");
        firstNameBox.sendKeys("Test");
        lastNameBox.sendKeys("Testerson");
        companyBox.sendKeys("GE Digital");
        phoneBox.sendKeys("5555555555");
        titleBox.sendKeys("Automated Tester");
        Select countryDropdown = new Select(this.countryBox); // Cast DOM element as Select object
        countryDropdown.selectByVisibleText("United States");   
        buildStateElement();  // State only became visible when United States was selected.  So build it.
        Select stateDropdown = new Select(this.stateBox);
        stateDropdown.selectByVisibleText("KY");
        Select industryDropdown = new Select(this.industryBox);
        industryDropdown.selectByVisibleText("Electronics and Electrical Equipment");
        optOutButton.click();   
    }

    public void submit() {
        submitButton.click();        
    }

    public boolean isVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.invisibilityOf(this.container));
            return false;
        }
        catch(TimeoutException e) {
            return true;
        }
    }

    public boolean hasRedirectedSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 10);
            wait.until(ExpectedConditions.urlToBe(this.redirectDestination));
            return true;
        }
        catch(TimeoutException e) {
            return false;
        }
    }

    public void setRedirectBehavior(boolean setting) {
        this.redirect = setting;
    }

    public void setRedirectDestination(String destination) {
        this.redirectDestination = destination;
    }

    private void buildEmailElement() {
        this.emailBox = driver.findElement(By.id("Email"));
    }

    private void buildFirstNameElement() {
        this.firstNameBox = driver.findElement(By.id("FirstName"));
    }

    private void buildLastNameElement() {
        this.lastNameBox = driver.findElement(By.id("LastName"));
    }

    private void buildCountryElement() {
        this.countryBox = driver.findElement(By.id("Country"));
    }

    private void buildCompanyElement() {
        this.companyBox = driver.findElement(By.id("Company"));
    }

    private void buildStateElement() {
        this.stateBox = driver.findElement(By.id("State"));
    }    

    private void buildIndustryElement() {
        this.industryBox = driver.findElement(By.id("Industry"));
    }

    private void buildPhoneElement() {
        this.phoneBox = driver.findElement(By.id("Phone"));
    }

    private void buildTitleElement() {
        this.titleBox = driver.findElement(By.id("Title"));
    }

    private void buildOptOutElement() {
        this.optOutButton = driver.findElement(By.cssSelector("label[for^='mktoRadio_']"));
    }

    private void buildSubmitButton() {
        this.submitButton = driver.findElement(By.className("mktoButton"));
    }


}