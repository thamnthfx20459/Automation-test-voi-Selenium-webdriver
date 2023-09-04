package main.java.automation.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CaptureScreenshot {

	static String filename = null;

	// tên ảnh chính là phần trên của email đăng nhập vd funixExample1@gmail.com=>
	// funixExample1.png
	public static void takeScreenshot(WebDriver driver, String name) throws Exception {
		try {
			Calendar c = Calendar.getInstance();
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			filename = "./screenshots/" + name + c.getTime().toString().replace(":", "").replace(" ", "") + ".png";
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			File destiny = new File(filename);
			FileUtils.copyFile(source, destiny);
		} catch (Exception ex) {
			System.out.println("Đã xảy ra lỗi khi chụp màn hình");
			ex.printStackTrace();

		}

		attachScreenshotToReport(filename);
	}

	public static void attachScreenshotToReport(String filename) {
		try {

			System.setProperty("org.uncommons.reportng.escape-output", "false");
			// lấy ra file theo đường dẫn
			File file = new File(filename);
			// chèn thẻ <a chứa title mô tả hình ảnh và đường dẫn tới thư mục chứa ảnh
			// <br>xuống dòng
			Reporter.log("<div><a title =\"screenshots\" href=\"" + file.getAbsolutePath() + "\">" + "<img src=\""
					+ file.getAbsolutePath() + "\" alt='" + file.getName()
					+ "'height='243'width='418'/></a><p>Ảnh testcase lỗi: \"" + file.getName() + "\"</p></div>");
		} catch (Exception e) {

		}
	}
}
