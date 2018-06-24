package com.abhi.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.utility.StaleElementUtils;
/**
 * 
 * @author abhinandan
 * 
 */
public class ItemReportPage extends WebPage{

	private final Logger log = LoggerHelper.getLogger(ItemReportPage.class);

	
	@FindBy(xpath="//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']")
	WebElement generateReportButton;

	

	public ItemReportPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		try
		{
			waitHelper.waitForElement(driver, generateReportButton,Config.getInstance().getExplicitWait());
		}
		catch(StaleElementReferenceException e )
		{
			generateReportButton = driver.findElement(By.xpath("//aw-command//button[@id='Awp0InContextReports'][@title='Generate Report']"));
			
			StaleElementUtils.refreshElement(driver, generateReportButton);
		}
		waitHelper.waitForElement(driver, generateReportButton,Config.getInstance().getExplicitWait());
		
		}
		

	@DataProvider(name = "reportdetails")
	public Object[][] getReportDetails(){

		Object[][] reportDetails = new Object[1][3];
		reportDetails[0][0] = "Project Finance Structure Overview";
		reportDetails[0][1] = "Project Manager";
		reportDetails[0][2] = "[MYSELF]";

		return reportDetails;
	}

	private void findTcRAButtons()
	{
		WebElement generateNowButton = driver.findElement(By.xpath("//button[@id='runReportBtn']"));
		WebElement scheduleNowButton = driver.findElement(By.xpath("//button[@id='scheduleNowBtn']"));
		WebElement scheduleButton = driver.findElement(By.xpath("//button[@id='openScheduleUIBtn']"));
		
	}

	

	public String clickOnGenerateReport(){
		log.info("clickin on Generate Report icon");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		generateReportButton.click();
		
		
		//generateReportButton.click();
		//generateReportButton.click();
	
		
		WebElement reportFromListElement = driver.findElement(By.xpath("//form[@panel-id='Awp0InContextReportsList']//ul/li//h3[contains(text(),'Comment Status (Project)')]"));
		reportFromListElement.click();
		
		WebElement generateNowButton = driver.findElement(By.xpath("//button[@id='runReportBtn']"));
		
		waitHelper.waitForElement(driver, generateNowButton,Config.getInstance().getTcRALoginWait());
		generateNowButton.click();
		
		
		return verifyeQResult();
	}

	private String verifyeQResult() {
		String resultRows = "";
		try {
			
			WebElement eqFrame= driver.findElement(By.id("eq-frame"));
			driver.switchTo().frame(eqFrame);
			
			WebElement reportOption = driver.findElement(By.xpath("//div[@id='setting_icon' and @title='Report Options']"));
			
			waitHelper.waitForElement(driver, reportOption,Config.getInstance().getTcRALoginWait());
			
			WebElement rowNumberLabel = driver.findElement(By.xpath("//div[@class='js-top-row-section topRowNumber']"));
			waitHelper.waitForElement(driver, rowNumberLabel,Config.getInstance().getTcRALoginWait());
			
			resultRows  = rowNumberLabel.getText();
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().defaultContent();
		 return resultRows;
	}

	


	
}
