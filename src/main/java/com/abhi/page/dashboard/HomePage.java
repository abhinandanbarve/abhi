package com.abhi.page.dashboard;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.helper.VerificationHelper;
import com.abhi.page.WebPage;
import com.abhi.page.globalsearch.GlobelSearchPage;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.reports.ItemReportPage;
import com.abhi.page.reports.SummaryReportPage;

/**
 * 
 * @author abhinandan
 * 
 */
public class HomePage extends WebPage{
	
	private final Logger logger = LoggerHelper.getLogger(HomePage.class);
	
	
	@FindBy(xpath = "//*[@id=\"aw-state-userName\"]")	
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']")
	public WebElement dressesMenu;
	
	
	@FindBy(xpath="//div[contains(text(),'Reports')]")
	public WebElement reportsTile;

	
	@FindBy(xpath="//aw-search-global//aw-link-with-popup-menu//a[contains(text(),'Category:')]")
	public WebElement globalSearchLink;

	@FindBy(xpath="//aw-search-global//aw-link-with-popup-menu//ul/li[contains(text(),'Projects/Opportunities')]")
	public WebElement selectProjectOnSearchMenu;
	
	@FindBy(xpath="//aw-search-global//aw-search-box//input[@type='text']")
	public WebElement globalSearchText;
	
	@FindBy(xpath="//aw-search-global//aw-search-box//aw-icon")
	public WebElement globalSearchButton;
	
	@FindBy(xpath="//div[@ng-controller='awTableContainerController']//table/tbody/tr/td//a[@title='Open']")
	public WebElement selectProject;
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(reportsTile,Config.getInstance().getTcRALoginWait());
	}
	
	
//	public SummaryReportPage clickOnSummaryReportsTile(){
//		logger.info("clickin on Reports:");
//		reportsTile.click();
//		return new SummaryReportPage(driver);
//	}
//	
//	
//	public ItemReportPage clickOnGlobalSearchLink(){
//		logger.info("clickin on Reports:");
//		globalSearchLink.click();
//		selectProjectOnSearchMenu.click();
//		globalSearchText.sendKeys("316837");
//		globalSearchButton.click();
//		waitHelper.waitForElement( selectProject,Config.getInstance().getExplicitWait());
//		selectProject.click();
//		return new ItemReportPage(driver);		
//	}
	
	public boolean isLoginSucsess(){
		logger.info("cheking Reports tile present or not");
		return VerificationHelper.verifyElementPresent(reportsTile);
	}
	
	public GlobelSearchResultPage searchProject(String project){
		logger.info("searching project..");
		GlobelSearchPage globelSearchPage = new GlobelSearchPage(driver);
		return globelSearchPage.searchProject(project);
	}


	public GlobelSearchResultPage searchDocuments(String document, int timeout) {
		logger.info("searching document..");
		GlobelSearchPage globelSearchPage = new GlobelSearchPage(driver);
		return globelSearchPage.searchDocuments(document, timeout);
	}

	
	
}
