

package baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.bcel.classfile.Method;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ExcelReader.excel_Reader;
import helpers.WaitHelper;

/**
 * @author training63
 *
 */
public class baseTestClass {
	
	public static final Logger logger=Logger.getLogger(baseTestClass.class.getName()); 
	public WebDriver driver;
	public static  Properties OR;
	public File f1;
	public FileInputStream file;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ITestResult result;
	
	public excel_Reader excelreader;
	
	
	
	static 
	{
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
		extent=new ExtentReports(System.getProperty("user.dir") + "/Reports/test" +"_"+ formater.format(calendar.getTime()) + ".html", false);
		
	}
	
	@BeforeClass()
	public void launchBrowser()
	{
		try {
			loadPropertiesFile();
		} catch (IOException e) {
			e.getMessage();
		}
		config conf=new config(OR);
		getBrowser(conf.getBrowser());
		WaitHelper waithelper=new WaitHelper(driver);
		waithelper.setImplicitWait(conf.getImplicitWait(), TimeUnit.SECONDS);
		waithelper.setPageLoadTimeout(conf.getPageLoadtimeOut(), TimeUnit.SECONDS);
		
	}
	
	public void getBrowser(String browser)
	{
		if(System.getProperty("os.name").contains("Windows"))
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
				driver=new FirefoxDriver();
			}
			
			else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
				driver=new ChromeDriver();
				System.out.println(System.getProperty("user.dir"));
			}
			
		}
		
		else if(System.getProperty("os.name").contains("Mac"))
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.marionette", System.getProperty("user.dir")+"/Drivers/geckodriver");
				driver=new FirefoxDriver();
			}
			
			else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
				driver=new ChromeDriver();
				System.out.println(System.getProperty("user.dir"));
			}
			
		}
	}
	
	
	public void loadPropertiesFile() throws IOException
	{
		String log4jConfPath="log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		OR=new Properties();
		f1=new File(System.getProperty("user.dir") + "/src/main/java/config/config.properties");
		file=new FileInputStream(f1);
		OR.load(file);
		logger.info("Loading config.properties");
		
		
		f1=new File(System.getProperty("user.dir") + "/src/main/java/config/or.properties");
		file=new FileInputStream(f1);
		OR.load(file);
		logger.info("Loading or.properties");
	
		f1=new File(System.getProperty("user.dir") + "/src/main/java/Properties/homePage.properties");
		file=new FileInputStream(f1);
		OR.load(file);
		logger.info("Loading homePage.properties");
		
	}
	
	public String getScreenShots(String imageName) throws IOException
	{
		if(imageName.equals(""))
		{
			imageName="blank";
		}
		File image=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imageLocation=System.getProperty("user.dir")+"/ScreenShots/";
		
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("dd_mm_yy_hh_mm_ss");
		String actualImageName=imageLocation+imageName+"_"+formater.format(calendar.getTime())+".png";
		File destFile=new File(actualImageName);
		FileUtils.copyFile(image, destFile);
		return actualImageName;
	}
	
	public void getPropertiesData()
	{
		
	}
	
	public WebElement waitForElement(WebDriver driver,long time,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementwithPollingInterval(WebDriver driver,long time,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void implicitWait(long time)
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	
	public void getresult(ITestResult result) throws IOException
	{
		
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, result.getName()+"Test is pass");
		}
		
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(LogStatus.SKIP, result.getName()+"Test is skipped ==>"+result.getThrowable());
		}
		
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, result.getName()+"Test is Failed ==>"+result.getThrowable());
			String screen=getScreenShots("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
					
		}
		else if(result.getStatus()==ITestResult.STARTED)
		{
			test.log(LogStatus.INFO, result.getName()+"Test started");
			
		}
	}
	
	
	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException
	{
		getresult(result);
	}
	
	@BeforeMethod()
	public void beforeMethod(Method result)
	{
		test=extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+"Test started");
		
	}
	
	@AfterClass(alwaysRun=true)
	public void endTest()
	{
		//driver.close();
		driver.quit();
		extent.endTest(test);
		extent.flush();
		
	}
	
	
	public  WebElement getLocator(String locator) throws Exception
	{
		//System.out.println(locator);
		String[] split=locator.split(":");
		String locatorType=split[0];
		String locatorValue=split[1];
		//System.out.println(locatorValue);
		if(locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if(locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if((locatorType.toLowerCase().equals("classname"))
			|| (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElement(By.tagName(locatorValue));
		else if((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
				return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElement(By.partialLinkText(locatorValue));
		else if((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("licssnk")))
				return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		
		else
			throw new Exception("Unlnown locator type'" + locatorType + "'");
	}
	
	
	public List<WebElement> getLocators(String locator) throws Exception
	{
		String[] split=locator.split(":");
		String locatorType=split[0];
		String locatorValue=split[1];
		
		if(locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if(locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if((locatorType.toLowerCase().equals("classname"))
			|| (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElements(By.tagName(locatorValue));
		else if((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
				return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElements(By.partialLinkText(locatorValue));
		else if((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
				return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		
		else
			throw new Exception("Unknown locator type'" + locatorType + "'");
	}
	
	
	public WebElement getWebElement(String locator) throws Exception
	{
		return getLocator(OR.getProperty(locator));
	}
	
	public List<WebElement> getWebelements (String locator) throws Exception
	{
		return getLocators(OR.getProperty(locator));
	}
	
	
	public String[][] getData(String excelName,String sheetName)
	{
		String excellocation=System.getProperty("user.dir") + "/Data/" + excelName;
		excel_Reader excelreader=new excel_Reader();
		return excelreader.getExcelData(excellocation, sheetName);
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception 
	{
		
		baseTestClass btc=new baseTestClass();
		
		//btc.getBrowser("chrome");
		
			btc.loadPropertiesFile();
		
		//System.out.println(btc.OR.getProperty("url"));
		//System.out.println(btc.OR.getProperty("un"));
			
			//System.out.println("The password is"+btc.OR.getProperty("password"));
		
			//System.out.println(btc.getWebElement("password"));
		
	}

}
