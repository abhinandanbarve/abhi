package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

public class TcRAResultPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath = "//*[@id='eq-frame']")
	public WebElement eQFrame;
	
	
	@FindBy(xpath="//div[@id='setting_icon' and @title='Report Options']")
	WebElement exportOptions;

	@FindBy(xpath="//div[@class='js-top-row-section topRowNumber']")
	WebElement totalRowLabel;


	public TcRAResultPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, eQFrame,new Config(TestBase.OR).getExplicitWait());
	}


	public void renderReport(){
		logger.info("rendering report ....");
		driver.switchTo().frame(eQFrame);
		waitHelper.waitForElement(driver, totalRowLabel,new Config(TestBase.OR).getTcRALoginWait());
	}

	private int getRowCount() {
		int rowCount = -1;
		logger.info("checking row count ....");
		String totalRow = totalRowLabel.getText();
		if(totalRow != null && totalRow.toLowerCase().contains("row") && totalRow.toLowerCase().contains("of")) {
			String totalRowCount = totalRow.substring(totalRow.indexOf("of")+2, totalRow.length());
			logger.info("checking row counttotalRowCount ...."+totalRowCount);
			rowCount = Integer.parseInt(totalRowCount.trim());
		}
			
			
		return rowCount;
	}
	
	public int renderReportAndGetRowCount(){
		int rowCount = -1;

		try {

			logger.info("rendering report....");
			renderReport();
			rowCount = getRowCount();
		}
		finally {
			switchToParentWindow();
		}
		return rowCount;
	}


	
	public void switchToParentWindow(){
		logger.info("rendering report ....");
		driver.switchTo().defaultContent();
	}

	
	
	private String verifyeQResult() {
		String resultRows = "";
		try {
			
			WebElement eqFrame= driver.findElement(By.id("eq-frame"));
			driver.switchTo().frame(eqFrame);
			
			WebElement reportOption = driver.findElement(By.xpath("//div[@id='setting_icon' and @title='Report Options']"));
			
			waitHelper.waitForElement(driver, reportOption,new Config(TestBase.OR).getTcRALoginWait());
			
			WebElement rowNumberLabel = driver.findElement(By.xpath("//div[@class='js-top-row-section topRowNumber']"));
			waitHelper.waitForElement(driver, rowNumberLabel,new Config(TestBase.OR).getTcRALoginWait());
			
			resultRows  = rowNumberLabel.getText();
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().defaultContent();
		 return resultRows;
	}
}
