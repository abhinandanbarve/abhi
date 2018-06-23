package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.base.TestBase;
import com.abhi.helper.logger.LoggerHelper;

public class GlobalHomeIcon extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(GlobalHomeIcon.class);

	
	@FindBy(id="AP4_Home")
	private WebElement globelHomeBtn;


	public GlobalHomeIcon(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, globelHomeBtn,new Config(TestBase.OR).getExplicitWait());
	}

	public void clickOnHomeButton(){
		logger.info("clicking on globel Home button...");
		globelHomeBtn.click();		
	}
	
	
}
