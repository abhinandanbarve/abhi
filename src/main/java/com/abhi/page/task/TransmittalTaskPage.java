package com.abhi.page.task;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

public class TransmittalTaskPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(TransmittalTaskPage.class);

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Action' ]")
	private WebElement actionSection;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Action' ]//textarea")
	private WebElement transmittalComment;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Action' ]//button[text()='Approve']")
	private WebElement approveButton;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Action' ]//button[text()='Reject']")
	private WebElement rejectButton;

	public TransmittalTaskPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( actionSection,Config.getInstance().getExplicitWait());
	}

	private void enterTransmittalComment(String transmittalComment){
		logger.info("entering password...."+transmittalComment);
		this.transmittalComment.sendKeys(transmittalComment);
	}

	private void clickOnApproveButton(){
		logger.info("clicking on approve button...");
		approveButton.click();		
	}

	private void clickOnRejectButton(){
		logger.info("clicking on reject button...");
		rejectButton.click();		
	}
	
	public void approveTransmittal(String transmittalComment){		
		enterTransmittalComment(transmittalComment);
		clickOnApproveButton();
	}
	
	public void rejectTransmittal(String transmittalComment){		
		enterTransmittalComment(transmittalComment);
		clickOnRejectButton();
	}

}
