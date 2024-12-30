package com.zeninbox.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zeninbox.base.BaseClass;

public class listeners extends BaseClass implements ITestListener {

	ExtentTest test;

	ExtentReports extent = ExtentReportGenerator.generateReport();
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "**" + result.getMethod().getMethodName());
		extenttest.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test Case Passed");
		String greenColor = "\033[0;32m";
		String resetColor = "\033[0m";
		System.out.println(greenColor + "Test Case Passed: " + resetColor + result.getName());
	}

	public void onTestFailure(ITestResult result) {

		test.log(Status.FAIL, "Test Case Failed");
		String redcolor = "\033[0;31m";
		String resetColor = "\033[0m";
		System.out.println(redcolor + "Test Case Failed: " + resetColor + result.getName());
		try {
			if (BaseClass.driver != null) {
				String screenshotPath = BaseClass.getScreenshotPath(result.getMethod().getMethodName());
				String absolutePath = System.getProperty("user.dir") + "/" + screenshotPath;
//				System.out.println("Relative Path: " + screenshotPath);
//				System.out.println("Absolute Path: " + absolutePath);
				test.addScreenCaptureFromPath(absolutePath, "Failure Screenshot");
			} else {
				test.log(Status.FAIL, "Driver is not initialized. Screenshot not captured.");
			}
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to attach screenshot due to: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, "Test Case Skipped");
		System.out.println("Test Case Skipped " + result.getName());
	}

	public void onFinish(ITestContext context) {

		extent.flush();
	}

}