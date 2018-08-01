package com.abhi.test.reports;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.TilePage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.reports.MultiProjectRightToolBarPage;
import com.abhi.page.reports.ReportFilterPage;
import com.abhi.page.reports.SummaryReportPage;
import com.abhi.page.reports.TcRAResultPage;
import com.abhi.page.reports.ReportFilterPage.ReportType;

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
