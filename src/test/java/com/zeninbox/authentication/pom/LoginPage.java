package com.zeninbox.authentication.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zeninbox.base.BaseClass;

public class LoginPage extends BaseClass {

	private WebDriver localDriver;
	private WebDriverWait wait;

	public LoginPage(WebDriver remoteDriver) {
		localDriver = remoteDriver;
		wait = new WebDriverWait(remoteDriver, 30);
		PageFactory.initElements(remoteDriver, this);
	}

	public void waitForElementToBeVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	@FindBy(how = How.ID, using = "email")
	private WebElement validEmail;

	public void enterValidEmail() {
		waitForElementToBeVisible(validEmail);
		validEmail.sendKeys(emailAddress);
	}

	public void enterSpaceBeforeEmail() {
		waitForElementToBeVisible(validEmail);
		validEmail.sendKeys(" " + emailAddress + " ");
	}

	public void enterInValidEmail() {
		waitForElementToBeVisible(validEmail);
		validEmail.sendKeys("anacooonda@ou");
	}

	public void enterValidPassword() {
		waitForElementToBeVisible(validEmail);
		validEmail.sendKeys("satish@gmware");
	}

	@FindBy(how = How.ID, using = "password")
	private WebElement validPass;

	public void enterValidPass() {
		waitForElementToBeVisible(validPass);
		validPass.sendKeys(enterPassword);
	}

	public void enterSpaceInValidPass() {
		waitForElementToBeVisible(validPass);
		validPass.sendKeys(" " + enterPassword + " ");
	}

	public void enterInValidPass() {
		waitForElementToBeVisible(validPass);
		validPass.sendKeys("satish");
	}

	@FindBy(how = How.XPATH, using = "//button[text()='Login']")
	private WebElement loginBotton;

	public void clickOnLogin() {
		waitForElementToBeVisible(loginBotton);
		loginBotton.click();
	}

	@FindBy(how = How.XPATH, using = "//button[text()='Login']")
	public WebElement loginB;

	public WebElement loginButton() {
		waitForElementToBeVisible(loginB);
		return loginB;
	}

	@FindBy(how = How.XPATH, using = "(//button[@data-state='closed'])[1]")
	private WebElement logOut;

	public void clickOnLogOut() {
		waitForElementToBeVisible(logOut);
		logOut.click();
	}

	@FindBy(how = How.XPATH, using = "//p[text()='Invalid email or password']")
	private WebElement invlaidmsgerror;

	public WebElement getError() {
		waitForElementToBeVisible(invlaidmsgerror);
		return invlaidmsgerror;
	}

}
