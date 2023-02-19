package com.selenium.CrossBrowserTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class googleLaunch {
	
	public WebDriver driver;
	
	@BeforeTest
	
	@Parameters("browser")
	public void SelectBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("Chromeheadless"))
		{
			ChromeDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
			System.out.println("Chrome Headless Driver Launched");
		}
		else if(browser.equalsIgnoreCase("PhantomJS")) 
		{
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, false);
			driver = new PhantomJSDriver(caps);
			System.out.println("PhontomJS headless driver launched");
		}
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}
	
	@Test
	public void GoogleSearch()
	{
		driver.findElement(By.name("q")).sendKeys("Fresco Play");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		try {Thread.sleep(2000);} catch (Exception e){}
		System.out.println("Page Title is : " +driver.getTitle());
		
	}
	
	@AfterTest
	public void BrowserQuit() 
	{
		driver.quit();
	}

}
