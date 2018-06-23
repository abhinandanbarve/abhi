package com.abhi.page;

import org.openqa.selenium.WebDriver;

import com.abhi.helper.wait.WaitHelper;

public class WebPage {


	WebDriver driver;
	WaitHelper waitHelper;

	public WebPage(WebDriver driver) {
		this.driver = driver;
		waitHelper = new WaitHelper(driver);
	}
}
