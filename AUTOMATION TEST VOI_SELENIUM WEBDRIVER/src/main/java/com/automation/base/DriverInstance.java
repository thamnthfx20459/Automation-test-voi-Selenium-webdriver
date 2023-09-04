package main.java.com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import main.java.automation.utils.PropertiesFileUtils;

public class DriverInstance {
	public WebDriver driver;

	@BeforeClass
	public void initDriverInstance() throws Exception {

		System.setProperty("webdriver.chrome.driver", "./drive/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@AfterClass
	public void closeDriverInstance() {
		driver.quit();
	}

}
