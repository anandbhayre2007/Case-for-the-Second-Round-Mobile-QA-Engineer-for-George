package testScripts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import configuration.config_File;
import driver.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class Tc_EMI_Calculator_02 extends Driver
{
	AppiumDriver dr=null;
	WebDriverWait wait=null;
	
	
	@BeforeTest
	@Parameters({"devicename","version", "ipAddress", "port"})
	public void setup(String devicename, String version, String ip, String port) throws MalformedURLException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", version);
		capabilities.setCapability("deviceName", devicename);
		capabilities.setCapability("udid", devicename);
		//capabilities.setCapability("automationName", "uiautomator");
		//capabilities.setCapability("systemPort", 8202);
		//capabilities.setCapability("noSign", true);
		capabilities.setCapability("app", config_File.apk);
		
		capabilities.setCapability("appPackage", "com.arbapps.loanemicalc");
		capabilities.setCapability("appActivity", ".MainScreen");
		
		dr = new AndroidDriver(new URL("http://"+ip+":"+port+"/wd/hub"), capabilities);		
		wait= new WebDriverWait(dr, 60);
	}
	
	@Test(dataProvider="getData",priority=1)
	public void script(String p, String t, String r, String my ) throws InterruptedException, IOException
	{
		logger=report.startTest("To test Loan EMI with Principal="+p+" ROI="+r+" Tensure="+t+my);
		
		String img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "LOAN EMI CALC button is displayed", img);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(or.getProperty("loanEmi_i")))).click();
		img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "LOAN EMI CALC screen is displayed", img);
		
		dr.findElement(By.id("com.arbapps.loanemicalc:id/principal_amount")).sendKeys(p);
		img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "Principal is displayed", img);
		
		
		dr.findElement(By.id("com.arbapps.loanemicalc:id/interest_rate")).sendKeys(r);
		img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "ROI is displayed", img);
		
		dr.findElement(By.id("com.arbapps.loanemicalc:id/duration")).sendKeys(t);
		img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "Tenure is displayed", img);
		
		dr.findElement(By.id("com.arbapps.loanemicalc:id/spinner3")).click();
		img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "Tenure options is displayed", img);
		
		dr.findElement(By.xpath("//android.widget.CheckedTextView[contains(@text,'"+my+"')]")).click();
		img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "Tenure option"+my+" is selected", img);
		
		
		dr.findElement(By.xpath(or.getProperty("calculate_c"))).click();
		img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "EMI is Calculated", img);
		
		Thread.sleep(2000);
		((AndroidDriver)dr).pressKeyCode(AndroidKeyCode.BACK);
		
		report.endTest(logger);
		
	}
	
	@Test(priority=2)
	public void script2() throws IOException
	{
		logger=report.startTest("To test reset functionality");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.arbapps.loanemicalc:id/loanemicalc"))).click();
		String img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "Reset button is displayed is displayed", img);
		
		dr.findElement(By.id("com.arbapps.loanemicalc:id/reset")).click();
		img=logger.addScreenCapture(lib.captureScreenShot(dr, ul.timeStamp()));
		logger.log(LogStatus.PASS, "Reset button is clicked", img);
		
		System.out.println("Clicked on Reset button");
		
		report.endTest(logger);
	}
	
	@AfterTest
	public void teardown()
	{
		dr.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		int rows= xl.getrowcount("Sheet1");
		int cols= xl.getColumncount("Sheet1");
		
		Object[][] data= new Object[rows-1][cols];
		
		for(int r=1; r<rows; r++)
		{
			for(int c=0; c<cols; c++)
			{
				data[r-1][c]=xl.getCellData("Sheet1", r+1, c+1);
			}
			
		}
		
		
		return data;
		
	}

}
