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
public class HomePage {
	
	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	WaitHelper waitHelper;
	
	String Tshirts = "T-shirts";
	String Blouses = "Blouses";
	String CasualDresses = "Casual Dresses";

	
	@FindBy(xpath = "//*[@id=\"aw-state-userName\"]")	
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']")
	public WebElement dressesMenu;
	
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtsMenu;

	
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		TestBase testBase = new TestBase();
		waitHelper.waitForElement(driver, womenMenu,new Config(TestBase.OR).getExplicitWait());
	}
	
	public void mouseOver(String data){
		log.info("doing mouse over on :"+data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]"))).build().perform();
	}
	
	public ProductCategoryPage clickOnIntem(String data){
		log.info("clickin on :"+data);
		driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]")).click();
		return new ProductCategoryPage(driver);
	}
	
	public ProductCategoryPage clickOnMenu(WebElement element){
		log.info("clickin on : "+element.getText());
		element.click();
		return new ProductCategoryPage(driver);
	}

}
