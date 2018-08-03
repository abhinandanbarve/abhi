package com.abhi.helper;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abhi.utility.StaleElementUtils;

/**
 * @author abhinandan
 * 
 */
public class WaitHelper {

	private WebDriver driver;
	private Logger Log = LoggerHelper.getLogger(WaitHelper.class);


	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("WaitHelper : " + this.driver.hashCode());
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		Log.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	public void setPageLoadTimeout(long timeout, TimeUnit unit) {
		Log.info(timeout);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Log.debug("");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		Log.info(locator);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}



	public void waitForElement(WebElement element, long timeout) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);		
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.info("element found..."+element.getText());
		} catch (StaleElementReferenceException exception) {	
			Log.error("Stale element exception. please check impl" + exception);
			StaleElementUtils.refreshElement(driver, element);
			waitForElement(element, timeout);
		}
	}

	public void waitForElementToPresence(By by, long timeout) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);		
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Log.info("element found..."+by.toString());
		} catch (Exception exception) {	
			Log.error("Exception. please check impl" + exception);			

		}
	}

	public void waitForElementToClick(WebElement element, long timeout) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);		
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Log.info("element found..."+element.toString());
		} catch (Exception exception) {	
			Log.error("Exception. please check impl" + exception);			

		}
	}	
	
	public void waitForSeaconds(int seconds) {

		try {
			Thread.sleep(seconds*1000);
		} catch (Exception exception) {	
			Log.error("Exception.Thread.sleep" + exception);			

		}
	}	
}
