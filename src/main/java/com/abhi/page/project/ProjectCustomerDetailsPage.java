package com.abhi.page.project;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.panel.AssignCustomerPanel;

public class ProjectCustomerDetailsPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(ProjectCustomerDetailsPage.class);

	@FindBy(xpath="//button[@id ='AP4_CustomerSearch']")
	private WebElement customerSearchButton;
	
	

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Customer' ]")
	private WebElement customerSection;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Frame Agreement']")
	private WebElement frameAgreementSection;

	
	

	public ProjectCustomerDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( customerSection,Config.getInstance().getExplicitWait());
		waitHelper.waitForElement( frameAgreementSection,Config.getInstance().getExplicitWait());
	}

	private void clickOnSearchCustomerIcon(){
		logger.info("clicking on Search Customer icon...");
		customerSearchButton.click();		
	}

	public AssignCustomerPanel getAssignCustomerPanel(){		
		clickOnSearchCustomerIcon();
		return new AssignCustomerPanel(driver);
	}
	
	public boolean verifyCustomerAssignment(String customerName){		
		
		//$x("//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Customer']//aw-table-command-cell//span[text()='Nautilus Engineering & Construction Co.']")
		String resultElementPath = "//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='Customer']//aw-table-command-cell//span[text()='"+customerName+"']"; 
		waitHelper.waitForElementToPresence(By.xpath("\""+resultElementPath+"\""), Config.getInstance().getExplicitWait());
		WebElement resultElement = driver.findElement(By.xpath("\""+resultElementPath+"\""));
		
		if(resultElement != null && resultElement.getText().equals(customerName))
			return true;
		return false;
		
	}
}