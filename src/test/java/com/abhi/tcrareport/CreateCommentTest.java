package com.abhi.tcrareport;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.documents.DocumentDetailsPage;
import com.abhi.page.documents.panel.CreateCommentPanel;
import com.abhi.page.documents.toolbar.DocumentDetailsToolBar;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.panel.UploadDocumentPanel;
import com.abhi.page.project.ProjectDocumentsPage;
import com.abhi.page.project.ProjectOverviewPage;
import com.abhi.page.project.ProjectRightToolBarPage;
import com.abhi.page.toolbar.ProjectDocumentsToolBar;

public class CreateCommentTest extends TestBase{

	private final Logger logger = LoggerHelper.getLogger(CreateCommentTest.class);


	@Test
	public void documentUploadTest() {

		String projectValue = "abhi-top-1.0";

		File document = getPhysicalDocument("");
		String fileName = FilenameUtils.removeExtension(document.getName());

		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectOverviewPage openProjectDetails = searchResultPage.openProjectDetails(projectValue);
		ProjectDocumentsPage documentsPage = openProjectDetails.getDocumentsPage();
		ProjectDocumentsToolBar loadToolBar = documentsPage.loadToolBar();
		UploadDocumentPanel addProjectDocumentPage = loadToolBar.getUploadDocumentPanel();
		addProjectDocumentPage.uploadDocument(document.getAbsolutePath());

		//As Indexing has issues lets search via traversing to Documents folder
		for (int i = 0; i < 6; i++) {
			try {
				new GlobalHomeIcon(driver).clickOnHomeButton();
				homePage = new HomePage(driver);
				searchResultPage = homePage.searchDocuments(fileName,10);
				break;
			} catch (Exception exception) {
				logger.warn("Search document not found. It seems document has not indexed yet."+ exception.getMessage());
			}
		}	

		DocumentDetailsPage openDocumentsDetails = searchResultPage.openDocumentsDetails(fileName);
		openDocumentsDetails.clickOnDocumentsCommentsTab();
		DocumentDetailsToolBar loadRightToolBarPage2 = openDocumentsDetails.loadRightToolBarPage();
		CreateCommentPanel createCommentPage = loadRightToolBarPage2.getCreateCommentPanel();
		createCommentPage.createComment(fileName, "1", "a", "Content for "+ fileName);

		new GlobalHomeIcon(driver).clickOnHomeButton();
		homePage = new HomePage(driver);
		searchResultPage = homePage.searchDocuments(fileName,10);
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		new CreateCommentTest().getPhysicalDocument(null);
	}

}


