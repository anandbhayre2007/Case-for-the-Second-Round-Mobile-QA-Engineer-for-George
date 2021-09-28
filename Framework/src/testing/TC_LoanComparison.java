package testing;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import configuration.config_File;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class TC_LoanComparison {

	public static void main(String[] args) throws MalformedURLException, InterruptedException 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "7.1.2");
		capabilities.setCapability("deviceName", "83d6e4847d44");
		capabilities.setCapability("udid", "83d6e4847d44");
		//capabilities.setCapability("automationName", "uiautomator");
		//capabilities.setCapability("systemPort", 8202);
		//capabilities.setCapability("noSign", true);
		capabilities.setCapability("app", config_File.apk);
		
		capabilities.setCapability("appPackage", "com.arbapps.loanemicalc");
		capabilities.setCapability("appActivity", ".MainScreen");
		
		AppiumDriver dr = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	

		Thread.sleep(3000);
		dr.findElement(By.id("com.arbapps.loanemicalc:id/loancomparison")).click();
		
		Thread.sleep(2000);
		dr.findElement(By.id("com.arbapps.loanemicalc:id/principal_amount1")).sendKeys("6000");
		
		
	}

}
