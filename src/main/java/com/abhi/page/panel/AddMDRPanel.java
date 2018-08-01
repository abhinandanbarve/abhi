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

public class AddMDRPanel extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(AddMDRPanel.class);

	@FindBy(xpath="//div[@class='aw-layout-panelMain']//aw-tab//a[text()='New']")
	private WebElement newPanelTab;

	@FindBy(xpath="//div[@class='aw-layout-panelMain']//aw-tab//a[text()='Palette']")
	private WebElement palettePanelTab;

	@FindBy(xpath="//div[@class='aw-layout-panelMain']//aw-tab//a[text()='Search']")
	private WebElement searchPanelTab;


	//$x("//form[@id='fileUploadForm']//input[@name='fmsFile']")
	@FindBy(xpath="//form[@id='fileUploadForm']//input[@name='fmsFile']")
	private WebElement mdrFile;


	//$x("//div[@class='aw-layout-panelMain']//div[contains(@class,'aw-layout-panelFooter')]//button")
	@FindBy(xpath="//div[@class='aw-layout-panelMain']//div[contains(@class,'aw-layout-panelFooter')]//button[not(contains(@class,'hidden'))]")
	private WebElement addButton;


	private WebElement resultElement;

	public AddMDRPanel(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElementToClick(newPanelTab,Config.getInstance().getExplicitWait());
		waitHelper.waitForElementToClick(palettePanelTab,Config.getInstance().getExplicitWait());
		waitHelper.waitForElementToClick(searchPanelTab,Config.getInstance().getExplicitWait());
	}

	public AddMDRPanel(WebDriver driver, int timeout) {
		super(driver);
		PageFactory.initElements(driver, this);	
		waitHelper.waitForElementToClick(searchPanelTab,timeout);
	}

	private void setMDRFile(String mdrFile){
		logger.info("entering mdr File...."+mdrFile);
		this.mdrFile.sendKeys(mdrFile);
	}


	private void clickOnAddButton(){
		logger.info("clicking on add button...");		
		addButton.click();		
	}

	

	public void uploadMDRFile(String mdrFile){
		setMDRFile(mdrFile);
		clickOnAddButton();
		handleNotifications();
	}

	public void handleNotifications(){			
		boolean isAlertVisible = true;
		int counter = 0;

		try {
			waitHelper.waitForElementToPresence(By.xpath("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text']"), Config.getInstance().getTcRALoginWait());
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


