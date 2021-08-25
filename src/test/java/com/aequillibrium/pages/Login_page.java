package com.aequillibrium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aequillibrium.drivers.Driver;

public class Login_page extends Driver {
	public static final String filename = null;
	//public ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	//public Properties prop = readPropertiesFile.readPropertiesFile(filename);

	By username = By.id("user-name");
	By password = By.id("password");
	By loginButton = By.id("login-button");
	By errormessage= By.xpath("//div[@class='error-message-container error']");
	By logoSymbol = By.className("app_logo");
	By errorMessageClose= By.xpath("//button[@class='error-button']");
	String url = "https://www.saucedemo.com/";
	public Login_page() {
		PageFactory.initElements(driver, this);
	}

	public void navigateTo_URL() {
		driver.get(url);
	}

	public boolean username_field() {
		WebElement element = driver.findElement(username);
		return element.isDisplayed();
	}

	public boolean password_field() {
		WebElement element = driver.findElement(password);
		return element.isDisplayed();
	}

	public void enter_Username(String user) {
		WebElement element = driver.findElement(username);
		element.sendKeys(user);
	}
	
	public void enter_password(String pass) {
		WebElement element = driver.findElement(password);
		element.sendKeys(pass);
	}
	
	public void clearUsername() {
		WebElement element = driver.findElement(username);
		element.clear();
	}
	
	public void clearpassword() {
		WebElement element = driver.findElement(password);
		element.clear();
	}
	
	public String getPasswordProp(String property) {
		WebElement element = driver.findElement(password);
		return element.getAttribute(property);
	}

	public boolean login_button() {
		WebElement element = driver.findElement(loginButton);
		return element.isDisplayed();
	}
	
	public void login_button_click() {
		WebElement element = driver.findElement(loginButton);
		element.click();
	}
	
	public void errorMessage_close() {
	
		WebElement element = driver.findElement(errorMessageClose);
		element.click();
			}
	
	public int errorMessage_close_isDisplayed() {
		
		List<WebElement> element = driver.findElements(errorMessageClose);
		return element.size();
			}
	
	public boolean verifyLogo() {
		WebElement element = driver.findElement(logoSymbol);
		return element.isDisplayed();
	}
	
	public String getErrorMessage() {
		WebElement element = driver.findElement(errormessage);
		return element.getText();
	}
	
	
}