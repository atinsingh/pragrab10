package com.bulsoft.zoomautomation.listeners;

import com.bulsoft.zoomautomation.drivermanager.DriverManager;
import com.bulsoft.zoomautomation.utils.CommonUtils;
import com.bulsoft.zoomautomation.utils.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenShotListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        CommonUtils.takeScreenCapture(DriverManager.getInstance().getDriver(),tr.getName(), Constants.FAIL);

    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        CommonUtils.takeScreenCapture(DriverManager.getInstance().getDriver(),tr.getName(), Constants.PASS);
    }
}
