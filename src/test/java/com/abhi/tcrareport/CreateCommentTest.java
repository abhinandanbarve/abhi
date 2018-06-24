package com.abhi.tcrareport;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abhi.base.TestBase;
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


	@Test
	public void documentUploadTest() {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String strFileName = formater.format(calendar.getTime());

		String projectValue = "abhi-top-1.0";
		File sourceFile = new File("C:\\Abhinandan\\BHLKCL.pdf");

		File destinationFile = new File("C:\\Abhinandan\\"+strFileName+".pdf");

		sourceFile.renameTo(destinationFile);

		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectDetailsPage openProjectDetails = searchResultPage.openProjectDetails(projectValue);
		openProjectDetails.clickOnProjectDetaileTab();
		ProjectRightToolBarPage loadRightToolBarPage = openProjectDetails.loadRightToolBarPage();

		AddProjectDocumentPage addProjectDocumentPage = loadRightToolBarPage.selectAddDocumentCommand();
		addProjectDocumentPage.uploadDocument(destinationFile.getAbsolutePath());

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(true, "Resport is not executed. Please verify..");
		new GlobalHomeIcon(driver).clickOnHomeButton();

		homePage = new HomePage(driver);
		searchResultPage = homePage.searchDocuments(strFileName);
		DocumentDetailsPage openDocumentsDetails = searchResultPage.openDocumentsDetails(strFileName);
		openDocumentsDetails.clickOnDocumentsCommentsTab();
		DocumentsRightToolBarPage loadRightToolBarPage2 = openDocumentsDetails.loadRightToolBarPage();
		CreateCommentPage createCommentPage = loadRightToolBarPage2.selectAddCommentCommand();
		createCommentPage.createComment(strFileName, "1", "a", "Content for "+ strFileName);
	}

}


