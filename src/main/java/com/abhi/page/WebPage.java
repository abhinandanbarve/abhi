package com.abhi.page;

import org.openqa.selenium.WebDriver;

import com.abhi.helper.WaitHelper;

public class WebPage {


	protected WebDriver driver;
	protected WaitHelper waitHelper;

	public WebPage(WebDriver driver) {
		this.driver = driver;
		waitHelper = new WaitHelper(driver);
	}
}
