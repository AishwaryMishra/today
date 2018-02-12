package loginPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.LoginPage;
import baseTest.baseTestClass;
import baseTest.config;
import helpers.LoggerHelper;

public class LoginTest extends baseTestClass {
	
	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	@Test
	public void testLoginToApplication()
	{
		log.info(LoginTest.class.getName()+" has started");
		
		config conf=new config(OR);
		driver.get(conf.getWebsite());
		LoginPage loginpage=new LoginPage(driver);
		loginpage.loginToApplication(conf.getUserName(), conf.getPassword());
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
