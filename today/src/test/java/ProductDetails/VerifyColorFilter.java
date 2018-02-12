package ProductDetails;

import org.apache.log4j.Logger;


import baseTest.baseTestClass;
import baseTest.config;
import helpers.LoggerHelper;

public class VerifyColorFilter extends baseTestClass{
	private final Logger log = LoggerHelper.getLogger(VerifyColorFilter.class);
	
	public void testVerifyColorFilter(){
		log.info(VerifyColorFilter.class.getName()+" started");	
		
		config conf = new config(OR);
		driver.get(conf.getWebsite());
	}

}
