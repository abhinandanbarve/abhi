package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;

public class ProjectDocumentDetailsPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(ProjectDocumentDetailsPage.class);

	//$x("//aw-secondary-workarea//aw-walker-element//aw-table//aw-table-command-cell//span[contains(text(),'Received Documents')]/ancestor::aw-table-command-cell//button[@title ='Open']")
	@FindBy(xpath="//aw-secondary-workarea//aw-walker-element//aw-table//aw-table-command-cell//span[contains(text(),'Received Documents')]/ancestor::aw-table-command-cell//button[@title ='Open']")
	private WebElement openReceivedDocument;

	public ProjectDocumentDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( openReceivedDocument,Config.getInstance().getExplicitWait());
	}

	private void clickOnOpenReceivedDocumentIcon(){
		logger.info("clicking on open Received Document icon...");
		openReceivedDocument.click();		
	}

	public ReceivedDocumentsPage openReceivedDocument(){		
		clickOnOpenReceivedDocumentIcon();
		return new ReceivedDocumentsPage(driver);
	}
}