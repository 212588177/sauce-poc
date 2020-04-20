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
import java.util.ArrayList;

public class MktoForm {

    private int id;
    private WebElement container;
    private ContactPage page;
    private boolean redirect;
    private ArrayList<WebElement> elements;

    MktoForm(ContactPage page, int id) {
        this.id = id;
        this.page = page;
        parseFormElements();
    }

    private void parseFormElements() {

    }

}