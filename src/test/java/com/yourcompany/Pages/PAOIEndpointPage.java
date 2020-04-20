package com.yourcompany.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PAOIEndpointPage {

    @FindBy(linkText = "Read our response")
    private WebElement theActiveLink;


    @FindBy(id= "Email")
    private WebElement emailBox;


    @FindBy(id= "_evidon-accept-button")
    private WebElement cookieAccept;

    public WebDriver driver;
    public static String url = "https://www.ge.com/digital/ge_marketo_ajax/paoi";


    public static PAOIEndpointPage visitPage(WebDriver driver) {
        PAOIEndpointPage page = new PAOIEndpointPage(driver);
        page.visitPage();
        return page;
    }

    public PAOIEndpointPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitPage() {
        this.driver.get(url);
    }

    public boolean isOnEndpoint() {
        String title = "GE Digital | Putting industrial data to work.";
        return driver.getTitle() == title;
    }

    public boolean isOnSoft404() {
        String title = "404 | GE Digital";
        return driver.getTitle() == title;
    }
}
