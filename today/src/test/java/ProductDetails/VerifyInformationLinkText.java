package ProductDetails;

import org.apache.log4j.Logger;

import baseTest.baseTestClass;
import baseTest.config;
import helpers.LoggerHelper;


public class VerifyInformationLinkText extends baseTestClass{
	private final Logger log = LoggerHelper.getLogger(VerifyInformationLinkText.class);
	
	public void testVerifyInformationLinkText(){
		log.info(VerifyInformationLinkText.class.getName()+" started");	
		
		config conf = new config(OR);
		driver.get(conf.getWebsite());
	}
}
