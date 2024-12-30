package com.zeninbox.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigDataProvider {

	private Properties Pro;

	public ConfigDataProvider() {
		String environment = System.getProperty("env", "dev"); // dev // prod
		try (InputStream input = getClass().getClassLoader().getResourceAsStream(environment + ".properties")) {
			if (input == null) {
				throw new IllegalArgumentException("Sorry, unable to find " + environment + ".properties file.");
			}
			Pro = new Properties();
			Pro.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return Pro.getProperty("Email");
	}

	public String getPass() {
		return Pro.getProperty("Password");
	}

	public String getLoginUrl() {
		return Pro.getProperty("loginUrl");
	}

	public String getfName() {
		return Pro.getProperty("firstName");
	}

	public String getlName() {
		return Pro.getProperty("lastName");
	}

	public String baseUrl() {
		return Pro.getProperty("baseUrl");
	}

	public String getReportPath() {
		return Pro.getProperty("report_path");
	}

	public String getScreenshotPath() {
		return Pro.getProperty("Screenshot_path");
	}

}
