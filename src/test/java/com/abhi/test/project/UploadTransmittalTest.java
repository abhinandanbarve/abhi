package com.abhi.test.project;

import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.panel.UploadTransmittalPanel;
import com.abhi.page.project.ProjectDetailsPage;
import com.abhi.page.project.ProjectOverviewPage;
import com.abhi.test.TestConstants;

public class UploadTransmittalTest extends TestBase{

	@Test
	public void uploadTransmittal() {

		String projectValue = TestConstants.PROJECT_NAME;
		String transmittalName = TestConstants.PROJECT_NAME;
		String transmittalNumber = TestConstants.PROJECT_TC_ID;
		String transmittalFile = System.getProperty("user.dir")+"/src/main/resources/transmittal/transmittal.zip";
				
		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectOverviewPage projectOverviewPage = searchResultPage.openProjectDetails(projectValue);		
		ProjectDetailsPage clickOnProjectDetailsTab = projectOverviewPage.getProjectDetailsPage();
		UploadTransmittalPanel uploadTransmittalPanel = clickOnProjectDetailsTab.loadToolBar().getUploadTransmittalPanel();
		uploadTransmittalPanel.uploadTransmittal(transmittalName, transmittalNumber, transmittalFile);
		new GlobalHomeIcon(driver).clickOnHomeButton();

	}
}