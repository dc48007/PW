package com.companyname.reportmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extentReports;
    private static String reportName;

    /*
     * Method to get Extent Report instance
     */
    public static synchronized ExtentReports getReporter(String className) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        String filepath = className + "." + sdf.format(new Date(System.currentTimeMillis())) + ".html";
        if (extentReports == null) {
            
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filepath);
            extentReports = new ExtentReports();
            
            extentReports.attachReporter(sparkReporter);
            reportName = filepath;
        }
        return extentReports;
    }

    public static ExtentReports getExtentReports(){
        return extentReports;
    }
    public static String getReportName(){
        return reportName;
    }
}
