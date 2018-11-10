package com.bulsoft.zoomautomation.testcases;

import com.bulsoft.zoomautomation.configuration.DriverConfig;
import com.bulsoft.zoomautomation.drivermanager.DriverManager;
import com.bulsoft.zoomautomation.listeners.ScreenShotListener;
import com.bulsoft.zoomautomation.pageobjects.ContactSalePage;
import com.bulsoft.zoomautomation.pageobjects.ContactSalesResponsePage;
import com.bulsoft.zoomautomation.pageobjects.MainNavigation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

@Listeners({ScreenShotListener.class})
public class ContactPageTest {
    Logger logger = LogManager.getLogger(ContactPageTest.class);
    WebDriver driver;
    MainNavigation navigation;
    ContactSalePage contactSalePage;
    ContactSalesResponsePage responsePage;

    @BeforeSuite
    public void setUp(){
        Path path = Paths.get("src","test","resources", "log4j2.xml");
        System.setProperty("log4j.configurationFile",path.toString());
        driver = DriverManager.getInstance().getDriver();
        driver.get(DriverConfig.getPropertyValue("site.url"));
        navigation = new MainNavigation(driver);
    }

    @Test
    public void testcase1(){
        contactSalePage= navigation.clickOnContactSalesLink();
        Assert.assertEquals(driver.getCurrentUrl(),"https://zoom.us/contactsales");
    }

    @Test
    public void testcase2(){
        String [] arr = {"info@dummy.com", "Osas Enterprize", "Mike", "Osas", "1234553", "CA "};
        logger.info("Test Data - Email - {} , Company - {} , FirstName -{} ", arr[0], arr[1],arr[2]);
         contactSalePage.inputEmail(arr[0])
                .inputCompanyName(arr[1])
                .inputFirstName(arr[2])
                .inputLastName(arr[3])
                .selectEmployeeCount("251-500")
                .selectCountry("CA")
                .inputPhoneNumber("1236479112")
                .selectState("ON")
                .inputZip("L5M6C7")
                .writeDescription("Please provide info");

        //CommonUtils.takeScreenCapture(driver,"LoginTest");
        //Assert.assertTrue(responsePage.getFormConfirmationMsg().getText().toUpperCase().contains("WOW"));
    }

    @Test(dependsOnMethods ="testcase2" )
    public void jsTestcase(){
        WebElement btn = driver.findElement(By.id("btnSubmit"));
        ((JavascriptExecutor)driver).executeScript(
             "arguments[0].click()", btn);
    }

    @Test
    public void scrollToview(){
        WebElement element = driver.findElement(By.cssSelector(".btn-cta-wrapper>.cta-bgyellow"));
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    @Test
    public void changeNavBar(){
        String script = "$('.top-contactsales').css({\\\"color\\\":\\\"red\\\"})";
        logger.info("Running Java script excecutor script = {} ", script);
        ((JavascriptExecutor)driver).executeScript(script);

    }

//    @AfterSuite
//    //public void quit(){
//        driver.quit();
//    }
}
