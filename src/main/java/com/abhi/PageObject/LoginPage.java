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
public class LoginPage{
	
	WebDriver driver;
	
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//*[@id=\"aw-state-userName\"]")
	WebElement userLink;
	
	@FindBy(xpath="//*[@id=\"main-view\"]/form/div/div/div[2]/div/ul/li[1]/input")
	WebElement userName;
		
	
	@FindBy(xpath="//*[@id=\"main-view\"]/form/div/div/div[2]/div/ul/li[2]/input")
	WebElement password;
	
	@FindBy(xpath="//*[@id=\"main-view\"]/form/div/div/div[2]/div/ul/li[4]/button")
	WebElement submitLogin;
	
	@FindBy(xpath="//*[@id=\"aw-state-userName\"]/div/div[2]/aw-popup-panel/div/div/div/div/div/aw-popup-command-bar/aw-popup-command-cell[2]/li")
	WebElement logout;
	
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
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
		//new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		return new HomePage(driver);
	}
	
	public boolean verifySuccessLoginMsg(){
		return new GenericHelper().isDisplayed(userLink);
	}
		
	
	public void loginToApplication(String userName, String password){		
		enterUserName(userName);
		enterPassword(password);
		clickOnSubmitButton();
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
		
	}
	

}
