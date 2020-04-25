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

public class ExperientialDemoPage {
    // Gather fields
    @FindBy(id="mktoForm_4671")
    private WebElement formContainer;
    @FindBy(id= "Email")
    private WebElement emailBox;
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
    @FindBy(css="label[for^='mktoRadio_']")
    private WebElement OptOutButton;
    @FindBy(css="button.mktoButton")
    private WebElement submitButton;

    // Demo Personas - Init to null and populate with helper function later
    private WebElement apmPersonaBox;
    private WebElement mesPersonaBox;

    // Evidon Cookie button
    @FindBy(id= "_evidon-accept-button")
    private WebElement cookieAccept;

    public WebDriver driver;
    public static String url = "https://www.ge.com/digital/lp/apm-demo";

    public static ExperientialDemoPage visitPage(WebDriver driver) {
        ExperientialDemoPage page = new ExperientialDemoPage(driver);
        page.visitPage();
        return page;
    }

    public ExperientialDemoPage(WebDriver driver) {
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
            // Generate dropdown and click element corresponding to text
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

    // Cursory check for any persona boxes.  If found, we will override the null assigned with the class
    public void getPersonaBoxes() {
        // APM Personas
        boolean apmPersonaBoxIsPresent = driver.findElements(By.id("demoPersona")).size() > 0;
        if(apmPersonaBoxIsPresent) {
            this.apmPersonaBox = driver.findElement(By.id("demoPersona"));
        }
        // MES Personas
        boolean mesPersonaBoxIsPresent = driver.findElements(By.id("demoPersonaMES")).size() > 0;
        if(mesPersonaBoxIsPresent) {
            this.mesPersonaBox = driver.findElement(By.id("demoPersonaMES"));
        }
    }

    // Select APM Persona
    public void setAPMPersonaType(String text) {
        Select apmPersonaDropdown = new Select(this.apmPersonaBox); // Cast DOM element as Select object
        apmPersonaDropdown.selectByVisibleText(text);
    }

    // Select MES Persona
    public void setMESPersonaType(String text) {
        Select mesPersonaDropdown = new Select(this.mesPersonaBox); // Cast DOM element as Select object
        mesPersonaDropdown.selectByVisibleText(text);
    }    

    public String emailInForm(){
        String inForm = emailBox.getAttribute("value");
        return inForm;
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public boolean formIsVisible() {
        return formContainer.isDisplayed();
    }
}