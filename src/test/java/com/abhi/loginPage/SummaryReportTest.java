package com.abhi.loginPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.abhi.PageObject.GlobalHomeIcon;
import com.abhi.PageObject.GlobelSearchResultPage;
import com.abhi.PageObject.HomePage;
import com.abhi.PageObject.MultiProjectRightToolBarPage;
import com.abhi.PageObject.ProjectDetailsPage;
import com.abhi.PageObject.ProjectReportListPage;
import com.abhi.PageObject.ReportFilterPage;
import com.abhi.PageObject.ReportFilterPage.ReportType;
import com.abhi.PageObject.SummaryReportPage;
import com.abhi.PageObject.ProjectRightToolBarPage;
import com.abhi.PageObject.TcRAResultPage;
import com.abhi.PageObject.TilePage;
import com.abhi.testBase.TestBase;

public class SummaryReportTest extends TestBase{

	@DataProvider(name="reportListData")
	public Object[][] dataSource(){
		return getData("TcRAReportList.xlsx", "MultiProjectReports");
	}

	
	@Test(dataProvider="reportListData")
	public void summaryReportTest(String reportName, String filterKey,String filterValue, String expectedRows) {

		TilePage tilePage = new TilePage(driver);
		SummaryReportPage summaryReportPage = tilePage.selectReportTile();
		MultiProjectRightToolBarPage multiProjectRightToolBarPage = summaryReportPage.selectReport(reportName);
		ReportFilterPage reportFilterPage = multiProjectRightToolBarPage.selectReportCommand(ReportType.MULTIPROJECT);
		reportFilterPage.setFilterCriteria(filterKey, filterValue);
		TcRAResultPage selectGeneateNow = reportFilterPage.selectGeneateNow();
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
