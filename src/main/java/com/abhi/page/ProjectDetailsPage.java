package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;

public class ProjectDetailsPage extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(ProjectDetailsPage.class);

	
	@FindBy(xpath="//label[text()='PROJECT INFORMATION']")
	private WebElement projectInformationLabel;

	
	
	@FindBy(xpath="//aw-tab-container//aw-tab//li//a[contains(text(),'Details')]")
	private WebElement projectDetailsTab;

	@FindBy(xpath="//aw-tab-container//aw-tab//li//a[contains(text(),'Documents')]")
	private WebElement projectDocumentsTab;

	
	public ProjectDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( projectInformationLabel,Config.getInstance().getExplicitWait());
	}

	public ProjectRightToolBarPage loadRightToolBarPage() {
		return new ProjectRightToolBarPage(driver);
	}
	
	private void clickOnDetailsTab() {
		logger.info("clicking on details tab...");
		projectDetailsTab.click();	
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
}
