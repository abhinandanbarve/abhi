package com.abhi.page.globalsearch;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

public class GlobelSearchPage extends WebPage{

	private final Logger logger = LoggerHelper.getLogger(GlobelSearchPage.class);

	@FindBy(xpath="//aw-search-global//aw-link-with-popup-menu//a[contains(text(),'Category')]")
	private WebElement globalSearchLink;

	@FindBy(xpath="//aw-search-global//aw-link-with-popup-menu//ul/li[contains(text(),'Projects/Opportunities')]")
	private WebElement projectMenuOnGlobalSearch;
	
	@FindBy(xpath="//aw-search-global//aw-link-with-popup-menu//ul/li[contains(text(),'Documents')]")
	private WebElement documentsMenuOnGlobalSearch;

	@FindBy(xpath="//aw-search-global//aw-search-box//input[@type='text']")
	private WebElement globalSearchText;

	@FindBy(xpath="//aw-search-global//aw-search-box//aw-icon")
	private WebElement globalSearchButton;


	public GlobelSearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement( globalSearchButton,Config.getInstance().getExplicitWait());
		logger.debug("Loading GlobelSearchPage done ...");
	}

	private void clickOnGlobalSearchLink(){
		logger.info("clicking on Global Search Link...");
		this.globalSearchLink.click();
	}

	private void selectProjectOnSearchMenu(){
		logger.info("clicking on Project On Search Menu...");
		this.projectMenuOnGlobalSearch.click();
	}
	
	private void selectDocumentOnSearchMenu(){
		logger.info("clicking on Document On Search Menu...");
		this.documentsMenuOnGlobalSearch.click();
	}

	private void enterSearchCriteria(String searchValue){
		logger.info("entering Search Criteria....Search value is: "+searchValue);
		this.globalSearchText.clear();
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

	public GlobelSearchResultPage searchDocuments(String document, int timeout) {
		
		clickOnGlobalSearchLink();
		selectDocumentOnSearchMenu();
		enterSearchCriteria(document);
		clickOnSearchButton();			
		return new GlobelSearchResultPage(driver,timeout);
	}

}
