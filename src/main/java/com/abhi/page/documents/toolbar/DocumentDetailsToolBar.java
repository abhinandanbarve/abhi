package com.abhi.page.documents.toolbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.documents.panel.CreateCommentPanel;

public class DocumentDetailsToolBar extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(DocumentDetailsToolBar.class);

	
	//String generateReportBtnPath = "//button[@id='cmdFilterPanelDui'][@title='Generate Report']";
	String generateReportBtnPath = "//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']";

	@FindBy(xpath="//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']")
	WebElement generateReportButton;

	
	@FindBy(xpath="//aw-command//button[@id='AP4_CreateCommentChain'][@title='Create Comment']")
	WebElement createCommentButton;

	
	public DocumentDetailsToolBar(WebDriver driver) 	{

		super(driver);
		PageFactory.initElements(driver, this);		
		waitHelper.waitForElement(createCommentButton,Config.getInstance().getExplicitWait());

	}

	public void clickOnGenerateReportButton(){
		logger.info("clicking on generate report button...");
		this.generateReportButton.click();

	}

	private void clickOnCreateCommentButton(){
		logger.info("clicking on add document button...");
		this.createCommentButton.click();

	}
	
	public CreateCommentPanel getCreateCommentPanel(){
		clickOnCreateCommentButton();
		return new CreateCommentPanel(driver);
	}
	
	
	

}