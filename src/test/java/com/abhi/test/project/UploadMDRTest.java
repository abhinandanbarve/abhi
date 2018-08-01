package com.abhi.test.project;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.documents.AdminDocumentsPage;
import com.abhi.page.documents.MDRDetailsPage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.project.ProjectDocumentsPage;
import com.abhi.page.project.ProjectOverviewPage;

public class UploadMDRTest extends TestBase{

	public void uploadMDR(String projectValue, String mdrFile) {

		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectOverviewPage projectOverviewPage = searchResultPage.openProjectDetails(projectValue);		
		ProjectDocumentsPage documentsPage = projectOverviewPage.getDocumentsPage();
		AdminDocumentsPage adminDocumentsPage = documentsPage.openAdminFolder();
		MDRDetailsPage openMDRDocumentRevision = adminDocumentsPage.openMDRDocumentRevision();
		openMDRDocumentRevision.removeExistingMDRTemplate();
		openMDRDocumentRevision.updateMDRTemplate(mdrFile);
		new GlobalHomeIcon(driver).clickOnHomeButton();

	}
}