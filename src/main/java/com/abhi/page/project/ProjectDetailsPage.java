package com.abhi.page.project;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

import toolbar.ProjectDetailsToolBar;

public class ProjectDetailsPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(ProjectDetailsPage.class);

	
	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='ADMINISTRATION' ]")
	private WebElement administrationSection;

	@FindBy(xpath="//div[@class='aw-layout-workarea']//div[contains(@class,'aw-layout-panelSection') and @caption='DETAILS']")
	private WebElement detailsSection;

	public ProjectDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( administrationSection,Config.getInstance().getExplicitWait());
		waitHelper.waitForElement( detailsSection,Config.getInstance().getExplicitWait());
	}

	public ProjectDetailsToolBar loadToolBar() {
		return new ProjectDetailsToolBar(driver);
	}	
	
}