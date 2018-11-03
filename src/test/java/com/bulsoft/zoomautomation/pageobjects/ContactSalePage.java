package com.bulsoft.zoomautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactSalePage extends BasePageObject {

    @FindBy(css = "#email")
    WebElement emailInput;

    @FindBy(css = "#company")
    WebElement companyNameInput;

    @FindBy(css = "#first_name")
    WebElement firstNameInput;

    @FindBy(css = "#last_name")
    WebElement lastNameInput;

    @FindBy(css = "#employee_count")
    WebElement employeeCountSelect;

    @FindBy(css = "#phone")
    WebElement phoneNumberInput;

    @FindBy(css = "#country")
    WebElement countrySelect;

    @FindBy(css = "#state")
    WebElement stateProvinceSelect;

    @FindBy(css = "#description")
    WebElement additionalInfoTxtArea;

    @FindBy(css = "#city")
    WebElement zipCode;

    @FindBy(css = "#btnSubmit")
    WebElement submitBtn;

    public ContactSalePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public ContactSalePage inputEmail(String email){
        this.emailInput.sendKeys(email);
        return this;
    }

    /**
     * This will take company name as an String and will
     * populate the conpany name input box
     * @param companyName
     * @return
     */
    public ContactSalePage inputCompanyName(String companyName){
        this.companyNameInput.sendKeys(companyName);
        return  this;
    }

    public ContactSalePage inputFirstName(String firstName){
       this.firstNameInput.sendKeys(firstName);
       return  this;
    }

    public ContactSalePage inputLastName(String lastName){
        this.lastNameInput.sendKeys(lastName);
        return  this;
    }

    public ContactSalePage selectEmployeeCount(String value){
        selectByValue(this.employeeCountSelect,value);
        return this;
    }

    public ContactSalePage selectCountry(String value){
        selectByValue(this.countrySelect,value);
        return  this;
    }
    public ContactSalePage selectState(String value){
        selectByValue(this.stateProvinceSelect,value);
        return  this;
    }

    public ContactSalePage inputPhoneNumber(String phone){
        this.phoneNumberInput.sendKeys(phone);
        return this;
    }

    public ContactSalePage writeDescription(String desciption){
        this.additionalInfoTxtArea.sendKeys(desciption);
        return this;
    }

    public ContactSalePage inputZip(String zip){
        this.zipCode.sendKeys(zip);
        return this;
    }


    public ContactSalesResponsePage clickSubmitBtn(){
        this.submitBtn.click();
        return new ContactSalesResponsePage(driver);
    }


    private void selectByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }




}
