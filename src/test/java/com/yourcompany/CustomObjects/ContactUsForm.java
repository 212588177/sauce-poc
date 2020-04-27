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
import java.util.ArrayList;

public class ContactUsForm extends MktoForm {

    private WebElement contactUsReasonBox;
    private WebElement commentsBox;

    public ContactUsForm(WebDriver driver, int id) {
        super(driver, id);  
        this.buildFormElements();
    }

    protected void buildFormElements() {
        super.buildFormElements();
        buildContactUsReasonElement();
        buildCommentsBoxElement();
    }

    private void buildContactUsReasonElement() {
        this.contactUsReasonBox = driver.findElement(By.id("contactUsType"));
    }

    private void buildCommentsBoxElement() {
        this.commentsBox = driver.findElement(By.id("Original_Request__c"));
    }

    public void enterTestData() {
        super.enterTestData();
        setContactUsReasonDropdown();
        setCommentsText();
    }

    private void setContactUsReasonDropdown() {
        Select contactDropdown = new Select(this.contactUsReasonBox); // Cast DOM element as Select object
        contactDropdown.selectByVisibleText("Sales Assistance");
    }

    private void setCommentsText() {
        commentsBox.sendKeys("This is an automated test.  Please disregard.");
    }


}