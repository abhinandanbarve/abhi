package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

public class GlobelSearchResultPage extends WebPage{

	
	private final Logger logger = LoggerHelper.getLogger(GlobelSearchResultPage.class);

	@FindBy(xpath="//div[@ng-controller='awTableContainerController']//table/tbody/tr/td//a[@title='Open']")
	private WebElement selectProject;
	

	public GlobelSearchResultPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, selectProject,new Config(TestBase.OR).getExplicitWait());
	}
	
	private void openProjectDetails(){
		logger.info("opening Project Details...");
		this.selectProject.click();
	}

	public ProjectDetailsPage openProjectDetails(String searchValue){		
		openProjectDetails();
		return new ProjectDetailsPage(driver);
	}

	
}
