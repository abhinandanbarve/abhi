package com.abhi.test.project;

import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.documents.AdminDocumentsPage;
import com.abhi.page.documents.MDRDetailsPage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.project.ProjectDocumentsPage;
import com.abhi.page.project.ProjectOverviewPage;
import com.abhi.test.TestConstants;

public class UploadMDRTest extends TestBase{

	@Test
	public void uploadMDR() {
		
		String projectValue = TestConstants.PROJECT_NAME;
		String mdrFile = System.getProperty("user.dir")+"/src/main/resources/mdr/MDRTemplate.xlsm";
				
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