package com.bulsoft.zoomautomation.drivermanager;

import com.bulsoft.zoomautomation.configuration.DriverConfig;
import com.bulsoft.zoomautomation.utils.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverManager {

    private String browerType = DriverConfig.getPropertyValue("browerType");

    private  WebDriver driver;
    private static DriverManager instance;

    private DriverManager(){
        if(browerType.equals(Constants.CHROME)){
            System.setProperty("webdriver.chrome.driver",DriverConfig.getPropertyValue("chrome.driver"));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //capabilities.setPlatform(Platform.MAC);
            capabilities.setBrowserName("chrome");
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities) ;
            }catch (Exception ex){

            }
        }else {
            if(browerType.equals(Constants.FIREFOX)){
                System.setProperty("webdriver.gecko.driver",DriverConfig.getPropertyValue("firefox.driver"));
                driver = new FirefoxDriver();
            }
        }

    }
    public static DriverManager getInstance(){
        if(instance==null){
           instance =   new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
