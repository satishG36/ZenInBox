package com.zeninbox.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.zeninbox.base.BaseClass;

public class ExtentReportGenerator extends BaseClass {

	public static ExtentReports generateReport() {

		String ReportPath = reportPath;

		ExtentSparkReporter repoter = new ExtentSparkReporter(ReportPath);

		repoter.config().setDocumentTitle("ZenInBox_Web_Aplication");

		repoter.config().setReportName("ZenInBox_Test_Result");

		repoter.config().setTheme(Theme.STANDARD);

		ExtentReports extent = new ExtentReports();

		extent.attachReporter(repoter);

		extent.setSystemInfo("aplication Type", "Web_Application");
		extent.setSystemInfo("Application name", "ZenInBox");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("QA Name", "Satish Gaware");
		extent.setSystemInfo("OS", "Linux");

		return extent;

	}

}
