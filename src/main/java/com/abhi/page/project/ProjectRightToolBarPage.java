package com.abhi.page.project;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.login.LoginPage;
import com.abhi.page.reports.TcRAReportListPanel;
import com.abhi.utility.StaleElementUtils;

public class ProjectRightToolBarPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	
	//String generateReportBtnPath = "//button[@id='cmdFilterPanelDui'][@title='Generate Report']";
	String generateReportBtnPath = "//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']";

	@FindBy(xpath="//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']")
	WebElement generateReportButton;

//	@FindBy(xpath="//aw-command//button[@id='AP4_AddProjectDocument'][@title='Add Document']")
//	WebElement addDocumentButton;
//	
//	 
	public ProjectRightToolBarPage(WebDriver driver) 	{

		super(driver);
		PageFactory.initElements(driver, this);
		try
		{
			waitHelper.waitForElement( generateReportButton,Config.getInstance().getExplicitWait());
		}
		catch(StaleElementReferenceException e )
		{
			generateReportButton = driver.findElement(By.xpath(generateReportBtnPath));

			StaleElementUtils.refreshElement(driver, generateReportButton);
		}
		waitHelper.waitForElement( generateReportButton,Config.getInstance().getExplicitWait());

	}

	public void clickOnGenerateReportButton(){
		logger.info("clicking on generate report button...");
		this.generateReportButton.click();

	}

//	private void clickOnAddDocumentButton(){
//		logger.info("clicking on add document button...");
//		this.addDocumentButton.click();
//
//	}
	
//	public UploadDocumentPanel selectAddDocumentCommand(){
//		clickOnAddDocumentButton();
//		return new UploadDocumentPanel(driver);
//	}
	
	
	public TcRAReportListPanel generateReportCommand(){		
		
		try
		{
			clickOnGenerateReportButton();
			new TcRAReportListPanel(driver, 1);
		}
		catch(Exception exception)
		{
			clickOnGenerateReportButton();
		}	
		
		return new TcRAReportListPanel(driver);
	}


}