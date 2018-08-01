package com.abhi.page.toolbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.panel.UploadDocumentPanel;
import com.abhi.utility.StaleElementUtils;

public class ProjectDocumentsToolBar extends ProjectToolBar{


	private final Logger logger = LoggerHelper.getLogger(ProjectDocumentsToolBar.class);


	@FindBy(xpath="//aw-command//button[@id='AP4_AddProjectDocument'][@title='Add Document']")
	WebElement addDocumentButton;

	
	public ProjectDocumentsToolBar(WebDriver driver) 	{

		super(driver);
		PageFactory.initElements(driver, this);
		try
		{
			waitHelper.waitForElement( addDocumentButton,Config.getInstance().getExplicitWait());
		}
		catch(StaleElementReferenceException e )
		{
			StaleElementUtils.refreshElement(driver, addDocumentButton);
		}

	}

	private void clickOnAddDocumentButton(){
		logger.info("clicking on generate report button...");
		this.addDocumentButton.click();

	}

	public UploadDocumentPanel getUploadDocumentPanel(){		

		try
		{
			clickOnAddDocumentButton();
			new UploadDocumentPanel(driver, 1);
		}
		catch(Exception exception)
		{
			clickOnAddDocumentButton();
		}	

		return new UploadDocumentPanel(driver);
	}

}