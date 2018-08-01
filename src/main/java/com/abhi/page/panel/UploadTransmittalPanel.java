package com.abhi.page.panel;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

public class UploadTransmittalPanel extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(UploadTransmittalPanel.class);

	//$x("//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//aw-widget//aw-property-label//div[contains(text(),'Name:')]/ancestor::aw-widget//aw-property-val//input[@type='text']")
	@FindBy(xpath="//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//aw-widget//aw-property-label//div[contains(text(),'Name:')]/ancestor::aw-widget//aw-property-val//input[@type='text']")
	private WebElement transmittalName;


	//$x("//div[@class='dnvgl-criteria-searchButton']//dnvgl-action-icon[@id='cmdSearch']")
	@FindBy(xpath="//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//aw-widget//aw-property-label//div[contains(text(),'Transmittal Number:')]/ancestor::aw-widget//aw-property-val//input[@type='text']")
	private WebElement transmittalNumber;

	//$x("//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//div[contains(text(),'Transmittal file:')]/ancestor::div[contains(@class,'aw-widgets-propertyContainer')]//form//input[@type='file']")
	@FindBy(xpath="//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//div[contains(text(),'Transmittal file:')]/ancestor::div[contains(@class,'aw-widgets-propertyContainer')]//form//input[@type='file']")
	private WebElement transmittalFile;


	//$x("//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//div[contains(text(),'Cover letter:')]/ancestor::div[contains(@class,'aw-widgets-propertyContainer')]//form//input[@type='file']")
	@FindBy(xpath="//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//div[contains(text(),'Cover letter:')]/ancestor::div[contains(@class,'aw-widgets-propertyContainer')]//form//input[@type='file']")
	private WebElement coverLetter;

	//$x("//div[@class='aw-layout-panelMain']//div[contains(@class,'aw-layout-panelFooter')]//button")
	@FindBy(xpath="//div[@class='aw-layout-panelMain']//div[contains(@class,'aw-layout-panelFooter')]//button")
	private WebElement oKButton;


	public UploadTransmittalPanel(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElementToClick(transmittalName,Config.getInstance().getExplicitWait());
		waitHelper.waitForElementToClick(transmittalNumber,Config.getInstance().getExplicitWait());
	}

	public UploadTransmittalPanel(WebDriver driver, int timeout) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElementToClick(transmittalName,timeout);
	}

	private void enterTransmittalName(String transmittalName){
		logger.info("entering transmittalName ...."+transmittalName);
		this.transmittalName.sendKeys(transmittalName);
	}

	private void enterTransmittalNumber(String transmittalNumber){
		logger.info("entering transmittalNumber...."+transmittalNumber);
		this.transmittalNumber.sendKeys(transmittalNumber);
	}

	private void setTransmittalFile(String transmittalFile){
		logger.info("entering transmittalNumber...."+transmittalNumber);
		this.transmittalFile.sendKeys(transmittalFile);
	}


	private void clickOnOKButton(){
		logger.info("clicking on Ok button...");
		oKButton.click();		
	}

	public void handleNotifications(){			
		boolean isAlertVisible = true;
		int counter = 0;

		try {
			waitHelper.waitForElementToPresence(By.xpath("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Transmittal Upload is completed.')]"), Config.getInstance().getTcRALoginWait());
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

	public void uploadTransmittal(String transmittalName, String transmittalNumber, String transmittalFile){
		enterTransmittalName(transmittalName);
		enterTransmittalNumber(transmittalNumber);
		setTransmittalFile(transmittalFile);
		clickOnOKButton();
		handleNotifications();
	}


}



/*
 * 
 * 
 * <ul id="noty_bottom_layout_container" class=""><li class="lightTheme noty_container_type_information" style=""><div class="noty_bar noty_type_information" id="noty_1370724776882556700"><div class="noty_message"><span class="noty_text">Documents have been created successfully. Initiating file upload. Please be patient, this may take few minutes depending upon your internet speed.</span><div class="noty_close" style="display: block;"><svg class="aw-base-icon" viewBox="0 0 24 24"><path class="aw-theme-iconOutline" fill="#464646" d="M23.6,6.4c-0.3-0.2-0.6-0.2-0.9-0.1C21.8,6.6,21,7.2,20.5,8h-6.2c-1.3-2.3-3.7-4-5.9-4C8.2,4,8,4.2,8,4.5V11 H0v1h8v6.5C8,18.8,8.2,19,8.5,19c2.1,0,4.6-1.7,5.9-4h6.2c0.5,0.8,1.2,1.4,2.1,1.7c0.3,0.1,0.7,0.1,0.9-0.1c0.3-0.2,0.4-0.5,0.4-0.8 V7.2C24,6.9,23.8,6.6,23.6,6.4z M23,15.8c-0.7-0.2-1.4-0.8-1.7-1.5C21.2,14.1,21,14,20.8,14h-6.8c-0.2,0-0.4,0.1-0.4,0.3 c-0.9,1.9-2.9,3.4-4.6,3.7V5c1.7,0.3,3.7,1.8,4.6,3.7C13.7,8.9,13.9,9,14.1,9h6.8c0.2,0,0.4-0.1,0.5-0.3c0.3-0.7,1-1.3,1.7-1.5V15.8 z"></path></svg></div></div></div></li><li class="lightTheme noty_container_type_information" style=""><div class="noty_bar noty_type_information" id="noty_72608885549715040"><div class="noty_message"><span class="noty_text">Transmittal Upload is completed.</span><div class="noty_close" style="display: block;"><svg class="aw-base-icon" viewBox="0 0 24 24"><path class="aw-theme-iconOutline" fill="#464646" d="M23.6,6.4c-0.3-0.2-0.6-0.2-0.9-0.1C21.8,6.6,21,7.2,20.5,8h-6.2c-1.3-2.3-3.7-4-5.9-4C8.2,4,8,4.2,8,4.5V11 H0v1h8v6.5C8,18.8,8.2,19,8.5,19c2.1,0,4.6-1.7,5.9-4h6.2c0.5,0.8,1.2,1.4,2.1,1.7c0.3,0.1,0.7,0.1,0.9-0.1c0.3-0.2,0.4-0.5,0.4-0.8 V7.2C24,6.9,23.8,6.6,23.6,6.4z M23,15.8c-0.7-0.2-1.4-0.8-1.7-1.5C21.2,14.1,21,14,20.8,14h-6.8c-0.2,0-0.4,0.1-0.4,0.3 c-0.9,1.9-2.9,3.4-4.6,3.7V5c1.7,0.3,3.7,1.8,4.6,3.7C13.7,8.9,13.9,9,14.1,9h6.8c0.2,0,0.4-0.1,0.5-0.3c0.3-0.7,1-1.3,1.7-1.5V15.8 z"></path></svg></div></div></div></li><li class="lightTheme noty_container_type_information" style=""><div class="noty_bar noty_type_information" id="noty_125427890792757020"><div class="noty_message"><span class="noty_text">Transmittal Upload is in progress and will be notified once completed.</span><div class="noty_close" style="display: block;"><svg class="aw-base-icon" viewBox="0 0 24 24"><path class="aw-theme-iconOutline" fill="#464646" d="M23.6,6.4c-0.3-0.2-0.6-0.2-0.9-0.1C21.8,6.6,21,7.2,20.5,8h-6.2c-1.3-2.3-3.7-4-5.9-4C8.2,4,8,4.2,8,4.5V11 H0v1h8v6.5C8,18.8,8.2,19,8.5,19c2.1,0,4.6-1.7,5.9-4h6.2c0.5,0.8,1.2,1.4,2.1,1.7c0.3,0.1,0.7,0.1,0.9-0.1c0.3-0.2,0.4-0.5,0.4-0.8 V7.2C24,6.9,23.8,6.6,23.6,6.4z M23,15.8c-0.7-0.2-1.4-0.8-1.7-1.5C21.2,14.1,21,14,20.8,14h-6.8c-0.2,0-0.4,0.1-0.4,0.3 c-0.9,1.9-2.9,3.4-4.6,3.7V5c1.7,0.3,3.7,1.8,4.6,3.7C13.7,8.9,13.9,9,14.1,9h6.8c0.2,0,0.4-0.1,0.5-0.3c0.3-0.7,1-1.3,1.7-1.5V15.8 z"></path></svg></div></div></div></li></ul>
 * */
