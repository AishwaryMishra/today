package homepage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.baseTestClass;

public class TestDataDrivenScript extends baseTestClass
{
	
	@DataProvider(name="testData")
	public Object[][] dataSource()
	{
		return getData("TestData.xlsx", "LoginTestData");
	}
	
	@Test(dataProvider="testData")
	public void testLogin(String userName,String password, String runMode)
	{
		System.out.println("User Name is :"+userName);
		System.out.println("Password is :"+password);
		System.out.println("User Mode is :"+runMode);
	}

}
