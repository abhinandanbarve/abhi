package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

public class GlobelSearchPage extends WebPage{

	
	private final Logger logger = LoggerHelper.getLogger(HomePage.class);

	@FindBy(xpath="//aw-search-global//aw-link-with-popup-menu//a[contains(text(),'Category:')]")
	public WebElement globalSearchLink;

	@FindBy(xpath="//aw-search-global//aw-link-with-popup-menu//ul/li[contains(text(),'Projects/Opportunities')]")
	public WebElement projectMenuOnGlobalSearch;

	@FindBy(xpath="//aw-search-global//aw-search-box//input[@type='text']")
	public WebElement globalSearchText;

	@FindBy(xpath="//aw-search-global//aw-search-box//aw-icon")
	public WebElement globalSearchButton;


	public GlobelSearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, globalSearchButton,new Config(TestBase.OR).getExplicitWait());
	}

	public void clickOnGlobalSearchLink(){
		logger.info("clicking on Global Search Link...");
		this.globalSearchLink.click();
	}

	public void selectProjectOnSearchMenu(){
		logger.info("clicking on Global Search Link...");
		this.projectMenuOnGlobalSearch.click();
	}

	public void enterSearchCriteria(String searchValue){
		logger.info("entering password...."+searchValue);
		this.globalSearchText.sendKeys(searchValue);
	}

	public void clickOnSearchButton(){
		logger.info("clicking on search button...");
		this.globalSearchButton.click();
	}
	
	public GlobelSearchResultPage searchProject(String searchValue){		
		clickOnGlobalSearchLink();
		selectProjectOnSearchMenu();
		enterSearchCriteria(searchValue);
		clickOnSearchButton();
		return new GlobelSearchResultPage(driver);
	}

}
