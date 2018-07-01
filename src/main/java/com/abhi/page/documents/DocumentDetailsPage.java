package com.abhi.page.documents;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.project.ProjectDetailsPage;

public class DocumentDetailsPage extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(ProjectDetailsPage.class);

	
	@FindBy(xpath="//label[text()='PROJECT INFORMATION']")
	private WebElement projectInformationLabel;

	
	
	@FindBy(xpath="//aw-tab-container//aw-tab//li//a[contains(text(),'Comments')]")
	private WebElement documentCommentsTab;

	
	public DocumentDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(documentCommentsTab,Config.getInstance().getExplicitWait());
	}

	public DocumentsRightToolBarPage loadRightToolBarPage() {
		return new DocumentsRightToolBarPage(driver);
	}
	
	private void clickOnCommentsTab() {
		logger.info("clicking on Comments tab...");
		documentCommentsTab.click();	
	}
	
	public void clickOnDocumentsCommentsTab() {
		clickOnCommentsTab();
	}
}