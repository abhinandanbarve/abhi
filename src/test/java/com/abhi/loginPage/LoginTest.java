package com.abhi.loginPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.abhi.PageObject.HomePage;
import com.abhi.PageObject.LoginPage;
import com.abhi.PageObject.LogoutPage;
import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.TestBase;

/**
 * 
 * @author abhinandan
 * 
 */
public class LoginTest extends TestBase{
	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	@Test
	public void testLoginToApplication(){
		log.info(LoginTest.class.getName()+" started");
		
		driver.get(config.getWebsite());
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.loginToApplication(config.getUserName(), config.getPassword());
		
		HomePage homePage = new HomePage(driver);
		
		boolean status = homePage.isLoginSucsess();
		
		if(status){
		   log.info("login is sucessful");	
		}
		else{
			Assert.assertTrue(false, "login is not sucessful");
		}
	}
	
	@AfterMethod
	public void afterMethod() {
		
		try {
			LogoutPage logoutPage = new LogoutPage(driver);
			logoutPage.logoutTcApplication();
		}
		catch(Exception exception ) {
			log.error(exception.getMessage(), exception);
		}
		
	}

}
