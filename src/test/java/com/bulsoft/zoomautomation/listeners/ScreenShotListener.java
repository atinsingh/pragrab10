package com.bulsoft.zoomautomation.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.bulsoft.zoomautomation.drivermanager.DriverManager;
import com.bulsoft.zoomautomation.reports.HTMLReports;
import com.bulsoft.zoomautomation.utils.CommonUtils;
import com.bulsoft.zoomautomation.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenShotListener extends TestListenerAdapter {

    private ExtentTest test;
    private static final Logger logger = LogManager.getLogger(ScreenShotListener.class);

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        test.log(Status.FAIL,"Failed Test case");
        CommonUtils.takeScreenCapture(DriverManager.getInstance().getDriver(),tr.getName(), Constants.FAIL);

    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        test.log(Status.PASS,"Passed with good result");
        CommonUtils.takeScreenCapture(DriverManager.getInstance().getDriver(),tr.getName(), Constants.PASS);
        try {
            logger.info("Screenshot File name -> {}",CommonUtils.getScreenShotPath(tr.getName(),Constants.PASS));
            test.addScreenCaptureFromPath(CommonUtils.getScreenShotPath(tr.getName(),Constants.PASS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        test = HTMLReports.getInstance().getReports().createTest(result.getName());

    }

    @Override
    public void onFinish(ITestContext testContext) {
        HTMLReports.getInstance().getReports().flush();
        super.onFinish(testContext);
    }
}
