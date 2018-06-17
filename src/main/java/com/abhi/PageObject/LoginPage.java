package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

/**
 * 
 * @author abhinandan
 * 
 */
public class LoginPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath="//input[@type='text' and @placeholder='User Name']")
	WebElement userName;

	@FindBy(xpath="//input[@type='password' and @placeholder='Password']")
	WebElement password;

	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	WebElement submitLogin;


	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, userName,new Config(TestBase.OR).getExplicitWait());
	}


	public void enterUserName(String userName){
		logger.info("entering user name ...."+userName);
		this.userName.sendKeys(userName);
	}

	public void enterPassword(String password){
		logger.info("entering password...."+password);
		this.password.sendKeys(password);
	}

	public void clickOnSubmitButton(){
		logger.info("clicking on submit button...");
		submitLogin.click();		
	}

	
	public HomePage loginToApplication(String userName, String password){		
		enterUserName(userName);
		enterPassword(password);
		clickOnSubmitButton();
		return new HomePage(driver);
	}
}
