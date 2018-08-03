package com.abhi.page.documents;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.GenericHelper;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.panel.AddMDRPanel;

public class MDRDetailsPage extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(MDRDetailsPage.class);

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Files']")
	private WebElement fileSection;

	//$x("//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Files']//aw-walker-element//aw-table//aw-table-cell//span[contains(text(),'MS ExcelX')]")
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Files']//aw-walker-element//aw-table//aw-table-cell//span[contains(text(),'MS ExcelX')]")
	private WebElement mdrRevision;

	//$x("//aw-command//button[@id='Awp0Cut']")
	@FindBy(xpath="//aw-command//button[@id='Awp0Cut']")
	private WebElement cutMDRButton;

	//$x("//aw-command//button[@id='Awp0ShowAddObject']")
	@FindBy(xpath="//aw-command//button[@id='Awp0ShowAddObject']")
	private WebElement addMDRButton;

	//$x("//aw-command//button[@id='AP4_EditMDR']")
	@FindBy(xpath="//aw-command//button[@id='AP4_EditMDR']")
	private WebElement editMDRButton;

	@FindBy(xpath="//aw-command//button[@id='AP4_UploadMDR']")
	private WebElement uploadMDRButton;
	
	//$x("//div[@class='noty_buttons']//button[text()='Yes']")
	@FindBy(xpath="//div[@class='noty_buttons']//button[text()='Yes']")
	private WebElement importMDRConfirmationButton;
	
	
	public MDRDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(fileSection,Config.getInstance().getExplicitWait());
	}

	private void selectMDRRevision() {
		
		logger.info("clicking on mdr Revision...");
		waitHelper.waitForElementToClick(mdrRevision, Config.getInstance().getExplicitWait());
		mdrRevision.click();
	}

	private void cutMDRRevision() {

		logger.info("clicking on cut MDRButton button...");
		waitHelper.waitForElementToClick(cutMDRButton, Config.getInstance().getExplicitWait());
		cutMDRButton.click();
	}

	private void clickOnAddMDRRevisionIcon() {

		logger.info("clicking on add MDRButton button...");
		waitHelper.waitForElementToClick(addMDRButton, Config.getInstance().getExplicitWait());
		addMDRButton.click();
	}
	
	private void clickOnUploadMDRRevisionIcon() {

		logger.info("clicking on upload MDRButton button...");
		waitHelper.waitForElementToClick(uploadMDRButton, Config.getInstance().getExplicitWait());
		uploadMDRButton.click();
	}

	private void clickOnEditMDRRevisionIcon() {

		logger.info("clicking on edit MDRButton button...");
		waitHelper.waitForElementToClick(editMDRButton, Config.getInstance().getExplicitWait());
		editMDRButton.click();
	}
	
	private void clickOnImportMDRConfirmationButton() {

		logger.info("clicking on edit MDRButton button...");
		waitHelper.waitForElementToClick(importMDRConfirmationButton, Config.getInstance().getExplicitWait());
		importMDRConfirmationButton.click();
	}
	
	public void removeExistingMDRTemplate() {
			
		if(new GenericHelper().isDisplayed(mdrRevision))
		{
			selectMDRRevision();
			waitHelper.waitForSeaconds(1);
			cutMDRRevision();
			waitHelper.waitForSeaconds(1);
			handleNotifications();
			waitHelper.waitForSeaconds(1);
		}
		
		clickOnAddMDRRevisionIcon();	
	}

	public void updateMDRTemplate(String mdrFile) {
		waitHelper.waitForSeaconds(1);
		new AddMDRPanel(driver).uploadMDRFile(mdrFile);
		waitHelper.waitForSeaconds(1);
		selectMDRRevision();
		waitHelper.waitForSeaconds(1);
		clickOnUploadMDRRevisionIcon();
		waitHelper.waitForSeaconds(1);
		PageFactory.initElements(driver, this);
		clickOnImportMDRConfirmationButton();
		waitHelper.waitForSeaconds(1);
		handleNotifications();
		waitHelper.waitForSeaconds(1);
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





