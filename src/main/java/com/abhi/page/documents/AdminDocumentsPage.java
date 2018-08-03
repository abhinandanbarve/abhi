package com.abhi.page.documents;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.GenericHelper;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

public class AdminDocumentsPage  extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(AdminDocumentsPage.class);


	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Documents']")
	private WebElement documentsSection;


	//$x("//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Documents']//aw-walker-element//aw-table//aw-table-cell//span[contains(text(),'MDR Document Revision')]/ancestor::div[@role ='row']//aw-table-command-cell//button[@title ='Open']")
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Documents']//aw-walker-element//aw-table//aw-table-cell//span[contains(text(),'MDR Document Revision')]/ancestor::div[@role ='row']//aw-table-command-cell//button[@title ='Open']")
	private WebElement mdrRevisionOpenIcon;

	//$x("//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Documents']//aw-walker-element//aw-table//aw-table-cell//span[contains(text(),'MDR Document Revision')]/ancestor::div[@role ='row']//aw-table-command-cell//button[@title ='Open']")
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Documents']//aw-walker-element//aw-table//aw-table-cell//span[contains(text(),'MDR Document Revision')]/ancestor::div[@role ='row']")
	private WebElement mdrRevision;


	public AdminDocumentsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( documentsSection,Config.getInstance().getExplicitWait());
	}

	private void clickOnOpenMDRIcon() {
		logger.info("clicking on clickOnOpenMDRIcon tab...");
		
		mdrRevision.click();
		waitHelper.waitForSeaconds(1);
		mdrRevisionOpenIcon.click();
	}

	public MDRDetailsPage openMDRDocumentRevision() {
		clickOnOpenMDRIcon();
		return new MDRDetailsPage(driver);
	}

}
