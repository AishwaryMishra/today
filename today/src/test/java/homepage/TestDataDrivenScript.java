package homepage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.LoginPage;
import baseTest.baseTestClass;

public class TestDataDrivenScript extends baseTestClass
{
	
	@DataProvider(name="testData")
	public Object[][] dataSource()
	{
		return getData("NewData.xlsx", "Login");
	}
	
	@Test(dataProvider="testData")
	public void testLogin(String UserName,String Password, String RunMode)
	{
		LoginPage loginpage=new LoginPage(driver);
		loginpage.loginToApplication(UserName, Password);
		
		System.out.println("User Name is :"+UserName);
		System.out.println("Password is :"+Password);
		System.out.println("User Mode is :"+RunMode);
	}

}
