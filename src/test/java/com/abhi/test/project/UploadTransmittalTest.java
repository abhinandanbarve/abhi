package com.abhi.test.project;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.panel.UploadTransmittalPanel;
import com.abhi.page.project.ProjectDetailsPage;
import com.abhi.page.project.ProjectOverviewPage;

public class UploadTransmittalTest extends TestBase{

	public void uploadTransmittal(String projectValue, String transmittalName, String transmittalNumber, String transmittalFile) {

		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectOverviewPage projectOverviewPage = searchResultPage.openProjectDetails(projectValue);		
		ProjectDetailsPage clickOnProjectDetailsTab = projectOverviewPage.getProjectDetailsPage();
		UploadTransmittalPanel uploadTransmittalPanel = clickOnProjectDetailsTab.loadToolBar().getUploadTransmittalPanel();
		uploadTransmittalPanel.uploadTransmittal(transmittalName, transmittalNumber, transmittalFile);
		new GlobalHomeIcon(driver).clickOnHomeButton();

	}
}