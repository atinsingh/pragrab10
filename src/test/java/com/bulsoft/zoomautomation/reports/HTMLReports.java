package com.bulsoft.zoomautomation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.bulsoft.zoomautomation.utils.CommonUtils;

import java.nio.file.Paths;

public class HTMLReports {

    private ExtentHtmlReporter htmlReporter;
    private ExtentReports reports;
    private static HTMLReports instance;

    private HTMLReports(){
        htmlReporter = new ExtentHtmlReporter(CommonUtils.getReportFileName());
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    public static HTMLReports getInstance() {
        if(instance==null){
            instance = new HTMLReports();
        }
        return instance;
    }

    public ExtentReports getReports() {
        return reports;
    }
}
