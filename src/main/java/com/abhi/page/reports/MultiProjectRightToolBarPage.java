package com.abhi.page.reports;

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
import com.abhi.page.reports.ReportFilterPage.ReportType;
import com.abhi.utility.StaleElementUtils;

public class MultiProjectRightToolBarPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);


	//String generateReportBtnPath = "//button[@id='cmdFilterPanelDui'][@title='Generate Report']";
	String generateReportBtnPath = "//button[@id='cmdFilterPanelDui' and @title='Generate Report']";

	@FindBy(xpath="//button[@id='cmdFilterPanelDui' and @title='Generate Report']")
	WebElement generateReportButton;


	public MultiProjectRightToolBarPage(WebDriver driver) 	{

		super(driver);
		PageFactory.initElements(driver, this);
		try
		{
			waitHelper.waitForElement(generateReportButton,Config.getInstance().getExplicitWait());
		}
		catch(StaleElementReferenceException e )
		{
			generateReportButton = driver.findElement(By.xpath(generateReportBtnPath));

			StaleElementUtils.refreshElement(driver, generateReportButton);
		}
		waitHelper.waitForElement(generateReportButton,Config.getInstance().getExplicitWait());

	}

	private void clickOnGenerateReportButton(){
		logger.info("clicking on generate report button...");
		this.generateReportButton.click();

	}

	public ReportFilterPage selectReportCommand(ReportType reportType){		

		try
		{
			clickOnGenerateReportButton();
			new ReportFilterPage(driver, reportType, 2);
		}
		catch(Exception exception)
		{
			clickOnGenerateReportButton();
			clickOnGenerateReportButton();			
		}
		return new ReportFilterPage(driver,reportType);

	}


}