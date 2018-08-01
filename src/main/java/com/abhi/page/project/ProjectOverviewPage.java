package com.abhi.page.project;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

import toolbar.ProjectDetailsToolBar;
import toolbar.ProjectOverviewToolBar;

public class ProjectOverviewPage extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(ProjectOverviewPage.class);

	
	@FindBy(xpath="//label[text()='PROJECT INFORMATION']")
	private WebElement projectInformationLabel;

	
	
	@FindBy(xpath="//aw-tab-container//aw-tab//li//a[contains(text(),'Details')]")
	private WebElement projectDetailsTab;

	
	@FindBy(xpath="//aw-tab-container//aw-tab//li//a[contains(text(),'Customer Details')]")
	private WebElement projectCustomerDetailsTab;

	@FindBy(xpath="//aw-tab-container//aw-tab//li//a[contains(text(),'Documents')]")
	private WebElement projectDocumentsTab;

	
	public ProjectOverviewPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( projectInformationLabel,Config.getInstance().getExplicitWait());
	}

	public ProjectRightToolBarPage loadRightToolBarPage() {
		return new ProjectRightToolBarPage(driver);
	}
	
	public ProjectOverviewToolBar loadToolBar() {
		return new ProjectOverviewToolBar(driver);
	}	
	
	
	private void clickOnDetailsTab() {
		logger.info("clicking on details tab...");
		projectDetailsTab.click();	
	}
	
	
	private void clickOnCustomerDetailsTab() {
		logger.info("clicking on Customer Details tab...");
		projectCustomerDetailsTab.click();	
	}

	private void clickOnDocumentsTab() {
		logger.info("clicking on details tab...");
		projectDocumentsTab.click();	
	}
	
	public void clickOnProjectDetaileTab() {
		clickOnDetailsTab();
	}
	
	public void clickOnProjectDocumentsTab() {
		clickOnDocumentsTab();
	}
	
	public ProjectCustomerDetailsPage getCustomerDetailsPage() {
		clickOnCustomerDetailsTab();
		return new ProjectCustomerDetailsPage(driver);
	}
	
	public ProjectDetailsPage getProjectDetailsPage() {
		clickOnDetailsTab();
		return new ProjectDetailsPage(driver);
	}
	
	public ProjectDocumentsPage getDocumentsPage() {
		clickOnDocumentsTab();
		return new ProjectDocumentsPage(driver);
	}
}
