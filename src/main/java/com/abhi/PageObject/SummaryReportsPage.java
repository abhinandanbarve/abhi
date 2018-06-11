package com.abhi.PageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import com.abhi.helper.DropDown.DropDownHelper;
import com.abhi.helper.Javascript.JavaScriptHelper;
import com.abhi.helper.Logger.LoggerHelper;
import com.abhi.helper.assertionHelper.VerificationHelper;
import com.abhi.testBase.Config;
import com.abhi.testBase.TestBase;
/**
 * 
 * @author abhinandan
 * 
 */
public class SummaryReportsPage extends WebPage{

	private final Logger log = LoggerHelper.getLogger(SummaryReportsPage.class);
	
	public String Black = "Black";
	public String Orange = "Orange";
	public String Yellow = "Yellow";
	
	@FindBy(xpath="//span[@class='aw-layout-locationTitle ng-binding'][contains(text(),'Reports')]")
	WebElement reportTitle;
	
	@FindBy(xpath="//h3[@id='CellTitle'][@class='aw-widgets-cellListCellTitle ng-binding'] [contains(text(),'Comment Status')]")
	WebElement reportListItem;
	
	@FindBy(xpath="//h3[@id='CellTitle'][@class='aw-widgets-cellListCellTitle ng-binding']")
	List<WebElement> allReportItems;
	
	@FindBy(xpath="//button[@id='cmdFilterPanelDui'][@title='Generate Report']")
	WebElement generateReportButton;
	
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	WebElement proceedToCheckOut;
	
	@FindBy(xpath="//*[@id='center_column']/ul/li")
	List<WebElement> totalProducts;
	
	@FindBy(xpath="//*[@id='selectProductSort']")
	public WebElement sortBy;
	
	@FindBy(xpath="//*[@id='center_column']/ul/li/div/div[2]/div/span[1]")
	List<WebElement> allpriceElements;
	
	
	public SummaryReportsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, allReportItems.get(0),new Config(TestBase.OR).getTcRALoginWait());
	}
	
	public int getTotalReports(){
		return allReportItems.size();
	}
	
	public List<WebElement> getAllReportNames(){
		return allReportItems;
	}
	
	@DataProvider(name = "reportdetails")
	public Object[][] getReportDetails(){
		
		Object[][] reportDetails = new Object[1][3];
		reportDetails[0][0] = "Project Finance Structure Overview";
		reportDetails[0][1] = "Project Manager";
		reportDetails[0][2] = "[MYSELF]";
		
		return reportDetails;
	}
	
	public void clickOnReportName(String reportName){
		
		WebElement reportWebElement = getWebElementUsingReportName(reportName);
		log.info("clickin on Generate Report icon");
		reportWebElement.click();
	}
	
	private WebElement getWebElementUsingReportName(String reportName) {
		for (WebElement reportItem : allReportItems) {
			if(reportItem.getText().toLowerCase().equals(reportName.toLowerCase()))
				return reportItem;
		}
		log.error(reportName + " :: report not found in the report list. Please verify configuration file.");
		return null;
	}

	public void clickOnGenerateReport(){
		log.info("clickin on Generate Report icon");
		generateReportButton.click();
	}
	
	
	public boolean verifyPoductAddedSuccesfully(){
		return VerificationHelper.verifyElementPresent(reportListItem);
	}
	
	
	
	public void mouseOverOnProduct(int number){
		String fPart = "//*[@id='center_column']/ul/li[";
		String sPart = "]/div/div[2]/h5/a";
		Actions action = new Actions(driver);
		log.info("doing mouse over on: "+number+"..product");
		action.moveToElement(driver.findElement(By.xpath(fPart+number+sPart))).build().perform();
	}
	
	public void clickOnAddToCart(){
		log.info("clickin on add to cart");
		generateReportButton.click();
	}
	
	
	
	public void clickOnProceedTocheckOut(){
		log.info("clickin on :"+proceedToCheckOut.getText());
		proceedToCheckOut.click();
	}
	
	public void selectColor(String data){
		new JavaScriptHelper(driver).scrollIntoView(driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]")));
		driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]")).click();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void selectSmallSize() {
		log.info("selecting small size..");
		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).click();
	}

	public void selectMediumSize() {
		log.info("selecting Medium size..");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).click();
				log.info("checkbox is checked..");
			}
		} catch (Exception e) {
			log.info("checkbox was already checked..");
		}
	}

	public void selectLSize() {
		log.info("selecting Large size..");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).click();
				log.info("checkbox is checked..");
			}
		} catch (Exception e) {
			log.info("checkbox was already checked..");
		}
	}
	
	public void selectFirstProduct() {
		Actions obj = new Actions(driver);
		log.info("performning mouse over on first product of page..");
		obj.moveToElement(driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).get(0)).build().perform();
		log.info("clicking on add to basket..");
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
	}
	
	
	
	public void selectSortByFilter(String dataToSelect){
		DropDownHelper dropdown = new DropDownHelper(driver);
		dropdown.SelectUsingVisibleText(sortBy, dataToSelect);
	}
}
