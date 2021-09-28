package library;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import configuration.config_File;

public class ApplicationLibrary 
{

	
	
	public  String captureScreenShot(WebDriver dr, String name) throws IOException
	{		
		TakesScreenshot scrShot =((TakesScreenshot)dr);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		String  path=config_File.screenShotPath+name+".jpg";
		File DestFile=new File(path);		
		FileUtils.copyFile(SrcFile, DestFile);		
		return path;
	}
	
}
