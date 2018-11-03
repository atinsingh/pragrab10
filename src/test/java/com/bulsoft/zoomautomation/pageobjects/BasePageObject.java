package com.bulsoft.zoomautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.print.Pageable;

public class BasePageObject {

    WebDriver driver;

    public BasePageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
