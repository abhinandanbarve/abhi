package com.abhi.page.project.create;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.dashboard.HomePage;

public class CreateProjectDetailsPage extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(CreateProjectDetailsPage.class);

	
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='ADMINISTRATION' ]")
	private WebElement administrationSection;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='SERVICE' ]")
	private WebElement serviceSection;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='FINANCIAL' ]")
	private WebElement financialSection;

	
	
	@FindBy(xpath="//aw-walker-element//aw-property-label//div[contains(text(),'Service Area')]/ancestor::aw-walker-element//aw-property-val//input[@type='text']")
	private WebElement serviceArea;

	@FindBy(xpath="//aw-walker-element//aw-property-label//div[contains(text(),'Service Line')]/ancestor::aw-walker-element//aw-property-val//input[@type='text']")
	private WebElement serviceLine;

	@FindBy(xpath="//aw-walker-element//aw-property-label//div[contains(text(),'Service Type')]/ancestor::aw-walker-element//aw-property-val//input[@type='text']")
	private WebElement serviceType;

	@FindBy(xpath="//aw-walker-element//aw-property-label//div[contains(text(),'CAPEX/OPEX')]/ancestor::aw-walker-element//aw-property-val//input[@type='text']")
	private WebElement serviceMode;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//button[@id='Awp0SaveEdits2' and @title='Save Edits']")
	private WebElement saveEdits;

	
	public CreateProjectDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( administrationSection,Config.getInstance().getPageLoadTimeOut());
		waitHelper.waitForElement( serviceSection,Config.getInstance().getPageLoadTimeOut());
		waitHelper.waitForElement( financialSection,Config.getInstance().getPageLoadTimeOut());
		
	}

	
	private void enterServiceArea(String serviceArea){
		logger.info("entering service area...."+serviceArea);
		this.serviceArea.clear();
		this.serviceArea.sendKeys(serviceArea);
	}

	private void enterServiceLine(String serviceLine){
		logger.info("entering service Line...."+serviceLine);
		this.serviceLine.clear();
		this.serviceLine.sendKeys(serviceLine);
	}
	
	private void enterServiceType(String serviceType){
		logger.info("entering service Type...."+serviceType);
		this.serviceType.clear();
		this.serviceType.sendKeys(serviceType);
	}
	
	private void enterServiceMode(String serviceMode){
		logger.info("entering service Mode...."+serviceMode);
		this.serviceMode.clear();
		this.serviceMode.sendKeys(serviceMode);
	}

	private void clickOnSaveEditsButton(){
		logger.info("clicking on save Edits button...");
		saveEdits.click();		
	}
	public boolean createProjectDetails(CreateProjectInput projectInput){		

		for(int i = 0; i< 8; i++)
		{
			try {
				if(projectInput.getServiceArea() != null && ! projectInput.getServiceArea().isEmpty())
					enterServiceArea(projectInput.getServiceArea());

				if(projectInput.getServiceLine() != null && ! projectInput.getServiceLine().isEmpty())
					enterServiceLine(projectInput.getServiceLine());

				if(projectInput.getServiceType() != null && ! projectInput.getServiceType().isEmpty())
					enterServiceType(projectInput.getServiceType());

				if(projectInput.getServiceMode() != null && ! projectInput.getServiceMode().isEmpty())
					enterServiceMode(projectInput.getServiceMode());

				
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		clickOnSaveEditsButton();
		return true;
	}
	
}
