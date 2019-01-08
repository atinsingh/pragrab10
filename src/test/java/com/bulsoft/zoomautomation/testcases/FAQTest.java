package com.bulsoft.zoomautomation.testcases;

import com.bulsoft.zoomautomation.configuration.DriverConfig;
import com.bulsoft.zoomautomation.drivermanager.DriverManager;
import com.bulsoft.zoomautomation.listeners.ScreenShotListener;
import com.bulsoft.zoomautomation.pageobjects.FAQComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners({ScreenShotListener.class})
public class FAQTest {

    WebDriver webDriver  = DriverManager.getInstance().getDriver();
    FAQComponent component;

    @BeforeSuite
    public void setUp(){
        webDriver.get(DriverConfig.getPropertyValue("site.url"));
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        component = new FAQComponent(webDriver);
    }

    @Test
    public void tc1(){
        String errorMessage = component.searchQuestion(" ").getErrorMessage().getText();
        Assert.assertTrue(errorMessage.contains("Sorry"));
        //component.clickOnPopularQuestion().clickOnQuestionByIndex(3);
    }
    @Test
    public void tc2(){
        component.searchQuestion("auto").clickOnPopularQuestion().clickOnQuestionByIndex(2);

    }


}
