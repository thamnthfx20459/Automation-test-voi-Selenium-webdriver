package main.java.com.automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.automation.utils.PropertiesFileUtils;

public class LoginPage {
	 WebDriverWait wait;
	 WebDriverWait driver;
	
	public LoginPage(WebDriver driver) {
		wait = new WebDriverWait(driver, 30);
	}
	
	public void enterEmail(String email)throws InterruptedException {
		
		String elementIdentified = PropertiesFileUtils.getProperty("login_email_xpath");
		WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
		inputEmail.sendKeys(email);
		Thread.sleep(1000);
		
		
	}
   public void enterPassword(String password) throws InterruptedException {
	   String elementIdentified1 = PropertiesFileUtils.getProperty("login_password_xpath");
	   WebElement inputpassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified1)));
	   inputpassword.sendKeys(password);
		Thread.sleep(1000);
	}
   public void clickloginIn() throws InterruptedException {
	   String elementIdentified2 = PropertiesFileUtils.getProperty("login_signin_xpath");
	   WebElement clickloginl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified2)));
	   clickloginl.click();
		Thread.sleep(1000);
	}

}


