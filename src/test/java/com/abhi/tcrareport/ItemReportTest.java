package com.abhi.tcrareport;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.project.ProjectOverviewPage;
import com.abhi.page.project.ProjectRightToolBarPage;
import com.abhi.page.reports.TcRAReportListPanel;
import com.abhi.page.reports.ReportFilterPage;
import com.abhi.page.reports.TcRAResultPage;

public class ItemReportTest extends TestBase{


	@DataProvider(name="reportListData")
	public Object[][] dataSource(){
		return getData("TcRAReportList.xlsx", "ProjectReports");
	}


	@Test(dataProvider="reportListData")
	public void executeItemReportTest(String reportName, String projectValue, String expectedRows) {

		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectOverviewPage openProjectDetails = searchResultPage.openProjectDetails(projectValue);
		ProjectRightToolBarPage loadRightToolBarPage = openProjectDetails.loadRightToolBarPage();
		TcRAReportListPanel projectReportListPage = loadRightToolBarPage.generateReportCommand();
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
