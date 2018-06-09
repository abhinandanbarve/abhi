package com.abhi.productDetailsPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abhi.PageObject.HomePage;
import com.abhi.PageObject.LoginPage;
import com.abhi.PageObject.SummaryReportsPage;
import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

/**
 * @author abhinandan
 * 
 */
public class VerifyProductCounts extends TestBase{
	private final Logger log = LoggerHelper.getLogger(VerifyProductCounts.class);
	LoginPage loginPage;
	HomePage homePage;
	
	@Test
	public void testVerifyProductCounts(){
		log.info(VerifyProductCounts.class.getName()+" started");	
		
		Config config = new Config(OR);
		driver.get(config.getWebsite());
		
		homePage = new HomePage(driver);
		SummaryReportsPage pCate = null;//homePage.clickOnMenu(homePage.womenMenu);
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
