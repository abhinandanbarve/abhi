package com.abhi.page.login;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

public class LogoutPage extends WebPage{

	private final Logger log = LoggerHelper.getLogger(LogoutPage.class);

	@FindBy(xpath = "//*[@id=\"aw-state-userName\"]")
	private WebElement userNameLink;

	@FindBy(xpath="//*[@id='aw-state-userName']//li[contains(text(),'Sign Out')]")
	private WebElement logoutLink;

	public LogoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( userNameLink,Config.getInstance().getExplicitWait());
	}

	private void clickOnUserNameLink(){
		log.info("clicked on user name link...");
		userNameLink.click();
	}

	private void clickOnLogoutLink(){
		log.info("clicked on logout link...");
		logoutLink.click();
	}

	public void logoutTcApplication(){
		clickOnUserNameLink();
		clickOnLogoutLink();

	}
}
