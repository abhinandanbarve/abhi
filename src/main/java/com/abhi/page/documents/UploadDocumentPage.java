package com.abhi.page.documents;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhi.base.Config;
import com.abhi.helper.JavaScriptHelper;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.WebPage;

public class UploadDocumentPage extends WebPage{


	private final Logger logger = LoggerHelper.getLogger(UploadDocumentPage.class);

	@FindBy(xpath="//dnvgl-file-upload//button[@id= 'inputfileButton' and contains(text(),'Choose Files')]")
	private WebElement chooseFilesButton;

	@FindBy(xpath="//dnvgl-file-upload//input[@type='file' and @id='inputfilehiddenbutton']")
	private WebElement inputFileType;


	@FindBy(xpath="//aw-include[@name='AP4_AddProjectDocument']//button[@action='uploadOKClicked']//aw-i18n[contains(text(),'Create')]")
	private WebElement createButton;


	public UploadDocumentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitHelper.waitForElementToClick(chooseFilesButton,Config.getInstance().getExplicitWait());
	}

	private void clickOnCreateButton(){
		logger.info("clicking on create button...");
		createButton.click();		
	}

	private void selectFileToUpload(String strFilePath){
		logger.info("clicking on choose Files button...");		

		String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		new JavaScriptHelper(driver).executeScript(js, inputFileType);
		waitHelper.waitForElement(inputFileType,5);
		inputFileType.sendKeys(strFilePath);
	}

	public void uploadDocument(String strFilePath){			
		logger.info("upload Documentin progress...");
		boolean isAlertVisible = true;
		int counter = 11;
		for(int i=0; i < 4; i++)
		{
			try {
				selectFileToUpload(strFilePath);
				waitHelper.waitForElement(createButton,2);
				clickOnCreateButton();
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			waitHelper.waitForElementToPresence(By.xpath("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Files have been uploaded successfully.')]"), Config.getInstance().getExplicitWait());
		} catch (Exception e) {
			e.printStackTrace();
		}

		while(isAlertVisible || counter == 10) {
			List<WebElement> findElements = null;
			try {	
				
				findElements = driver.findElements(By.xpath("//ul[@id='noty_bottom_layout_container']//div[contains(@class,'noty_close')]"));

				for (WebElement webElement : findElements) {
					
					try {
						webElement.click();	
						webElement.click();	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(findElements == null || findElements.size() ==0)
				isAlertVisible = false;
			counter++;
		}
	}
}


/*
$x("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Files have been uploaded successfully.')]")
$x("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Initiating file upload. Please be patient, this may take few minutes depending upon your internet speed.')]")
Documents have been created successfully.
$x("//ul[@id='noty_bottom_layout_container']//span[@class='noty_text' and contains(text(),'Files have been uploaded successfully.')]")

 $x()

 *
 */