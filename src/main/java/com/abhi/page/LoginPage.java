package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.base.TestBase;
import com.abhi.helper.LoggerHelper;

/**
 * 
 * @author abhinandan
 * 
 */
public class LoginPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath="//input[@type='text' and @placeholder='User Name']")
	private WebElement userName;

	@FindBy(xpath="//input[@type='password' and @placeholder='Password']")
	private WebElement password;

	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	private WebElement submitLogin;


	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, userName,new Config(TestBase.OR).getExplicitWait());
	}


	private void enterUserName(String userName){
		logger.info("entering user name ...."+userName);
		this.userName.sendKeys(userName);
	}

	private void enterPassword(String password){
		logger.info("entering password...."+password);
		this.password.sendKeys(password);
	}

	private void clickOnSubmitButton(){
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
