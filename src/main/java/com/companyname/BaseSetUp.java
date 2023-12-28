package com.companyname;

import java.io.File;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.companyname.reportmanager.ExtentManager;
import com.companyname.reportmanager.Logger;

public class BaseSetUp {

    public static ITestContext context;
    protected ExtentReports extent;
    @BeforeSuite
    public void init(ITestContext testContext) {
        context = testContext;
        String suitename = context.getSuite().getName();
        if(suitename.equalsIgnoreCase("Default Suite")){
            extent = ExtentManager.getReporter(this.getClass().getSimpleName());
        }else{
            extent =ExtentManager.getReporter(suitename);
        }
        // code to be added to create instance of report object in DB(mongo)
    }

    @AfterSuite
    public void tearDown(){
        
        Logger.endTest();
        System.out.println("Report name: "+ ExtentManager.getReportName());

        try {
            File folder = new File(System.getProperty("user.dir"));
            File[] fList = folder.listFiles();
            // Search all the files that matches the specific text
            for (File file : fList) {
                if (file.getName().startsWith(this.getClass().getSimpleName())) {
                    System.out.println("File deleted");
                   // file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
