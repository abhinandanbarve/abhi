package com.abhi.productDetailsPage;

import org.apache.log4j.Logger;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

/**
 * @author abhinandan
 * 
 */
public class VerifyInformationLinkText extends TestBase{
	private final Logger log = LoggerHelper.getLogger(VerifyInformationLinkText.class);
	
	public void testVerifyInformationLinkText(){
		log.info(VerifyInformationLinkText.class.getName()+" started");	
		
		Config config = new Config(OR);
		driver.get(config.getWebsite());
	}
}
