package com.abhi.test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.abhi.base.Config;
import com.abhi.base.TestBase;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.login.LoginPage;
import com.abhi.page.login.LogoutPage;

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
		
		driver.get(Config.getInstance().getWebsite());
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.loginToApplication(Config.getInstance().getUserName(), Config.getInstance().getPassword());
		
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
