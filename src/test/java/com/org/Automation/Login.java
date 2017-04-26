package com.org.Automation;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.chrome.*;




//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.reporters.XMLReporter;

import  java.sql.Connection;		
import  java.sql.Statement;		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;		
import java.util.List;
import java.util.concurrent.TimeUnit;

import atu.testng.reports.utils.Utils;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;



@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
MethodListener.class })
public class Login
{
	public WebDriver driver;
	
	String Actualtext;
	String element;
    private String baseURL;
	{
	System.setProperty("atu.reporter.config", "D:/Automation/Java/IACMapplication/ATUReporter_Selenium_testNG_5.5 BETA + ATU Recorder 2.1+javadoc+propfile/atu.properties");
    }
		 	
	File file = new File("D:/Automation/Java/IACMAutomation/src/test/java/com/org/Automation/Employeestatus.properties");

 
 	  

  @Test(priority=1)
  public void Commonlogin() 
  {
	  driver.get(baseURL + "/Login.aspx");    
	  driver.findElement(By.id("cplLogin_txtUserName")).sendKeys("iacmadmin");
	  driver.findElement(By.id("cplLogin_txtPassword")).sendKeys("di");
	  driver.findElement(By.id("cplLogin_btnLogin")).click();
      //driver.get(baseURL + "/Employee/Add.aspx");   
  }
  
//@Test(priority=1)
public void Employee_Accountcreation() throws AWTException, IOException, InterruptedException 
{ 
	  setAuthorInfoForReports();
	  driver.get(baseURL + "/Employee/Add.aspx"); 
	  FileInputStream fileInput = null;fileInput = new FileInputStream(file);Properties prop = new Properties();prop.load(fileInput);  
	  driver.findElement(By.id("cplMain_AddForm_IdentificationNo")).sendKeys(prop.getProperty("IdentificationNo"));
	  driver.findElement(By.id("cplMain_AddForm_EmployeeNo")).sendKeys(prop.getProperty("EmployeeNo"));
	  driver.findElement(By.id("cplMain_AddForm_DisplayName")).sendKeys(prop.getProperty("DisplayName"));
	  driver.findElement(By.id("cplMain_AddForm_FirstName")).sendKeys(prop.getProperty("FirstName"));    
	  driver.findElement(By.xpath(".//*[@id='cplMain_AddForm_LastName']")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.id("cplMain_AddForm_LastName")).sendKeys(prop.getProperty("LastName"));
	  Thread.sleep(300);
	  Thread.sleep(300);
	 driver.findElement(By.id("cplMain_AddForm_Password")).sendKeys(prop.getProperty("Password"));
	  Thread.sleep(300);
	 Thread.sleep(3000);
	  driver.findElement(By.id("cplMain_AddForm_TelephoneNo")).sendKeys(prop.getProperty("TelephoneNo"));
	  driver.findElement(By.id("cplMain_AddForm_txtMobileNo")).sendKeys(prop.getProperty("MobileNo"));
   	  driver.findElement(By.id("ctl00_cplMain_AddForm_ReferenceUsersAutoCompleteBox_Input")).sendKeys(prop.getProperty("ReferenceUsersAutoCompleteBox"));
      //driver.findElement(By.id("ctl00_cplMain_AddForm_ReferenceUsersAutoCompleteBox_Input")).sendKeys("subbannan krishnan");

      driver.findElement(By.id("ctl00_cplMain_AddForm_ReferenceUsersAutoCompleteBox_Input")).sendKeys(Keys.ARROW_DOWN);
      Thread.sleep(3000);
      driver.findElement(By.id("ctl00_cplMain_AddForm_ReferenceUsersAutoCompleteBox_Input")).sendKeys(Keys.ENTER);
	  Thread.sleep(3000); 

		    driver.findElement(By.id("AccessPanel_rptPermissions_lkAction_0")).click();
	
	  //driver.findElement(By.id("Save")).click(); 
	  ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	  ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
	
	  WebElement element1 = driver.findElement(By.xpath(".//*[@id='lblPageTitle']"));
	  ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(element1));
	  Thread.sleep(3000);
	  Actualtext = driver.findElement(By.xpath(".//*[@id='lblPageTitle']")).getText(); 
	  Assert.assertEquals(Actualtext,"Employees"); 
	  Thread.sleep(3000);
	   
	  

  
       
  
}



		  

 
@BeforeMethod
  public void beforeMethod() throws IOException {

  
   System.setProperty("webdriver.firefox.marionette","D:\\Automation\\Java\\IACMAutomation\\driver\\geckodriver.exe");
	driver = new FirefoxDriver();
	   
	   driver.manage().window().maximize();
   
	
    ATUReports.setWebDriver(driver);
    ATUReports.indexPageDescription = "IACM - Onboard";
    baseURL = "http://192.168.16.194:90";
    Commonlogin();
    setAuthorInfoForReports();
   
}
 	
  
private void setAuthorInfoForReports() {
  ATUReports.setAuthorInfo("Testing Team", Utils.getCurrentTime(),"1.0");

	}

@AfterMethod
  public void afterMethod() {	
		driver.quit();
}
}
