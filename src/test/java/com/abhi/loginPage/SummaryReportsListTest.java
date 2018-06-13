package com.abhi.loginPage;

import org.testng.annotations.Test;

import com.abhi.PageObject.HomePage;
import com.abhi.PageObject.SummaryReportsPage;
import com.abhi.testBase.TestBase;

public class SummaryReportsListTest extends TestBase{
	
  @Test
  public void summaryReportsListTest() {
	  
	  HomePage homePage = new HomePage(driver);
	  SummaryReportsPage clickOnSummaryReportsTile = homePage.clickOnSummaryReportsTile();
	  clickOnSummaryReportsTile.clickOnGenerateReport();
	  
	  System.out.println(clickOnSummaryReportsTile.Yellow);
  }
}
