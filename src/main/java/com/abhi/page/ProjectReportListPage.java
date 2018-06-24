package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.base.TestBase;
import com.abhi.helper.LoggerHelper;

public class ProjectReportListPage extends WebPage {

	private final Logger logger = LoggerHelper.getLogger(ProjectReportListPage.class);

	@FindBy(xpath="//form[@panel-id='Awp0InContextReportsList']//ul/li")
	private WebElement reportList;
	

	public ProjectReportListPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, reportList,Config.getInstance().getExplicitWait());
	}

	public ProjectReportListPage(WebDriver driver, int waitTime) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, reportList,waitTime);
	}
	
	private void selectReport(String reportName){
		logger.info("selecting report ...."+reportName);
		WebElement report = reportList.findElement(By.xpath("//form[@panel-id='Awp0InContextReportsList']//ul/li//h3[contains(text(),'"+reportName+"')]"));
		report.click();
	}

	public ReportFilterPage selectProjectReport(String reportName){		
		selectReport(reportName);		
		return new ReportFilterPage(driver);
	}
	
}
