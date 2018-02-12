package ProductDetails;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.ProductCategoryPage;
import baseTest.baseTestClass;
import baseTest.config;
import helpers.LoggerHelper;

public class VerifyProductCounts extends baseTestClass{
	private final Logger log = LoggerHelper.getLogger(VerifyProductCounts.class);
	LoginPage loginPage;
	HomePage homePage;
	
	@Test
	public void testVerifyProductCounts(){
		log.info(VerifyProductCounts.class.getName()+" started");	
		
		config conf = new config(OR);
		driver.get(conf.getWebsite());
		
		homePage = new HomePage(driver);
		ProductCategoryPage pCate = homePage.clickOnMenu(homePage.womenMenu);
		pCate.selectColor(pCate.Orange);
		int count = pCate.getTotalProducts();
		
		if(count==3){
			log.info("product count is matching");
		}
		else{
			Assert.assertTrue(false,"product count is not matching");	
		}
		
	}

}
