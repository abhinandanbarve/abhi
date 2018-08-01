package com.abhi.tcrareport;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.abhi.base.TestBase;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.dashboard.TilePage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.project.ProjectOverviewPage;
import com.abhi.page.project.create.CreateProjectDetailsPage;
import com.abhi.page.project.create.CreateProjectInput;
import com.abhi.page.project.create.CreateProjectPage;

public class CreateProjectTest extends TestBase{

	private final Logger logger = LoggerHelper.getLogger(CreateProjectTest.class);

	
	@Test
	public void createProjectTest() {

		String projectValue = "abhi-demo-12";
		CreateProjectInput projectInput = new CreateProjectInput();
		projectInput.setEstimatedEndDate("08-Jul-2019");
		projectInput.setServiceArea("Noble Denton Marine Services");
		projectInput.setServiceLine("Marine Warranty Surveys");
		projectInput.setServiceType("Operation");
		projectInput.setServiceMode("OG23 Upstream");
		
		TilePage tilePage = new TilePage(driver);
		CreateProjectPage selectCreateProjectTile = tilePage.selectCreateProjectTile();
		CreateProjectDetailsPage createProject = selectCreateProjectTile.createProject(projectValue);
		
		createProject.createProjectDetails(projectInput);
		
		
		new GlobalHomeIcon(driver).clickOnHomeButton();
		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchProject = homePage.searchProject(projectValue);
		ProjectOverviewPage openProjectDetails = searchProject.openProjectDetails(projectValue);
		
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		new CreateProjectTest().getPhysicalDocument(null);
	}

}


