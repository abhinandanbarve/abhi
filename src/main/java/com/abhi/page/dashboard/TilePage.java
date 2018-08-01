package com.abhi.page.dashboard;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.login.LoginPage;
import com.abhi.page.project.create.CreateProjectPage;
import com.abhi.page.reports.SummaryReportPage;

public class TilePage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath="//div[contains(text(),'Reports')]")
	private WebElement reportsTile;
	
	@FindBy(xpath="//div[contains(text(),'Create Project')]")
	private WebElement createProjectTile;
	
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
	
	private void clickOnCreateProjectTile(){
		logger.info("clicking on create Project tile...");
		createProjectTile.click();		
	}

	public CreateProjectPage selectCreateProjectTile(){		
		clickOnCreateProjectTile();
		return new CreateProjectPage(driver);
	}
}