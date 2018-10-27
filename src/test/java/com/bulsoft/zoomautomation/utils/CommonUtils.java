package com.bulsoft.zoomautomation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

public class CommonUtils {

    public static void takeScreenCapture(WebDriver driver, String testCaseName){

        try{
            Path path = Paths.get("target/screenshot/failure/");
            Files.createDirectories(path);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(new FileInputStream(screenshot),
                    Paths.get("target/screenshot/failure/"+getFileName(testCaseName)));

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static String getFileName(String testCaseName){
        return "ScreenShot_"+testCaseName+"_"+ LocalDate.now()+"_"+ LocalTime.now()+".png";
    }

}
