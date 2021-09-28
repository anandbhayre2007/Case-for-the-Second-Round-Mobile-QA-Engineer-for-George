package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import configuration.config_File;
import library.ApplicationLibrary;
import library.Utility;
import library.Xls_Reader;


public class Driver 
{
	
	public static ExtentReports report;
	public static ExtentTest logger;
	public  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	public Utility ul= new Utility();
	public ApplicationLibrary lib= new ApplicationLibrary();
	public static Properties or;
	public static Xls_Reader xl;
	
	@BeforeSuite
	public void initialize() throws IOException
	{
		report= new ExtentReports(config_File.reportPath+"Mobile_AutomationReport_"+timeStamp+".html");
		
		or= new Properties();		
		FileInputStream file= new FileInputStream(config_File.or);
		
		or.load(file);
		
		xl= new Xls_Reader(config_File.testdata);

		
		
	}
	
	
	
	@AfterSuite
	public void tearDown()
	{
		report.flush();
		
	}

}
