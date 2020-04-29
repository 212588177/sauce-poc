package com.yourcompany.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select; // Added for select box functionality

public class ExperientialDemoPage {


    // Evidon Cookie button
    @FindBy(id= "_evidon-accept-button")
    private WebElement cookieAccept;

    public WebDriver driver;
    public String url = "https://www.ge.com/digital/lp/apm-demo";

/*    public static ExperientialDemoPage visitPage(WebDriver driver) {
        ExperientialDemoPage page = new ExperientialDemoPage(driver, url);
        page.visitPage();
        return page;
    } */

    public ExperientialDemoPage(WebDriver driver, String url) {
        this.driver = driver;       
        this.url = url;
        PageFactory.initElements(driver, this);
        visitPage();        
    }

    public void visitPage() {
        this.driver.get(url);
    }

    public boolean acceptCookies(){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_evidon-accept-button")));
            WebElement cookieAccept = driver.findElement(By.id("_evidon-accept-button"));
            cookieAccept.click();
            return true;
        }
        catch(TimeoutException e) {
            return false;
        }        
    }

    public boolean closeDriftChat() {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("drift-widget")));
                driver.switchTo().frame(driver.findElement(By.id("drift-widget"))); // Drift stuff lives in an iFrame
                WebElement driftClose = driver.findElement(By.cssSelector("button[aria-label='Dismiss']"));            
                driftClose.click();
                driver.switchTo().parentFrame(); // Return to parent frame
            return true;
        }
        catch(TimeoutException e) {
            return false;
        }
    }

}
