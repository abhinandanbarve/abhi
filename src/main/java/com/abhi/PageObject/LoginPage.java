package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.helper.Wait.WaitHelper;
import com.abhi.helper.genericHelper.GenericHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

/**
 * 
 * @author abhinandan
 * 
 */
public class LoginPage extends WebPage{


	private final Logger log = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath="//input[@name='userName'][@type='text'][@placeholder='User Name']")
	WebElement userName;
	

	@FindBy(xpath="//input[@placeholder='Password'][@type='password']")
	WebElement password;
	
	@FindBy(xpath="//button[text()='Sign in']")
	WebElement submitLogin;

	
	@FindBy(xpath = "//*[@id=\"aw-state-userName\"]")	
	public WebElement userLink;
	
	@FindBy(xpath="//div[contains(text(),'Reports')]")
	public WebElement reportsTile;

	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, userName,new Config(TestBase.OR).getExplicitWait());
	}


	public void enterUserName(String userName){
		log.info("entering user name fdfgd ergret rgrtt ...."+userName);
		this.userName.sendKeys(userName);
	}

	public void enterPassword(String password){
		log.info("entering password...."+password);
		this.password.sendKeys(password);
	}

	public HomePage clickOnSubmitButton(){
		log.info("clicking on submit button...");
		submitLogin.click();
		return new HomePage(driver);
	}

	public boolean verifySuccessLoginMsg(){
		return new GenericHelper().isDisplayed(userLink) && new GenericHelper().isDisplayed(reportsTile);
	}


	public void loginToApplication(String userName, String password){		
		enterUserName(userName);
		enterPassword(password);
		clickOnSubmitButton();
	}
}
