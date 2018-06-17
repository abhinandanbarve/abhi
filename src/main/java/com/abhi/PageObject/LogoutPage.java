package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

public class LogoutPage extends WebPage{

	private final Logger log = LoggerHelper.getLogger(LogoutPage.class);
	
	@FindBy(xpath = "//*[@id=\"aw-state-userName\"]")
	WebElement userNameLink;
	
	@FindBy(xpath="//*[@id='aw-state-userName']//li[contains(text(),'Sign Out')]")
	WebElement logoutLink;
	
	
	@FindBy(xpath="//input[@name='userName'][@type='text'][@placeholder='User Name']")
	WebElement userName;
	
	public LogoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, userNameLink,new Config(TestBase.OR).getExplicitWait());
	}
	
	public void clickOnUserNameLink(){
		log.info("clicked on user name link...");
		userNameLink.click();
	}
	
	public void clickOnLogoutLink(){
		log.info("clicked on logout link...");
		logoutLink.click();
	}
	
	public void logoutTcApplication(){
		clickOnUserNameLink();
		clickOnLogoutLink();
		
	}
}
