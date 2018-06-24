package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.base.TestBase;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.ReportFilterPage.ReportType;
import com.abhi.utility.StaleElementUtils;

public class DocumentsRightToolBarPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	
	//String generateReportBtnPath = "//button[@id='cmdFilterPanelDui'][@title='Generate Report']";
	String generateReportBtnPath = "//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']";

	@FindBy(xpath="//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']")
	WebElement generateReportButton;

	@FindBy(xpath="//aw-command//button[@id='AP4_AddProjectDocument'][@title='Add Document']")
	WebElement addDocumentButton;

	
	public DocumentsRightToolBarPage(WebDriver driver) 	{

		super(driver);
		PageFactory.initElements(driver, this);		
		waitHelper.waitForElement(driver, generateReportButton,Config.getInstance().getExplicitWait());

	}

	public void clickOnGenerateReportButton(){
		logger.info("clicking on generate report button...");
		this.generateReportButton.click();

	}

	private void clickOnCreateCommentButton(){
		logger.info("clicking on add document button...");
		this.addDocumentButton.click();

	}
	
	public CreateCommentPage selectAddCommentCommand(){
		clickOnCreateCommentButton();
		return new CreateCommentPage(driver);
	}
	
	
	

}