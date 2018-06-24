
package com.abhi.helper;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author abhinandan
 * 
 */
public class DropDownHelper {
	
	private WebDriver driver;
	private Logger Log = LoggerHelper.getLogger(DropDownHelper.class);

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("DropDownHelper : " + this.driver.hashCode());
	}

	
	public void selectUsingVisibleValue(WebElement element,String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		Log.info("Locator : " + element + " Value : " + visibleValue);
	}

	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		Log.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	public void selectUsingIndex(WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		Log.info("Locator : " + element + " Value : " + index);
	}
	
	public void selectUsingVisibleText(WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		Log.info("Locator : " + element + " Value : " + text);
	}
	
	
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			Log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
}
