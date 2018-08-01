package com.abhi.page.toolbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.panel.UploadTransmittalPanel;
import com.abhi.utility.StaleElementUtils;

public class ProjectOverviewToolBar extends ProjectToolBar{

	private final Logger logger = LoggerHelper.getLogger(ProjectOverviewToolBar.class);
	
	
	@FindBy(xpath="//aw-command//button[@id='AP4_UploadBatchDocuments'][@title='Upload Transmittal']")
	WebElement uploadTransmittal;
	
	public ProjectOverviewToolBar(WebDriver driver) 	{

		super(driver);
		PageFactory.initElements(driver, this);
		try
		{
			waitHelper.waitForElement( uploadTransmittal,Config.getInstance().getExplicitWait());
		}
		catch(StaleElementReferenceException e )
		{
			StaleElementUtils.refreshElement(driver, uploadTransmittal);
		}

	}

	private void clickOnUploadTransmittalButton(){
		logger.info("clicking on generate report button...");
		this.uploadTransmittal.click();

	}
	
	public UploadTransmittalPanel getUploadTransmittalPanel(){		
		
		try
		{
			clickOnUploadTransmittalButton();
			new UploadTransmittalPanel(driver, 1);
		}
		catch(Exception exception)
		{
			clickOnUploadTransmittalButton();
		}	
		
		return new UploadTransmittalPanel(driver);
	}

}
