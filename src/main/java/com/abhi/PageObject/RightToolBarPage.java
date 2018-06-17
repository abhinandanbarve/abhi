package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.PageObject.ReportFilterPage.ReportType;
import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.helper.Wait.StaleElementUtils;
import com.abhi.helper.assertionHelper.VerificationHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

public class RightToolBarPage extends WebPage{


	private final Logger log = LoggerHelper.getLogger(LoginPage.class);

	
	//String generateReportBtnPath = "//button[@id='cmdFilterPanelDui'][@title='Generate Report']";
	String generateReportBtnPath = "//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']";

	@FindBy(xpath="//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']")
	WebElement generateReportButton;

	
	public RightToolBarPage(WebDriver driver) 	{

		super(driver);
		PageFactory.initElements(driver, this);
		try
		{
			waitHelper.waitForElement(driver, generateReportButton,new Config(TestBase.OR).getExplicitWait());
		}
		catch(StaleElementReferenceException e )
		{
			generateReportButton = driver.findElement(By.xpath(generateReportBtnPath));

			StaleElementUtils.refreshElement(driver, generateReportButton);
		}
		waitHelper.waitForElement(driver, generateReportButton,new Config(TestBase.OR).getExplicitWait());

	}

	public void clickOnGenerateReportButton(){
		log.info("clicking on generate report button...");
		this.generateReportButton.click();

	}

	public WebPage selectReportCommand(ReportType reportType){		
		
		try
		{
			clickOnGenerateReportButton();
			new ProjectReportListPage(driver, 1);
		}
		catch(Exception exception)
		{
			clickOnGenerateReportButton();
		}
		
		
		WebPage tcraPage = null;
		if(reportType.equals(ReportType.PROJECT))
			tcraPage = new ProjectReportListPage(driver);
		else
			tcraPage = new ReportFilterPage(driver);
		
		return tcraPage;
	}


}