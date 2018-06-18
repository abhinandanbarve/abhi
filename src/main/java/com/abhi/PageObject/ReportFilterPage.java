package com.abhi.PageObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.helper.assertionHelper.VerificationHelper;
import com.abhi.helper.genericHelper.GenericHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

public class ReportFilterPage extends WebPage{

	public enum ReportType{
		PROJECT,
		MULTIPROJECT
	}

	private final Logger logger = LoggerHelper.getLogger(ReportFilterPage.class);

	@FindBy(xpath="//button[@id='runReportBtn']")
	private WebElement runReportBtn;

	@FindBy(xpath="//button[@id='scheduleNowBtn']")
	private WebElement scheduleNowBtn;

	@FindBy(xpath="//button[@id='openScheduleUIBtn']")
	private WebElement openScheduleUIBtn;

	private ReportType reportType = ReportType.PROJECT;
	HashMap<String, WebElement > filterBean = new HashMap<String, WebElement >();;


	public ReportFilterPage(WebDriver driver) {
		super(driver);
		this.reportType = ReportType.PROJECT;
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, runReportBtn,new Config(TestBase.OR).getTcRALoginWait());
	}	

	public ReportFilterPage(WebDriver driver, ReportType reportType) {
		super(driver);
		this.reportType = reportType;
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, runReportBtn,new Config(TestBase.OR).getTcRALoginWait());
	}

	public ReportFilterPage(WebDriver driver, ReportType reportType, int waitTime) {
		super(driver);
		this.reportType = reportType;
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, runReportBtn,waitTime);
	}
	

	private void clickOnGenerateNow(){
		logger.info("clicking on geneate now button...");
		runReportBtn.click();		
	}


	@SuppressWarnings("unused")
	private void clickOnScheduleNow(){
		logger.info("clicking on geneate now button...");
		scheduleNowBtn.click();		
	}


	@SuppressWarnings("unused")
	private void clickOnSchedule(){
		logger.info("clicking on geneate now button...");
		openScheduleUIBtn.click();		
	}

	public TcRAResultPage selectGeneateNow(){	
		clickOnGenerateNow();
		return new TcRAResultPage(driver);
	}

	public void setFilterCriteria(String filterKey, String filterValue){

		if(filterBean.isEmpty()) {
			createFilterDetails();
			clearDefaultValues();
		}
		WebElement filterValueElement = filterBean.get(filterKey);
		filterValueElement.clear();
		filterValueElement.sendKeys(filterValue);
	}

	private void clearDefaultValues() {
		
		for (Iterator<WebElement> iterator = filterBean.values().iterator(); iterator.hasNext();) {
			 WebElement filterElement = iterator.next();
			 filterElement.clear();
		}
	}

	public void setFilterCriteria(HashMap<String,String> filters){	
	}

	public HashMap<String, WebElement > createFilterDetails()
	{
		if(reportType == ReportType.PROJECT)
			return filterBean;

		List<WebElement> filterList = driver.findElements(By.xpath("//form[@panel-id='filterPanel']/form[@name='awPanelBody']/ul/li"));

		for (WebElement filterComponent : filterList) 
		{

			WebElement filterComponentName = filterComponent.findElement(By.xpath(".//aw-listbox[not(contains(@class,'hidden'))]"));

			if(filterComponentName == null) {
				System.out.println("fiter component found null. Hence continuing...");
				continue;
			}

			Coordinates coordinate = ((Locatable)filterComponentName).getCoordinates();
			coordinate.onPage();
			coordinate.inViewPort();

			String filterAttributeName = filterComponentName.getAttribute("title");
			System.out.println("filterAttributeName ::" + filterAttributeName);

			WebElement filterValueComponent = null;
			try
			{

				filterValueComponent = filterComponent.findElement(By.xpath(".//aw-widget[ not(contains(@class,'hidden')) and not(@prop='prop')]"));

			}
			catch(Exception e)
			{
				System.out.println("Element is Not Present"+e);
				filterValueComponent = filterComponent.findElement(By.xpath(".//tcra-listbox[ not(contains(@class,'hidden')) and contains(@id,'val')]"));

			}

			if(filterValueComponent == null)
				continue;

			WebElement textWebElement= filterValueComponent.findElement(By.cssSelector("input[type='text']"));

			waitHelper.waitForElement(driver, textWebElement,new Config(TestBase.OR).getImplicitWait());


			if(VerificationHelper.verifyElementNotPresent(textWebElement)) 
			{

				System.out.println("Element is Not Present");
			}

			if(!new GenericHelper().isDisplayed(textWebElement)) 
			{
				System.out.println("Element is Not Displayed");

			}

			filterBean.put(filterAttributeName, textWebElement);
			System.out.println(filterBean);

		}

		return filterBean;
	}


}
