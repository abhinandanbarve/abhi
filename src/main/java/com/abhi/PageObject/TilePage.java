package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

public class TilePage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath="//div[contains(text(),'Reports')]")
	private WebElement reportsTile;
	
	public TilePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, reportsTile,new Config(TestBase.OR).getExplicitWait());
	}

	
	private void clickOnReportTile(){
		logger.info("clicking on report tile...");
		reportsTile.click();		
	}

	public SummaryReportPage selectReportTile(){		
		clickOnReportTile();
		return new SummaryReportPage(driver);
	}
}