package com.bulsoft.zoomautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactSalesResponsePage extends BasePageObject {

    @FindBy(css = "div.mini-layout-body>p")
    WebElement formConfirmationMsg;

    public ContactSalesResponsePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public WebElement getFormConfirmationMsg() {
        return formConfirmationMsg;
    }
}
