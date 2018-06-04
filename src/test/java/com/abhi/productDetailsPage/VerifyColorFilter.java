package com.abhi.productDetailsPage;

import org.apache.log4j.Logger;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

/**
 * @author abhinandan
 * 
 */
public class VerifyColorFilter extends TestBase{
	private final Logger log = LoggerHelper.getLogger(VerifyColorFilter.class);
	
	public void testVerifyColorFilter(){
		log.info(VerifyColorFilter.class.getName()+" started");	
		
		Config config = new Config(OR);
		driver.get(config.getWebsite());
	}

}
