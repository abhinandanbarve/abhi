package com.abhi.loginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abhi.PageObject.GlobelSearchResultPage;
import com.abhi.PageObject.HomePage;
import com.abhi.PageObject.ProjectDetailsPage;
import com.abhi.PageObject.ProjectReportListPage;
import com.abhi.PageObject.ReportFilterPage;
import com.abhi.PageObject.ReportFilterPage.ReportType;
import com.abhi.PageObject.RightToolBarPage;
import com.abhi.PageObject.TcRAResultPage;
import com.abhi.testBase.TestBase;

public class ItemReportTest extends TestBase{
//	
//  @Test
//  public void summaryReportsListTest() {
//	  
//	  HomePage homePage = new HomePage(driver);
//	  ItemReportPage itemReportPage = homePage.clickOnGlobalSearchLink();
//	  String result = itemReportPage.clickOnGenerateReport();
//	  
//	  Assert.assertTrue(result != null && result.contains("Row :"), "Resport is not executed. Please verify..");
//	  
//  }
  
  
  @Test
  public void executeItemReportTest() {
	  
	  String report = "Comment Status (Project)";
	  String project = "316837";
	  
	  HomePage homePage = new HomePage(driver);
	  GlobelSearchResultPage searchResultPage = homePage.searchProject(project);
	  ProjectDetailsPage openProjectDetails = searchResultPage.openProjectDetails(project);
	  RightToolBarPage loadRightToolBarPage = openProjectDetails.loadRightToolBarPage();
	  ProjectReportListPage projectReportListPage = (ProjectReportListPage)loadRightToolBarPage.selectReportCommand(ReportType.PROJECT);
	  ReportFilterPage selectProjectReport = projectReportListPage.selectProjectReport(report);
	  TcRAResultPage selectGeneateNow = selectProjectReport.selectGeneateNow();
	  int rowCount = selectGeneateNow.renderReportAndGetRowCount();
	  Assert.assertTrue(rowCount > 0, "Resport is not executed. Please verify..");
	  
  }
}
