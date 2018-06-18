package com.abhi.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

public class ProjectDetailsPage extends WebPage{

	@FindBy(xpath="//label[text()='PROJECT INFORMATION']")
	private WebElement projectInformationLabel;


	public ProjectDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, projectInformationLabel,new Config(TestBase.OR).getExplicitWait());
	}

	public ProjectRightToolBarPage loadRightToolBarPage() {
		return new ProjectRightToolBarPage(driver);
	}
	
}
