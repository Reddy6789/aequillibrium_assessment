package com.aequillibrium.tests;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aequillibrium.drivers.Driver;
import com.aequillibrium.pages.Login_page;
import com.aequillibrium.utilities.fileUtils;

public class LoginTest extends Driver {

	public static final String filename = null;
	public Login_page LoginPage = new Login_page();
	public fileUtils selUtils = new fileUtils();
	public String password = "secret_sauce";

	// browserfirefox
	@Parameters({ "browserchrome" })
	@BeforeClass

	public void initialization(String browser) {
		Driver.init(browser);
		LoginPage.navigateTo_URL();
		Assert.assertEquals(Driver.driver.getTitle(), "Swag Labs");
		selUtils.clearScreenshotsDirectory();
	}

//	Verify if Username and Password fields are displayed
//	Verify the LOGIN button is displayed

	@Test(priority = 0)
	public void verifyUsernamePasswordLoginFields() {
		boolean flag_Username = LoginPage.username_field();
		boolean flag_Password = LoginPage.password_field();
		boolean flag_Login_Button = LoginPage.password_field();
		Assert.assertEquals(flag_Username, true);
		Assert.assertEquals(flag_Password, true);
		Assert.assertEquals(flag_Login_Button, true);

	}

//	Verify login is successful with a correct username and correct password.

	@Test(priority = 1)
	public void verifyIfLoginSuccesful() throws InterruptedException {
		LoginPage.enter_Username("standard_user");
		LoginPage.enter_password(password);
		LoginPage.login_button_click();
		if (LoginPage.verifyLogo()) {
			Reporter.log("Login Succesful");
		}
		driver.navigate().back();
	}

//	Verify below error message for invalid login.
//	Epic sadface: Username and password do not match any user in this service messages
	@Test(priority = 2)
	public void verifyLoginErrorMessage() {
		LoginPage.clearUsername();
		LoginPage.enter_Username("invalidUN");
		LoginPage.clearpassword();
		LoginPage.enter_password(password);
		LoginPage.login_button_click();
		String errorMessage = LoginPage.getErrorMessage();
		LoginPage.errorMessage_close();
		Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
	}

	// Verify the error messages are closed when user clicks on X symbol on the
	// error message box
	@Test(priority = 3)
	public void verifyCloseSymbol() {
		LoginPage.clearUsername();
		LoginPage.enter_Username("invalidUN");
		LoginPage.clearpassword();
		LoginPage.enter_password(password);
		LoginPage.login_button_click();
		LoginPage.errorMessage_close();
		Assert.assertEquals(LoginPage.errorMessage_close_isDisplayed(), 0,"Error message not succesfully closed");

	}

	// Verify if password field is masked

	@Test(priority = 4)
	public void verifyPasswordMasked() {

		LoginPage.enter_password(password);
		String passType = LoginPage.getPasswordProp("type");
		Assert.assertEquals(passType, "password");
	}

	@AfterTest
	public void quit() {
		Driver.driver.quit();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./ErrorScreenshots/" + result.getName() + ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}