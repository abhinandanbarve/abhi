package com.abhi.page.toolbar;

import org.apache.log4j.Logger;
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

public class ProjectToolBar extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(ProjectToolBar.class);
	
	@FindBy(xpath="//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']")
	WebElement generateReport;

	@FindBy(xpath="//aw-command//button[@id='Awp0ObjectInfo'][@title='Information']")
	WebElement information;

	@FindBy(xpath="//aw-command//button[@id='AP4_TrainingVideos'][@title='Teamcenter Help']")
	WebElement teamcenterHelp;
	
	 
	public ProjectToolBar(WebDriver driver) 	{

		super(driver);
		PageFactory.initElements(driver, this);
		try
		{
			waitHelper.waitForElement( generateReport,Config.getInstance().getExplicitWait());
		}
		catch(StaleElementReferenceException e )
		{
			StaleElementUtils.refreshElement(driver, generateReport);
		}

	}

	private void clickOnGenerateReportButton(){
		logger.info("clicking on generate report button...");
		this.generateReport.click();

	}

	private void clickOnInformationButton(){
		logger.info("clicking on Information button...");
		this.information.click();

	}
	
	private void clickOnTeamcenterButton(){
		logger.info("clicking on teamcenter Help button...");
		this.teamcenterHelp.click();

	}	
	
	public TcRAReportListPanel getTcRAReportListPanel(){		
		
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