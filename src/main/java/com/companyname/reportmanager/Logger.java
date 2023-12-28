package com.companyname.reportmanager;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import freemarker.template.utility.NullArgumentException;

public class Logger {
    public static ConcurrentHashMap<Long, ExtentTest> extentMap = new ConcurrentHashMap<>();

    public Logger() {
    }

    /*
     * Method to create extentTest
     */
    public static synchronized ExtentTest startTest(String testName, String description) {

        long threadId = Thread.currentThread().getId();
        if (extentMap.contains(threadId)) {
            return extentMap.get(threadId);
        }
        System.out.println("Started Test: " + testName + " Time: " + getTimeStamp());
        ExtentReports reports = ExtentManager.getExtentReports();
        if(reports==null){
            throw new NullArgumentException();
        }
        ExtentTest extentTest = ExtentManager.getExtentReports().createTest(testName, description);
        extentMap.put(Thread.currentThread().getId(), extentTest);
        try {
            getTest().log(Status.INFO, "Test run by: "+System.getProperty("user.name"));
            InetAddress localMachine = InetAddress.getLocalHost();
            getTest().log(Status.INFO, "Test Machine name: "+localMachine.getCanonicalHostName());
            
        } catch (Exception var7) {
            getTest().log(Status.INFO, "Test machine name "+ "Exception");
        }
        return extentTest;
    }

    public static synchronized ExtentTest getTest() {
        return extentMap.get(Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
      //  ExtentManager.getExtentReports().removeTest(extentMap.remove(Thread.currentThread().getId()));
        ExtentManager.getExtentReports().flush();
        extentMap.remove(Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    private static String getTimeStamp() {
        DateTimeFormatter dates = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dates.format(LocalDateTime.now());
    }

}
