package com.abhi.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.base.TestBase;
import com.abhi.helper.LoggerHelper;
/**
 * 
 * @author abhinandan
 * 
 */
public class SummaryReportPage extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(SummaryReportPage.class);


	@FindBy(xpath="//h3[@id='CellTitle']")
	private List<WebElement> reportList;

	@FindBy(xpath="//h3[@id='CellTitle']")
	private WebElement reportItem;


	public SummaryReportPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, reportItem,Config.getInstance().getTcRALoginWait());
	}


	private void clickOnReportName(String reportName){

		//h3[@id='CellTitle'][@class='aw-widgets-cellListCellTitle ng-binding'] [contains(text(),'Comment Status')]")
		reportItem = driver.findElement(By.xpath("//h3[@id='CellTitle' and text()='"+reportName+"']"));
		logger.info("clickin on report : "+reportName);
		reportItem.click();
	}

	public MultiProjectRightToolBarPage selectReport(String reportName){

		clickOnReportName(reportName);
		return new MultiProjectRightToolBarPage(driver);
	}


}
