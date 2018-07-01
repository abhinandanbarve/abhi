package com.abhi.tcrareport;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.documents.UploadDocumentPage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.project.ProjectDetailsPage;
import com.abhi.page.project.ProjectRightToolBarPage;

public class DocumentUploadTest extends TestBase{


	@Test
	public void documentUploadTest() {

		String projectValue = "abhi-top-1.0";
		String strFilePath = "C:\\Abhinandan\\BHLKCL.pdf";
		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectDetailsPage openProjectDetails = searchResultPage.openProjectDetails(projectValue);
		openProjectDetails.clickOnProjectDetaileTab();
		ProjectRightToolBarPage loadRightToolBarPage = openProjectDetails.loadRightToolBarPage();

		UploadDocumentPage addProjectDocumentPage = loadRightToolBarPage.selectAddDocumentCommand();
		addProjectDocumentPage.uploadDocument(strFilePath);
		
		
		Assert.assertTrue(true, "Resport is not executed. Please verify..");
		new GlobalHomeIcon(driver).clickOnHomeButton();

	}

}


