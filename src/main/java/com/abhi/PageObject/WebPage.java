package com.abhi.PageObject;

import org.openqa.selenium.WebDriver;

import com.abhi.helper.Wait.WaitHelper;

public class WebPage {


	WebDriver driver;
	WaitHelper waitHelper;

	public WebPage(WebDriver driver) {
		this.driver = driver;
		waitHelper = new WaitHelper(driver);
	}
}
