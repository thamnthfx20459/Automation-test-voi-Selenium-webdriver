package test.java.com.automation.testcase;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import main.java.automation.utils.CaptureScreenshot;
import main.java.automation.utils.PropertiesFileUtils;
import main.java.com.automation.base.DriverInstance;
import main.java.com.automation.pom.LoginPage;

public class TC_LoginTest extends DriverInstance {
	@Test(dataProvider = "Excel")
	public void TC01_loginFirstAccount(String email, String password) throws InterruptedException {
		// laays URL tu properties file va tai trang
		String baseURL = PropertiesFileUtils.getProperty("base_URL");
		driver.get(baseURL);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// Lấy định danh iconSignin từ properties file và tìm kiếm, click

		String signin = PropertiesFileUtils.getProperty("icon_signin");
		WebElement iconSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signin)));
		if (iconSignIn.isDisplayed()) {
			iconSignIn.click();

			LoginPage loginPage = new LoginPage(driver);
			PageFactory.initElements(driver, loginPage);
			loginPage.enterEmail(email);
			loginPage.enterPassword(password);
			loginPage.clickloginIn();

			// Lấy định danh iconSignout từ properties file và tìm kiếm, click
			String signout = PropertiesFileUtils.getProperty("icon_signout");
			WebElement iconsignout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signout)));
			Assert.assertEquals(true, iconsignout.isDisplayed());
			if (iconsignout.isDisplayed()) {
				iconsignout.click();
			}

			Thread.sleep(1000);
		}
	}

	@DataProvider(name = "Excel")
	public Object[][] testDataGeneration() throws Exception {
		FileInputStream file = new FileInputStream("./data/assignment2_data_test.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet loginSheet = wb.getSheet("login");
		int nubmerOfData = loginSheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[nubmerOfData][2];
		for (int i = 0; i < nubmerOfData; i++) {
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell email = row.getCell(0);
			XSSFCell password = row.getCell(1);
			data[i][0] = email.getStringCellValue();
			data[i][1] = password.getStringCellValue();

		}
		return data;
	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		Thread.sleep(1000);
		if (ITestResult.FAILURE == result.getStatus()) {
			try {

				String email = (String) result.getParameters()[0];
				int index = email.indexOf('@');
				String accountName = email.substring(0, index);
				CaptureScreenshot.takeScreenshot(driver, accountName);
				System.out.println("Đã chụp màn hình: " + result.getName());
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
	}
}
