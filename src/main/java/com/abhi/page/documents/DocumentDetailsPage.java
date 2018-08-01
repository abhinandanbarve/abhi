package com.abhi.page.documents;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.documents.toolbar.DocumentDetailsToolBar;
import com.abhi.page.project.ProjectOverviewPage;

public class DocumentDetailsPage extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(ProjectOverviewPage.class);

	
	@FindBy(xpath="//label[text()='PROJECT INFORMATION']")
	private WebElement projectInformationLabel;

	
	
	@FindBy(xpath="//aw-tab-container//aw-tab//li//a[contains(text(),'Comments')]")
	private WebElement documentCommentsTab;

	
	public DocumentDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(documentCommentsTab,Config.getInstance().getExplicitWait());
	}

	public DocumentDetailsToolBar loadRightToolBarPage() {
		return new DocumentDetailsToolBar(driver);
	}
	
	private void clickOnCommentsTab() {
		logger.info("clicking on Comments tab...");
		documentCommentsTab.click();	
	}
	
	public void clickOnDocumentsCommentsTab() {
		clickOnCommentsTab();
	}
}