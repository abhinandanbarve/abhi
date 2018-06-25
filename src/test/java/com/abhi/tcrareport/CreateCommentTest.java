package com.abhi.tcrareport;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.AddProjectDocumentPage;
import com.abhi.page.CreateCommentPage;
import com.abhi.page.DocumentDetailsPage;
import com.abhi.page.DocumentsRightToolBarPage;
import com.abhi.page.GlobalHomeIcon;
import com.abhi.page.GlobelSearchResultPage;
import com.abhi.page.HomePage;
import com.abhi.page.ProjectDetailsPage;
import com.abhi.page.ProjectRightToolBarPage;

public class CreateCommentTest extends TestBase{

	private final Logger logger = LoggerHelper.getLogger(CreateCommentTest.class);

	
	@Test
	public void documentUploadTest() {

		String projectValue = "abhi-top-1.0";

		File document = getPhysicalDocument("");
		String fileName = FilenameUtils.removeExtension(document.getName());

		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectDetailsPage openProjectDetails = searchResultPage.openProjectDetails(projectValue);
		openProjectDetails.clickOnProjectDetaileTab();
		ProjectRightToolBarPage loadRightToolBarPage = openProjectDetails.loadRightToolBarPage();

		AddProjectDocumentPage addProjectDocumentPage = loadRightToolBarPage.selectAddDocumentCommand();
		addProjectDocumentPage.uploadDocument(document.getAbsolutePath());

		for (int i = 0; i < 6; i++) {
			try {
				new GlobalHomeIcon(driver).clickOnHomeButton();
				homePage = new HomePage(driver);
				searchResultPage = homePage.searchDocuments(fileName,5);
				break;
			} catch (Exception exception) {
				logger.warn("Search document not found. It seems document has not indexed yet."+ exception.getMessage());
			}
		}	
		

		
		DocumentDetailsPage openDocumentsDetails = searchResultPage.openDocumentsDetails(fileName);
		openDocumentsDetails.clickOnDocumentsCommentsTab();
		DocumentsRightToolBarPage loadRightToolBarPage2 = openDocumentsDetails.loadRightToolBarPage();
		CreateCommentPage createCommentPage = loadRightToolBarPage2.selectAddCommentCommand();
		createCommentPage.createComment(fileName, "1", "a", "Content for "+ fileName);
	}

	public static void main(String[] args) throws IOException {

		new CreateCommentTest().getPhysicalDocument(null);
	}

}

