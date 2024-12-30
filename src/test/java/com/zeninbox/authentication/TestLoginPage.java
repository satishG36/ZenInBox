package com.zeninbox.authentication;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.zeninbox.base.BaseClass;

public class TestLoginPage extends BaseClass {
	// note --
	// In the code, I added the static strings or messages directly instead of
	// storing them in a separate file due to timeline constraints.

	@Test(priority = 1)
	public void verifyLoginInWithValidInput() throws Exception {
		driver.get(loginURL);
		LoginPom.enterValidEmail();
		LoginPom.enterValidPass();
		LoginPom.clickOnLogin();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlToBe("https://staging.zeninbox.ai/conversation/inbox"));
		Assert.assertEquals("https://staging.zeninbox.ai/conversation/inbox", driver.getCurrentUrl());
		Thread.sleep(2000);
		LoginPom.clickOnLogOut();
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidEmail() throws Exception {
		driver.get(loginURL);
		LoginPom.enterInValidEmail();
		LoginPom.enterValidPass();
		LoginPom.clickOnLogin();
		Thread.sleep(2000);
		Assert.assertEquals("Invalid email or password", LoginPom.getError().getText());

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidPassword() throws Exception {
		driver.get(loginURL);
		LoginPom.enterValidEmail();
		LoginPom.enterInValidPass();
		LoginPom.clickOnLogin();
		Thread.sleep(2000);
		Assert.assertEquals("Invalid email or password", LoginPom.getError().getText());
	}

	@Test(priority = 4)
	public void verifyLoginWithLeadingAndTrailingSpacesInEmail() throws Exception {
		driver.get(loginURL);
		LoginPom.enterSpaceBeforeEmail();
		LoginPom.enterValidPass();
		LoginPom.clickOnLogin();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.urlToBe("https://staging.zeninbox.ai/conversation/inbox"));
			Assert.assertEquals("https://staging.zeninbox.ai/conversation/inbox", driver.getCurrentUrl());
			Thread.sleep(2000);
			LoginPom.clickOnLogOut();
			Thread.sleep(2000);
		} catch (Exception E) {
			Assert.fail("Failed to trim leading and trailing spaces in email. " + E.getMessage());
		}
	}

	@Test(priority = 5)
	public void verifyLoginWithLeadingAndTrailingSpacesInPassword() throws Exception {
		driver.get("https://staging.zeninbox.ai");
		LoginPom.enterValidEmail();
		LoginPom.enterSpaceInValidPass();
		LoginPom.clickOnLogin();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.urlToBe("https://staging.zeninbox.ai/conversation/inbox"));
			Assert.assertEquals("https://staging.zeninbox.ai/conversation/inbox", driver.getCurrentUrl());
			Thread.sleep(2000);
			LoginPom.clickOnLogOut();
			Thread.sleep(2000);
		} catch (Exception E) {
			Assert.fail("Failed to trim leading and trailing spaces in Password. \n \n" + E.getMessage());
		}
	}

	@Test(priority = 6)
	public void verifyLoginButtonShuldBeDisableBeforeEnteringTheText() throws Exception {
		driver.get("https://staging.zeninbox.ai");

		if (!LoginPom.loginB.isEnabled()) {
			System.out.println("Test Passed: Login button is disabled before entering credentials.");
		} else {
			System.out.println("Test Failed: Login button is enabled before entering credentials.");
		}

	}

}
