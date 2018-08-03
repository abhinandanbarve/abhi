package com.abhi.page.task;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.globalsearch.GlobelSearchResultPage;

public class MyTaskPage extends WebPage{

	
	private final Logger logger = LoggerHelper.getLogger(GlobelSearchResultPage.class);

	
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-primaryWorkarea')]//ul//li")	
	private WebElement taskList;
	
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-primaryWorkarea')]//ul//li//a[@title='Open']")
	private WebElement selectDocument;
	
	
	//$x("//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-primaryWorkarea')]//ul//li//h3[text()='Preview and Unzip Transmittal']/ancestor::li//label[text()='abhinandan-transmittal-name']/ancestor::li")
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-primaryWorkarea')]//ul//li//a[@title='Open']")
	private WebElement transmittalTask;
	
	
	public MyTaskPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( taskList,Config.getInstance().getExplicitWait());
	}	
	
	private void selectTransmittalTask(){
		logger.info("opening Project Details...");
		this.taskList.click();
	}
	

	public TransmittalTaskPage openTransmittalTask(String searchValue){		
		selectTransmittalTask();
		return new TransmittalTaskPage(driver);
	}

	
	
}
