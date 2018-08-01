package com.abhi.page.project;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.documents.AdminDocumentsPage;
import com.abhi.page.documents.ReceivedDocumentsPage;

import toolbar.ProjectDetailsToolBar;
import toolbar.ProjectDocumentsToolBar;

public class ProjectDocumentsPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(ProjectDocumentsPage.class);

	//$x("//aw-secondary-workarea//aw-walker-element//aw-table//aw-table-command-cell//span[contains(text(),'Received Documents')]/ancestor::aw-table-command-cell//button[@title ='Open']")
	@FindBy(xpath="//aw-secondary-workarea//aw-walker-element//aw-table//aw-table-command-cell//span[contains(text(),'Received Documents')]/ancestor::aw-table-command-cell//button[@title ='Open']")
	private WebElement openReceivedDocumentFolder;

	@FindBy(xpath="//aw-secondary-workarea//aw-walker-element//aw-table//aw-table-command-cell//span[contains(text(),'Admin')]/ancestor::aw-table-command-cell//button[@title ='Open']")
	private WebElement openAdminFolder;


	public ProjectDocumentsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( openAdminFolder,Config.getInstance().getExplicitWait());
		waitHelper.waitForElement( openReceivedDocumentFolder,Config.getInstance().getExplicitWait());
	}

	public ProjectDocumentsToolBar loadToolBar() {
		return new ProjectDocumentsToolBar(driver);
	}	
	
	
	private void clickOnAdminOpenFolderIcon(){
		logger.info("clicking on open Received Document icon...");
		openReceivedDocumentFolder.click();		
	}

	public AdminDocumentsPage openAdminFolder(){		
		clickOnAdminOpenFolderIcon();
		return new AdminDocumentsPage(driver);
	}

	
	private void clickOnOpenReceivedDocumentIcon(){
		logger.info("clicking on open Received Document icon...");
		openReceivedDocumentFolder.click();		
	}

	public ReceivedDocumentsPage openReceivedDocument(){		
		clickOnOpenReceivedDocumentIcon();
		return new ReceivedDocumentsPage(driver);
	}
}