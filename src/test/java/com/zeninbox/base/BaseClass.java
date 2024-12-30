package com.zeninbox.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.zeninbox.authentication.pom.LoginPage;
import com.zeninbox.utility.ConfigDataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

//static ConfigDataProvider data = new ConfigDataProvider();
//public static String reportPath = data.getReportPath();

public class BaseClass {
	static ConfigDataProvider data = new ConfigDataProvider();
	public static String reportPath = data.getReportPath();
	public static WebDriver driver;
	public LoginPage LoginPom;
	public String emailAddress = data.getEmail();
	public String enterPassword = data.getPass();
	public String loginURL = data.getLoginUrl();
	public String ssPath = data.getScreenshotPath();

//	@BeforeClass
//	public void startBrowser() {
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
////		driver.get("https://staging.zeninbox.ai/conversation/inbox");
//		LoginPom = new LoginPage(driver);
//	}
//	

	public static boolean isHeadless = true;

	@BeforeClass
	public void start_browser() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		if (isHeadless) {
			options.addArguments("--headless");
		}
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LoginPom = new LoginPage(driver);
	}

	public static String getScreenshotPath(String testCaseName) throws IOException {
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized.");
		}
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String relativePath = "ZenInBox_Report/screenshots/" + testCaseName + "_" + timestamp + ".png";
		String fullPath = System.getProperty("user.dir") + "/" + relativePath;
		File dir = new File("ZenInBox_Report/screenshots");
		if (!dir.exists() && !dir.mkdirs()) {
			throw new IOException("Failed to create screenshot directory.");
		}
		FileUtils.copyFile(source, new File(fullPath));
		return relativePath;
	}

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

}
