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
	WebElement userLink;
	
	@FindBy(xpath="//li[text()='Sign Out']")
	WebElement logout;
	
	@FindBy(xpath="//input[@name='userName'][@type='text'][@placeholder='User Name']")
	WebElement userName;
	
	public LogoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, userLink,new Config(TestBase.OR).getExplicitWait());
	}
	
	public void clickOnUserLink(){
		log.info("clicked on user in link...");
		userLink.click();
	}
	
	public void clickOnLogoutButton(){
		log.info("clicked on logout link...");
		logout.click();
	}
	
	
	public void logoutFromToApplication(){
		clickOnUserLink();
		clickOnLogoutButton();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitHelper.waitForElement(driver, userName,new Config(TestBase.OR).getExplicitWait());
	}
}
