package Registration;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.LoginPage;
import PageObject.MyAccountPage;
import PageObject.RegistrationPage;
import baseTest.baseTestClass;
import baseTest.config;
import helpers.LoggerHelper;


public class Registration extends baseTestClass{
	
	private final Logger log = LoggerHelper.getLogger(Registration.class);
	LoginPage loginPage;
	RegistrationPage register;
	MyAccountPage myAccountPage;
	
	@Test
	public void testLoginToApplication(){
		log.info(Registration.class.getName()+" started");
		
		config conf = new config(OR);
		driver.get(conf.getWebsite());
		
		
		
		loginPage = new LoginPage(driver);
		loginPage.clickOnSignInLink();
		loginPage.enterRegistrationEmail();
		loginPage.clickOnCreateAnAccount();
		
		register = new RegistrationPage(driver);
		register.setMrRadioButton();
		register.setFirstName("firstName");
		register.setLastname("lastname");
		register.setPassword("password");
		register.setAddress("address");
		register.setDay("5");
		register.setMonth("June");
		register.setYear("2017");
		register.setYourAddressFirstName("yourAddressFirstName");
		register.setYourAddressLastName("yourAddressLastName");
		register.setYourAddressCompany("yourAddressCompany");
		register.setYourAddressCity("yourAddressCity");
		register.setYourAddressState("Alaska");
		register.setYourAddressPostCode("99501");
		register.setMobilePhone("9999999999");
		register.setAddressAlias("addressAlias");
		register.clickRegistration();
		
		myAccountPage = new MyAccountPage(driver);
		
		if(myAccountPage.verifySuccessRegistrationMsg()){
			log.info("Registration is sucessful");
		}
		else{
			Assert.assertTrue(false, "Registration is not sucessful");
		}
	}
}
