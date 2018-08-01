package com.abhi.page.documents.panel;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.JavaScriptHelper;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

@SuppressWarnings("unused")
public class CreateCommentPanel extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(CreateCommentPanel.class);


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

	@FindBy(xpath="//aw-include[@name='AP4_CreateComment']//button[@action='createCommentData']")
	private WebElement createComment;



	public CreateCommentPanel(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(commentTitleText,Config.getInstance().getExplicitWait());
	}

	private void enterCommentTitle(String commentTitle) {
		logger.info("entering Comment title...."+commentTitle);
		commentTitleText.sendKeys(commentTitle);	

	}

	private void enterCommentPage(String page) {
		logger.info("entering Comment page...."+page);
		commentPageText.clear();
		commentPageText.sendKeys(page);
	}

	private void enterCommentSection(String section) {
		logger.info("entering Comment page...."+section);
		commentSectionText.sendKeys(section);
	}


	private void enterCommentContent(String content) {
		logger.info("entering Comment Content...."+content);
		for(int i=0; i < 4; i++)
		{
			try {
				
				JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
				String script = "document.getElementsByClassName('cke_wysiwyg_frame cke_reset').contentWindow.document.body.innerHTML = '"+content+"'";

				try {
					Thread.sleep(1000);
					script = "document.getElementsByClassName('cke_wysiwyg_frame cke_reset')[0].contentWindow.document.body.innerHTML = '<p>"+content+"<p>'";
					Object executeScript = javaScriptHelper.executeScript(script);
					Thread.sleep(1000);
					WebElement	boldButton = driver.findElement(By.xpath("//dnvgl-rich-text-widget//a[@title='Bold']"));
					boldButton.click();
					Thread.sleep(1000);

				} catch (Exception e) {
					e.printStackTrace();
					Thread.sleep(1000);
					script = "document.getElementsByClassName('cke_wysiwyg_frame cke_reset')[0].contentWindow.document.body.innerHTML = '<p>"+content+"<p>'";
					Object executeScript = javaScriptHelper.executeScript(script);
					Thread.sleep(1000);
					WebElement	boldButton = driver.findElement(By.xpath("//dnvgl-rich-text-widget//a[@title='Bold']"));
					boldButton.click();
					Thread.sleep(1000);
				}		
				createComment = driver.findElement(By.xpath("//aw-include[@name='AP4_CreateComment']//button[@action='createCommentData']"));
				waitHelper.waitForElement(createComment,Config.getInstance().getExplicitWait());
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void clickOnSaveButton(){
		logger.info("clicking on create comment button...");
		createComment.click();		
	}

	public void createComment(String commentTitle, String page, String section, String content){	
		try {
			enterCommentTitle(commentTitle);
			enterCommentPage(page);
			enterCommentSection(section);
			enterCommentContent(content);
			clickOnSaveButton();
			clearNotifications();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearNotifications() {
		boolean isAlertVisible = true;
		int counter = 11;
		
		try {
			waitHelper.waitForElementToPresence(By.xpath("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Comment created successfully.')]"), Config.getInstance().getExplicitWait());
		} catch (Exception e) {
			e.printStackTrace();
		}

		while(isAlertVisible || counter == 10) {
			List<WebElement> findElements = null;
			try {	
				
				findElements = driver.findElements(By.xpath("//ul[@id='noty_bottom_layout_container']//div[contains(@class,'noty_close')]"));

				for (WebElement webElement : findElements) {
					
					try {
						webElement.click();	
						webElement.click();	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(findElements == null || findElements.size() ==0)
				isAlertVisible = false;
			counter++;
		}
		
	}


}