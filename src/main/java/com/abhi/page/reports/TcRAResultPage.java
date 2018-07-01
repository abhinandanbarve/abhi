package com.abhi.page.reports;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;
import com.abhi.page.login.LoginPage;

public class TcRAResultPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath = "//*[@id='eq-frame']")
	private WebElement eQFrame;
	
	
	@FindBy(xpath="//div[@id='setting_icon' and @title='Report Options']")
	private WebElement exportOptions;

	@FindBy(xpath="//div[@class='js-top-row-section topRowNumber']")
	private WebElement totalRowLabel;


	public TcRAResultPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(eQFrame,Config.getInstance().getExplicitWait());
	}


	private void renderReport(){
		logger.info("rendering report ....");
		driver.switchTo().frame(eQFrame);
		waitHelper.waitForElement(totalRowLabel,Config.getInstance().getTcRALoginWait());
	}

	private int getRowCount() {
		int rowCount = -1;
		logger.info("checking row count ....");
		String totalRow = totalRowLabel.getText();
		if(totalRow != null && totalRow.toLowerCase().contains("row") && totalRow.toLowerCase().contains("of")) {
			String totalRowCount = totalRow.substring(totalRow.indexOf("of")+2, totalRow.length());
			logger.info("checking row counttotalRowCount ...."+totalRowCount);
			rowCount = Integer.parseInt(totalRowCount.trim());
		}
		return rowCount;
	}
	
	public int renderReportAndGetRowCount(){
		int rowCount = -1;

		try {

			logger.info("rendering report....");
			renderReport();
			rowCount = getRowCount();
		}
		finally {
			switchToParentWindow();
		}
		return rowCount;
	}

	public void switchToParentWindow(){
		logger.info("rendering report ....");
		driver.switchTo().defaultContent();
	}
	
}
