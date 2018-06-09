package com.abhi.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.helper.Wait.WaitHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;

/**
 * 
 * @author abhinandan
 * 
 */
public class HomePage extends WebPage{
	
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	
	String Tshirts = "T-shirts";
	String Blouses = "Blouses";
	String CasualDresses = "Casual Dresses";

	
	@FindBy(xpath = "//*[@id=\"aw-state-userName\"]")	
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']")
	public WebElement dressesMenu;
	
	
	//@FindBy(xpath="//div[@class='name nameOverride'][contains(text(),'Reports')]")
	@FindBy(xpath="//div[contains(text(),'Reports')]")
	public WebElement reportsTile;

	
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, reportsTile,new Config(TestBase.OR).getExplicitWait());
	}
	
	
	public SummaryReportsPage clickOnSummaryReportsTile(){
		log.info("clickin on Reports:");
		reportsTile.click();
		return new SummaryReportsPage(driver);
	}
	

}
