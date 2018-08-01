package com.abhi.tcrareport;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.panel.UploadDocumentPanel;
import com.abhi.page.project.ProjectDocumentsPage;
import com.abhi.page.project.ProjectOverviewPage;

public class DocumentUploadTest extends TestBase{


	@Test
	public void documentUploadTest() {

		String projectValue = "abhi-top-1.0";
		String strFilePath = "C:\\Abhinandan\\BHLKCL.pdf";
		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectOverviewPage openProjectDetails = searchResultPage.openProjectDetails(projectValue);
		ProjectDocumentsPage documentsPage = openProjectDetails.getDocumentsPage();
		UploadDocumentPanel addDocumentCommand = documentsPage.loadToolBar().getUploadDocumentPanel();		
		addDocumentCommand.uploadDocument(strFilePath);
		
		Assert.assertTrue(true, "Resport is not executed. Please verify..");
		new GlobalHomeIcon(driver).clickOnHomeButton();

	}

}


