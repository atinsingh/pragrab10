package com.bulsoft.zoomautomation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CommonUtils {

    public static void takeScreenCapture(WebDriver driver, String testCaseName, String type){
        Path path;
        try{
            if(type.equals(Constants.PASS)){
                 path = Paths.get("target/screenshot/pass/");
            }else {
                 path = Paths.get("target/screenshot/failure/");
            }

            Files.createDirectories(path);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            if(type.equals(Constants.PASS)){
                Files.copy(new FileInputStream(screenshot),
                        Paths.get("target/screenshot/pass/"+getFileName(testCaseName)));
            }else {
                Files.copy(new FileInputStream(screenshot),
                        Paths.get("target/screenshot/failure/"+getFileName(testCaseName)));
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static String getFileName(String testCaseName){

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileFormat = format.format(new Date());
        return "ScreenShot_"+testCaseName+"_"+fileFormat+".png";
    }

}
