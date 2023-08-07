package org.dssproject;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.implementation.bytecode.assign.Assigner.EqualTypesOnly;

public class BrowerLaunchMultipleChapter extends BaseClass {
	

	public static void main(String[] args) throws AWTException, InterruptedException, IOException {

		
		
		String 
		isbn = "HDL006HMLENG0000";

		String title = "Hydraulics: System Safety and Maintenance";

		browserLaunch();
		urlLaunch("https://app.cloud.scorm.com/sc/guest/SignInForm");
		implicityWait(40);
		userName("onlinepurchase@hurix.com");
		passwordd("Hur!X@123456");
		driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();// ------Login
		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-remove cookie_info_close']")).click();// ------close
																												// //
																												// cookies
		driver.findElement(By.xpath("//b[text()='Add Content']")).click();// -----addContentClick
		WebElement importbtn = driver.findElement(By.xpath("//a[text()='Import a SCORM, AICC, xAPI or cmi5 package']"));// -----importDropdown
		Enterjs(importbtn);
		driver.findElement(By.xpath("//label[text()='Choose file']"));
		enterRobot();
		String filePath = "D:\\DSS\\"+isbn+".zip";// -------change 1
		
		WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
		fileInput.sendKeys(filePath);
		
		
		driver.findElement(By.xpath("//button[@id='startImportButton']")).click();// ------importpackage
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='glyphicon glyphicon-edit link editTitle']")));
		WebElement outsideTitle = driver.findElement(By.xpath("(//div[@class='col-md-10'])[1]"));

		String outsideTitleName = outsideTitle.getText();
		System.out.println(outsideTitleName);

		TakesScreenshot ts = (TakesScreenshot) driver;// ----------Screenshot
		File From = ts.getScreenshotAs(OutputType.FILE);
		String baseDr = "D:\\DSS\\Screenshots\\";
		String titleName = isbn + "\\";
		File to = new File(baseDr + titleName + "outside title name.png");
		FileUtils.copyFile(From, to);

		String inputTitleName = title + " (" + isbn + ")";// -------change 2

		if (inputTitleName.equals(outsideTitleName)) {
			System.out.println("outside title matched with given title");
		} else {
			System.out.println("outside title does'nt matched with given title");
		}
		driver.findElement(By.xpath("//div[@class='btn btn-success btn-lg clickable right']")).click();// -------launchButton
		driver.switchTo().frame("ScormContent");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> wdwHandles = driver.getWindowHandles();
		Iterator<String> it = wdwHandles.iterator();
		String parentid = it.next();
		String Childid = it.next();
		driver.switchTo().window(Childid);

		driver.switchTo().frame("scormdriver_content");
		WebElement insideTitle = driver.findElement(By.id("navbar_course_title"));
		String insideTitleName = insideTitle.getText();

		if (inputTitleName.equals(insideTitleName)) {
			System.out.println("inside title matched with given title");
		} else {
			System.out.println("inside title does'nt matched with given title");
		}
		Thread.sleep(5000);
		TakesScreenshot ts1 = (TakesScreenshot) driver;// ----------Screenshot
		File From1 = ts1.getScreenshotAs(OutputType.FILE);
		File to1 = new File(baseDr + titleName + "inside title name.png");
		FileUtils.copyFile(From1, to1);

		WebElement startBtn = driver.findElement(By.xpath("//div[contains(text(),'START')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", startBtn);

		TakesScreenshot ts1_1 = (TakesScreenshot) driver;// ----------Screenshot
		File From1_1 = ts1_1.getScreenshotAs(OutputType.FILE);
		File to1_1 = new File(baseDr + titleName + "Cover image.png");
		FileUtils.copyFile(From1_1, to1_1);
		
		List<WebElement> chapters = driver.findElements(By.xpath("//div[@class='dki-element-text']"));
		int LastbeforeTwochapterSize=chapters.size()- 3;
	
		
		//------------count the chapter count(By.xpath("//div[@class='dki-element-text']"));
		

		js.executeScript("arguments[0].click()", startBtn);

		
			for(int i=1;i<=LastbeforeTwochapterSize;i++) {//--------------Total 6 Chapter/for loop for till 4 chapter
			driver.findElement(By.id("forwardButton")).click();// ------fwd
			System.out.println("");
	
			WebElement quiz = driver.findElement(By.xpath("//div[@class='dki-element-text    ']"));// -----quiz
			Enterjs(quiz);
			

			String TS = driver.findElement(By.id("totalScreens")).getText();
			int totalScreens = Integer.parseInt(TS);

			for (int j = 1; j <= totalScreens; j++) {

				driver.findElement(By.xpath("(//button[@tabindex='1'])[2]")).click();
				Alert al = driver.switchTo().alert();
				al.accept();}
			
				WebElement continueBtn = driver.findElement(By.xpath("//span[contains(text(),'Continue')]"));
				Enterjs(continueBtn);
				
			}
			
			driver.findElement(By.id("forwardButton")).click();//--------next 5 th chapetr
			driver.findElement(By.id("forwardButton")).click();
			WebElement continuefinalBtn = driver.findElement(By.xpath("(//div[contains(text(),'CONTINUE')])[2]"));
			Enterjs(continuefinalBtn);
			WebElement testBtn = driver.findElement(By.xpath("(//div[contains(text(),'TEST')])[2]"));
			Enterjs(testBtn);
			
			String TS = driver.findElement(By.id("totalScreens")).getText();
			int totalScreens = Integer.parseInt(TS);
			
			for (int l = 2; l >= 0; l--) {
				
			for (int k = 1; k <= totalScreens; k++) {

				driver.findElement(By.xpath("(//button[@tabindex='1'])[2]")).click();

				Alert al = driver.switchTo().alert();
				al.accept();}
			
			Thread.sleep(2000);
			
			if (l >= 1) {
				
				TakesScreenshot ts2 = (TakesScreenshot) driver;// ----------Screenshot
				File From2 = ts2.getScreenshotAs(OutputType.FILE);
				File to2 = new File(baseDr + titleName +l+ ".png");
				FileUtils.copyFile(From2, to2);
				driver.findElement(By.xpath("(//span[@class='dki-inline-variable'])[11]")).click();
			} else {
				TakesScreenshot ts3 = (TakesScreenshot) driver;// ----------Screenshot
				File From3 = ts3.getScreenshotAs(OutputType.FILE);
				File to3 = new File(baseDr + titleName +l+ ".png");
				FileUtils.copyFile(From3, to3);
				WebElement exitBtn = driver.findElement(By.xpath("(//span[contains(text(),'Exit')])[2]"));
				Enterjs(exitBtn);
			}}
		
			
			//-----------------------Relaunch---------------
			
			
			
			driver.switchTo().window(parentid);
			wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h2")));
			driver.findElement(By.xpath("//div[@class='btn btn-success btn-lg clickable right']")).click();// -----again
																											// launch
			driver.switchTo().frame("ScormContent");
			driver.switchTo().defaultContent();
			

			Set<String> wdwHandless = driver.getWindowHandles();
			Iterator<String> itt = wdwHandless.iterator();
			String parentid1 = itt.next();
			String Childid1 = itt.next();
			driver.switchTo().window(Childid1);

			Thread.sleep(6000);
			driver.switchTo().frame("scormdriver_content");
			
			TakesScreenshot ts4 = (TakesScreenshot) driver;//----------Screenshot
			File From4 = ts4.getScreenshotAs(OutputType.FILE);
			File to4 = new File(baseDr + titleName+"exhausted all attempt.png");
			FileUtils.copyFile(From4, to4);
			
			driver.findElement(By.xpath("(//span[@class='dki-inline-variable'])[1]")).click();

	
	
	}

	}



