package com.abhi.page;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.LoggerHelper;

public class AddProjectDocumentPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(AddProjectDocumentPage.class);

	@FindBy(xpath="//dnvgl-file-upload//button[@id= 'inputfileButton' and contains(text(),'Choose Files')]")
	private WebElement chooseFilesButton;

	@FindBy(xpath="//dnvgl-file-upload//input[@type='file' and @id='inputfilehiddenbutton']")
	private WebElement inputFileType;


	@FindBy(xpath="//aw-include[@name='AP4_AddProjectDocument']//button[@action='uploadOKClicked']//aw-i18n[contains(text(),'Create')]")
	private WebElement createButton;


	public AddProjectDocumentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElement(driver, chooseFilesButton,Config.getInstance().getExplicitWait());
	}

	private void clickOnCreateButton(){
		logger.info("clicking on create button...");
		createButton.click();		
	}

	private void setFileToUpload(String strFilePath){
		logger.info("clicking on choose Files button...");
		inputFileType.sendKeys(strFilePath);		
	}

	public void uploadDocument(String strFilePath){	
		
		setFileToUpload(strFilePath);
		try {
			File f1 = new File(strFilePath);
			String fileName = f1.getName();

			WebElement findElement = driver.findElement(By.xpath("//dnvgl-file-upload//div//div[contains(text(),'"+fileName+"')]"));
			String text = findElement.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickOnCreateButton();

		/*
$x("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Files have been uploaded successfully.')]")
$x("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Initiating file upload. Please be patient, this may take few minutes depending upon your internet speed.')]")
Documents have been created successfully.
$x("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Files have been uploaded successfully.')]")
		 */
	}
}