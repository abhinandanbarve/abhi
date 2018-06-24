package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;

public class CreateCommentPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(CreateCommentPage.class);


	//aw-radiobutton
	//aw-checkbox

	//$x("//aw-include[@name='AP4_CreateComment']//div[contains(@class,'aw-widgets-propertyContainer')]//aw-property-label//div[contains(text(),'Title:')]/ancestor::div[2]//input[@type='text']")
	
	
	@FindBy(xpath="//aw-include[@name='AP4_CreateComment']//div[contains(@class,'aw-widgets-propertyContainer')]//aw-property-label//div[contains(text(),'Title:')]/ancestor::div[2]//input[@type='text']")
	private WebElement commentTitleText;

	@FindBy(xpath="//aw-include[@name='AP4_CreateComment']//div[contains(@class,'aw-widgets-propertyContainer')]//aw-property-label//div[contains(text(),'Page:')]/ancestor::div[2]//input[@type='text']")
	private WebElement commentPageText;

	@FindBy(xpath="//aw-include[@name='AP4_CreateComment']//div[contains(@class,'aw-widgets-propertyContainer')]//aw-property-label//div[contains(text(),'Section:')]/ancestor::div[2]//input[@type='text']")
	private WebElement commentSectionText;

	//$x("//dnvgl-rich-text-widget//div[@role='presentation']")
	private WebElement commentContentText;
	
	@FindBy(xpath="//aw-include[@name='AP4_CreateComment']//button[@action='uploadOKClicked']//aw-i18n[contains(text(),'Create')]")
	private WebElement saveButton;


	public CreateCommentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, commentTitleText,Config.getInstance().getExplicitWait());
	}

	private void enterCommentTitle(String commentTitle) {
		logger.info("entering Comment title...."+commentTitle);
		commentTitleText.sendKeys(commentTitle);	
		
	}
	
	private void enterCommentPage(String page) {
		logger.info("entering Comment page...."+page);
		commentPageText.sendKeys(page);
	}
	
	private void enterCommentSection(String section) {
		logger.info("entering Comment page...."+section);
		commentSectionText.sendKeys(section);
	}
	
	private void enterCommentContent(String content) {
		logger.info("entering Comment Content...."+content);
		commentContentText.sendKeys(content);		
	}

	
	private void clickOnSaveButton(){
		logger.info("clicking on create comment button...");
		saveButton.click();		
	}
	
	public void createComment(String commentTitle, String page, String section, String content){	
		enterCommentTitle(commentTitle);
		enterCommentPage(page);
		enterCommentSection(section);
		enterCommentContent(content);
		clickOnSaveButton();
	}

	
}