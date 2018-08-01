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

public class CreateProjectPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(CreateProjectPage.class);

	@FindBy(xpath="//div[@class='aw-layout-workarea']//input[@type='text']")
	private WebElement projectName;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//button[text()='Create']")
	private WebElement createProjectButton;


	public CreateProjectPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( createProjectButton,Config.getInstance().getExplicitWait());
	}


	private void enterProjectName(String projectName){
		logger.info("entering project name ...."+projectName);
		this.projectName.sendKeys(projectName);
	}

	
	private void clickOnProjectButton(){
		logger.info("clicking on submit button...");
		createProjectButton.click();		
	}

	public CreateProjectDetailsPage createProject(String projectName){		
		enterProjectName(projectName);
		clickOnProjectButton();
		return new CreateProjectDetailsPage(driver);
	}
}