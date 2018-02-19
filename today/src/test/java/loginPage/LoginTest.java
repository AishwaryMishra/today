package loginPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ExcelReader.ExcelDataProviderNew;
import ExcelReader.excel_Reader;
import PageObject.LoginPage;
import baseTest.baseTestClass;
import baseTest.config;
import helpers.LoggerHelper;

public class LoginTest extends baseTestClass {
	
	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	String path=System.getProperty("user.dir") + "/Data/NewData.xlsx";
	int rowCount;
	
	@DataProvider(name="testData")
	public Object[][] dataSource()
	{
		return getData("NewData.xlsx", "Login");
	}
	
	@Test(dataProvider="testData")
	public void testLoginToApplication(String UserName,String Password, String RunMode)
	{
		log.info(LoginTest.class.getName()+" has started");
		
		config conf=new config(OR);
		driver.get(conf.getWebsite());
		LoginPage loginpage=new LoginPage(driver);
		
		//Method 1 from property file as below ==loginpage.loginToApplication();
		//loginpage.loginToApplication(conf.getUserName(), conf.getPassword());
		
		
		
		//Method 2 from excel
		/*===========================================================*/
		
	//	ExcelDataProviderNew excel=new ExcelDataProviderNew(path);
	//	rowCount=excel.getRowCount("Login");
		//System.out.println("Row count ================"+rowCount);
		
	//	for (int rowNum=2;rowNum<=rowCount+1;rowNum++)
	//	{
	//	loginpage.loginToApplication(excel.getCellData("Login", "UserName", rowNum), excel.getCellData("Login", "Password", rowNum));
	//	}
		
		/*===========================================================*/
		
		
		//Method 3 from DataProvider
			
		loginpage.loginToApplication(UserName, Password);
		
		
		boolean status=loginpage.verifySuccessLoginMsg();
		if(status)
		{
			log.info("Login into application successfully!!");
		}
			
			else
			{
				Assert.assertTrue(false, "Login is not successful!!");
			}
		
		
	}

}
