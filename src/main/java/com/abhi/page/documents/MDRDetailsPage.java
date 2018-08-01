package com.abhi.page.documents;

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


	public MDRDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(fileSection,Config.getInstance().getExplicitWait());
	}

	private void selectMDRRevision() {

		logger.info("clicking on mdr Revision...");
		mdrRevision.click();
	}

	private void cutMDRRevision() {

		logger.info("clicking on cut MDRButton button...");
		cutMDRButton.click();
	}

	private void clickOnAddMDRRevisionIcon() {

		logger.info("clicking on add MDRButton button...");
		addMDRButton.click();
	}

	private void clickOnEditMDRRevisionIcon() {

		logger.info("clicking on edit MDRButton button...");
		editMDRButton.click();
	}

	public void removeExistingMDRTemplate() {

		selectMDRRevision();
		cutMDRRevision();
		clickOnAddMDRRevisionIcon();		
	}
	
	public void updateMDRTemplate(String mdrFile) {

		new AddMDRPanel(driver).uploadMDRFile(mdrFile);
		selectMDRRevision();
		clickOnEditMDRRevisionIcon();
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





