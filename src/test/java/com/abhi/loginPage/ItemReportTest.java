package com.abhi.loginPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.abhi.PageObject.GlobalHomeIcon;
import com.abhi.PageObject.GlobelSearchResultPage;
import com.abhi.PageObject.HomePage;
import com.abhi.PageObject.ProjectDetailsPage;
import com.abhi.PageObject.ProjectReportListPage;
import com.abhi.PageObject.ProjectRightToolBarPage;
import com.abhi.PageObject.ReportFilterPage;
import com.abhi.PageObject.TcRAResultPage;
import com.abhi.testBase.TestBase;

public class ItemReportTest extends TestBase{


	@DataProvider(name="reportListData")
	public Object[][] dataSource(){
		return getData("TcRAReportList.xlsx", "ProjectReports");
	}


	@Test(dataProvider="reportListData")
	public void executeItemReportTest(String reportName, String projectValue, String expectedRows) {

		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectDetailsPage openProjectDetails = searchResultPage.openProjectDetails(projectValue);
		ProjectRightToolBarPage loadRightToolBarPage = openProjectDetails.loadRightToolBarPage();
		ProjectReportListPage projectReportListPage = loadRightToolBarPage.selectReportCommand();
		ReportFilterPage selectProjectReport = projectReportListPage.selectProjectReport(reportName);
		TcRAResultPage selectGeneateNow = selectProjectReport.selectGeneateNow();
		int rowCount = selectGeneateNow.renderReportAndGetRowCount();
		int minExpectedRowCount = 1;

		try {
			minExpectedRowCount = Integer.parseInt(expectedRows);
		} catch (Exception e) {
		}

		Assert.assertTrue(rowCount >= minExpectedRowCount, "Resport is not executed. Please verify..");

		new GlobalHomeIcon(driver).clickOnHomeButton();
		
	}
}