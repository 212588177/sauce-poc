package com.yourcompany.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select; // Added for select box functionality

public class ContactPage {
    // Gather fields
    @FindBy(id= "Email")
    private WebElement emailBox;
    @FindBy(id="contactUsType")
    private WebElement contactTypeBox;
    @FindBy(id="FirstName")
    private WebElement FirstNameBox;
    @FindBy(id="LastName")
    private WebElement LastNameBox;
    @FindBy(id="Company")
    private WebElement CompanyBox;
    @FindBy(id="Country")
    private WebElement CountryBox;
    @FindBy(id="Industry")
    private WebElement IndustryBox;
    @FindBy(id="Phone")
    private WebElement PhoneBox;
    @FindBy(id="Title")
    private WebElement TitleBox;
    @FindBy(id="Original_Request__c")
    private WebElement CommentsBox;
    // Opt-out button.  Couldn't just target the radio ID, as it's not actually clickable.
    @FindBy(css="label[for='mktoRadio_86342_1']")
    private WebElement OptOutButton;
    
    /* 

    // Drift chat icon -- to clear obstruction
    @FindBy(id="drift-widget")
    private WebElement DriftChatButton;        
    // Drift chat close button
    @FindBy(css="button[aria-label='Dismiss']")
    private WebElement DriftChatClose;


    */

    // Evidon Cookie button
    @FindBy(id= "_evidon-accept-button")
    private WebElement cookieAccept;

    public WebDriver driver;
    public static String url = "https://www.ge.com/digital/lp/sales-contact-me";

    public static ContactPage visitPage(WebDriver driver) {
        ContactPage page = new ContactPage(driver);
        page.visitPage();
        return page;
    }

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitPage() {
        this.driver.get(url);
    }

    // public void followPAOIEndPoint(){ this.driver.get(paoiWebhook);}

    public void acceptCookies(){
        if(cookieAccept.isDisplayed()) {
            cookieAccept.click();
        }
    }

    public void setFormFieldEmail(String text) {
        // 2 Send value to the element
        emailBox.sendKeys(text);
      //  submitButton.click();
    }

    public void setFormFieldFirstName(String text) {
        // 2 Send value to the element
        FirstNameBox.sendKeys(text);
      //  submitButton.click();
    }    

    public void setFormFieldLastName(String text) {
        // 2 Send value to the element
        LastNameBox.sendKeys(text);
      //  submitButton.click();
    }        

    public void setFormFieldCompany(String text) {
        // 2 Send value to the element
        CompanyBox.sendKeys(text);
      //  submitButton.click();
    }        
    
    public void setFormFieldTitle(String text) {
        // 2 Send value to the element
        TitleBox.sendKeys(text);
      //  submitButton.click();
    }            

    public void setFormFieldPhone(String text) {
        // 2 Send value to the element
        PhoneBox.sendKeys(text);
      //  submitButton.click();
    }        
    
    public void setFormFieldComments(String text) {
        // 2 Send value to the element
        CommentsBox.sendKeys(text);
      //  submitButton.click();
    }            

    // Select "Contact Type" option from dropdown
    public void setFormFieldContactType(String text) {
        Select contactDropdown = new Select(contactTypeBox); // Cast DOM element as Select object
        contactDropdown.selectByVisibleText(text);
    }

    public void setFormFieldCountry(String text) {
        Select countryDropdown = new Select(CountryBox); // Cast DOM element as Select object
        countryDropdown.selectByVisibleText(text);
    }
    
    public void setFormFieldIndustry(String text) {
        Select industryDropdown = new Select(IndustryBox); // Cast DOM element as Select object
        industryDropdown.selectByVisibleText(text);
    }    

    public void setFormFieldOptOut() {
            OptOutButton.click();
    }

    public void setFormFieldState(String text) {
        if(driver.findElements(By.id("State")).size() > 0) {
            WebElement stateDropdownBox = driver.findElement(By.id("State"));
            Select stateDropdown = new Select(stateDropdownBox);
            stateDropdown.selectByVisibleText(text);
        }
    }

    public void closeDriftChat() {
        boolean driftIsPresent = driver.findElements(By.id("drift-widget")).size() > 0;
        if(driftIsPresent) {
                driver.switchTo().frame(driver.findElement(By.id("drift-widget")));
                if(driver.findElements(By.cssSelector("button[aria-label='Dismiss']")).size() > 0) {
                    driver.findElement(By.cssSelector("button[aria-label='Dismiss']")).click();
                }
                driver.switchTo().parentFrame();
        }

    }


    public String emailInForm(){
        String inForm = emailBox.getAttribute("value");
        return inForm;
    }
}
