package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;

public class TilePage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath="//div[contains(text(),'Reports')]")
	private WebElement reportsTile;
	
	public TilePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(reportsTile,Config.getInstance().getExplicitWait());
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