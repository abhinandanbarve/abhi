package com.abhi.page.globalsearch;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.documents.DocumentDetailsPage;
import com.abhi.page.project.ProjectOverviewPage;

public class GlobelSearchResultPage extends WebPage{

	
	private final Logger logger = LoggerHelper.getLogger(GlobelSearchResultPage.class);

	//@FindBy(xpath="//div[@ng-controller='awTableContainerController']//table/tbody/tr/td//a[@title='Open']")
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-primaryWorkarea')]//ul//li//a[@title='Open']")	
	private WebElement selectProject;
	
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-primaryWorkarea')]//ul//li//a[@title='Open']")
	private WebElement selectDocument;
	
	
	public GlobelSearchResultPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( selectProject,Config.getInstance().getExplicitWait());
	}
	
	public GlobelSearchResultPage(WebDriver driver, int seconds) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( selectDocument,seconds);
	}
	
	private void openProjectDetails(){
		logger.info("opening Project Details...");
		this.selectProject.click();
	}
	
	private void openDocumentsDetails(){
		logger.info("opening Document Details...");
		this.selectDocument.click();
	}

	public ProjectOverviewPage openProjectDetails(String searchValue){		
		openProjectDetails();
		return new ProjectOverviewPage(driver);
	}

	public DocumentDetailsPage openDocumentsDetails(String searchValue){		
		openDocumentsDetails();
		return new DocumentDetailsPage(driver);
	}

	
}
