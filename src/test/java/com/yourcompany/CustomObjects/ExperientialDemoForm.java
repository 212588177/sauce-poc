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

public class ExperientialDemoForm extends MktoForm {

    private WebElement personaDropdownBox;
    private String personaSelection;

    public ExperientialDemoForm(WebDriver driver, int id, boolean hasPersona, String personaElement, String personaSelection) {
        super(driver, id);  
        this.buildFormElements();
        if(hasPersona) {
            this.personaDropdownBox = this.driver.findElement(By.id(personaElement));
            this.personaSelection = personaSelection;
        }
    }

    protected void buildFormElements(String personaElement, String personaSelection) {
        super.buildFormElements();
    }

    private void selectPersonaElement() {
        Select personaDropdown = new Select(this.personaDropdownBox); 
        personaDropdown.selectByVisibleText(this.personaSelection);
    }

    public void enterTestData() {
        super.enterTestData();
        selectPersonaElement();        
    }

}