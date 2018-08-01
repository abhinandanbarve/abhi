package com.abhi.test.project;

import com.abhi.base.TestBase;
import com.abhi.page.dashboard.HomePage;
import com.abhi.page.globalsearch.GlobalHomeIcon;
import com.abhi.page.globalsearch.GlobelSearchResultPage;
import com.abhi.page.panel.AssignCustomerPanel;
import com.abhi.page.project.ProjectCustomerPage;
import com.abhi.page.project.ProjectOverviewPage;

public class AssignCustomerTest extends TestBase{

	public void assignCustomerToProject(String projectValue, String customerName) {

		HomePage homePage = new HomePage(driver);
		GlobelSearchResultPage searchResultPage = homePage.searchProject(projectValue);
		ProjectOverviewPage openProjectDetails = searchResultPage.openProjectDetails(projectValue);
		ProjectCustomerPage projectCustomerPage = openProjectDetails.getCustomerDetailsPage();
		
		AssignCustomerPanel assignCustomerPanel = projectCustomerPage.loadAssignCustomerPanel();
		
		assignCustomerPanel.assignCustomer(customerName);
		new GlobalHomeIcon(driver).clickOnHomeButton();
		
	}
}
