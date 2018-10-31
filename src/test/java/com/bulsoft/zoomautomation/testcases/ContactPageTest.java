package com.bulsoft.zoomautomation.testcases;

import com.bulsoft.zoomautomation.configuration.DriverConfig;
import com.bulsoft.zoomautomation.drivermanager.DriverManager;
import com.bulsoft.zoomautomation.listeners.ScreenShotListener;
import com.bulsoft.zoomautomation.pageobjects.ContactSalePage;
import com.bulsoft.zoomautomation.pageobjects.ContactSalesResponsePage;
import com.bulsoft.zoomautomation.pageobjects.MainNavigation;
import com.bulsoft.zoomautomation.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ScreenShotListener.class})
public class ContactPageTest {
    WebDriver driver;
    MainNavigation navigation;
    ContactSalePage contactSalePage;
    ContactSalesResponsePage responsePage;

    @BeforeSuite
    public void setUp(){
        driver = DriverManager.getInstance();
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
        responsePage = contactSalePage.inputEmail("info@dummy.com")
                .inputCompanyName("Osas Enterprize")
                .inputFirstName("Mike")
                .inputLastName("Osas")
                .selectEmployeeCount("251-500")
                .selectCountry("CA")
                .inputPhoneNumber("1236479112")
                .selectState("ON")
                .inputZip("L5M6C7")
                .writeDescription("Please provide info")
                .clickSubmitBtn();

        //CommonUtils.takeScreenCapture(driver,"LoginTest");
        Assert.assertTrue(responsePage.getFormConfirmationMsg().getText().toUpperCase().contains("WOW"));
    }

    @AfterSuite
    public void quit(){
        driver.quit();
    }
}
