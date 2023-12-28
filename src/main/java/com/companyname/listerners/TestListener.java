package com.companyname.listerners;

import java.sql.Date;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

 /**
     * getTestParams method
     *
     * @param tr
     * @return String
     */
    public String getTestParams(ITestResult tr) {
        int iLength = tr.getParameters().length;
        String message = "";
        try {
            if (tr.getParameters().length > 0) {
                message = tr.getParameters()[0].toString();
                for (int iCount = 0; iCount < iLength; iCount++) {
                    if (iCount == 0) {
                        message = tr.getParameters()[0].toString();
                    } else {
                        message = message + ", " + tr.getParameters()[iCount].toString();
                    }
                }
            }
        } catch (Exception e) {
            // do nothing...
        }
        return message;
    }


    /**
     * onTestStart method
     *
     * @param tr
     */
    @Override
    public void onTestStart(ITestResult tr) {
        ITestNGMethod method = tr.getMethod();
        if (method.isTest()) {
            this.startTest(tr);
        }
    }

    private void startTest(ITestResult tr) {
        String testName = this.getTestNameFor(tr);
        String className = tr.getTestClass().getRealClass().getSimpleName();
        String testDescription = this.getDescriptionForMethod(tr.getMethod());
        com.companyname.reportmanager.Logger.startTest(className + "." + testName, testDescription);
    }

    public String getDescriptionForMethod(ITestNGMethod method) {
        return method.getConstructorOrMethod().getMethod().getAnnotatedReturnType().toString();
    }
    private String getTestNameFor(ITestResult tr) {
        String methodName = tr.getMethod().getMethodName();
        Object[] parameters = tr.getParameters();
        String parametersString = Arrays.stream(parameters).map(Object::toString)
                .collect(Collectors.joining(",", "[", "]"));
        return methodName + parametersString;
    }
    public void log(String line) {
        System.out.format("%s%n", line);
    }

     /**
     * onConfigurationSuccess method
     *
     * @param itr
     */
    @Override public void onConfigurationSuccess(ITestResult itr) {
        super.onConfigurationSuccess(itr);
    }
/**
     * onTestSuccess method
     *
     * @param tr
     */
    @Override public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
    }/**
     * onTestFailure method
     *
     * @param tr
     */
    @Override public void onTestFailure(ITestResult tr) {
        if (!getTestMessage(tr).equals("")) {
            log(getTestMessage(tr) + "\n");
        }
        log(tr.getEndMillis(), "END TEST: " + tr.getInstanceName() + "." + tr.getName());
        log("\n---\n");
        super.onTestFailure(tr);
    }

    /**
     * log method
     *
     * @param dateMillis
     * @param line
     */
    public void log(long dateMillis, String line) {
        System.out.format("%s: %s%n", String.valueOf(new Date(dateMillis)), line);
    }
    /**
     * getTestMessage method
     *
     * @param tr
     * @return String
     */
    public String getTestMessage(ITestResult tr) {
        Boolean found = false;
        if (tr != null && tr.getThrowable() != null) {
            found = true;
        }
        if (found == true) {
            return tr.getThrowable().getMessage() == null ? "" : tr.getThrowable().getMessage();
        } else {
            return "";
        }
    }



}
