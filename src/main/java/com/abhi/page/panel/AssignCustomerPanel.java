package com.abhi.page.panel;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

public class AssignCustomerPanel extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(AssignCustomerPanel.class);

	@FindBy(xpath="//div[@class='aw-layout-panelMain']//aw-tab//a[text()='Favorites']")
	private WebElement favoritesPanelTab;

	@FindBy(xpath="//div[@class='aw-layout-panelMain']//aw-tab//a[text()='O&G']")
	private WebElement oAndGPanelTab;

	@FindBy(xpath="//div[@class='aw-layout-panelMain']//aw-tab//a[text()='DNV GL']")
	private WebElement dnvglPanelTab;


	//$x("//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//aw-widget//aw-property-label//div[contains(text(),'Account Name:')]/ancestor::aw-widget//aw-property-val//input[@type='text']")
	@FindBy(xpath="//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//aw-widget//aw-property-label//div[contains(text(),'Account Name:')]/ancestor::aw-widget//aw-property-val//input[@type='text']")
	private WebElement accountNameText;

	
	//$x("//div[@class='dnvgl-criteria-searchButton']//dnvgl-action-icon[@id='cmdSearch']")
	@FindBy(xpath="//div[@class='dnvgl-criteria-searchButton']//dnvgl-action-icon[@id='cmdSearch']")
	private WebElement searchCustomerButton;
	
	
	private WebElement resultElement;
	
	public AssignCustomerPanel(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElementToClick(favoritesPanelTab,Config.getInstance().getExplicitWait());
		waitHelper.waitForElementToClick(oAndGPanelTab,Config.getInstance().getExplicitWait());
		waitHelper.waitForElementToClick(dnvglPanelTab,Config.getInstance().getExplicitWait());
	}

	private void clickOnDnvglPanelTab(){
		logger.info("clicking on DnvglPanelTab...");
		dnvglPanelTab.click();		
	}

	private void selectAndAssignCustomer(String customerName) {
		
		//$x("//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//aw-list//ul//li//label[@title='Nautilus Engineering & Construction Co.']")
		String resultElementPath = "//div[@class='aw-layout-panelMain']//form[@name='awPanelBody']//aw-list//ul//li//label[@title='"+customerName+"']"; 
		waitHelper.waitForElementToPresence(By.xpath("\""+resultElementPath+"\""), Config.getInstance().getExplicitWait());
		resultElement = driver.findElement(By.xpath("\""+resultElementPath+"\""));
		resultElement.click();
	}
	
	private void clickOnSearchCustomerIcon(){
		logger.info("clicking on create button...");
		searchCustomerButton.click();		
	}
	
		
	private void enterCustomerName(String customerName){
		logger.info("entering customer Name...."+customerName);
		this.accountNameText.sendKeys(customerName);
	}
	
	
	public void assignCustomer(String customerName){
		
		clickOnDnvglPanelTab();
		enterCustomerName(customerName);
		clickOnSearchCustomerIcon();
		selectAndAssignCustomer(customerName);
	}

	
}


