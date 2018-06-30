package com.abhi.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.abhi.excel.ExcelReader;
import com.abhi.helper.LoggerHelper;
import com.abhi.page.HomePage;
import com.abhi.page.LoginPage;
import com.abhi.page.LogoutPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestBase {

	private final Logger logger = LoggerHelper.getLogger(TestBase.class);

	public WebDriver driver;
	public static Properties OR;
	
	public File f1;
	public FileInputStream file;

	public static ExtentReports extent;
	public static ExtentTest test;
	public ExcelReader excelReader;
	public ITestResult result;

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/report/result/test" + formater.format(calendar.getTime()) + ".html", false);
	}

	@BeforeTest
	public void launchBrowserAndLogin() throws Exception{

		try {
			loadPropertiesFile();
			Assert.assertTrue(OR != null && OR.size() > 0, "Exception occured while loading configuration files.");
		} catch (Exception exception) {
			logger.error("Exception occured while loading configuration files.", exception);
			throw exception;
		}

		getBrowser(Config.getInstance().getBrowser());
		driver.get(Config.getInstance().getWebsite());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		HomePage homePage = loginToApplication();
		boolean isLoginSucsess = homePage.isLoginSucsess();
		Assert.assertTrue(isLoginSucsess,"Login to Teamcenter application fail. Please verify credentials.");
	
	}

	@AfterTest
	public void logoutAndCloseBrowser(){

		boolean isLogoutSuccess = logoutTcApplication();
		boolean isCloseBrowserSuccess = closeBrowser();
		Assert.assertTrue(isLogoutSuccess && isCloseBrowserSuccess, "Exception occured while logout or close browser operation. Please verify application and test case.");
	}

	
	public HomePage loginToApplication() {		
		LoginPage loginPage = new LoginPage(driver);
		return loginPage.loginToApplication(Config.getInstance().getUserName(), Config.getInstance().getPassword());

	}

	public boolean logoutTcApplication() {

		boolean isOperationSuccess = true;
		
		try 
		{
			new LogoutPage(driver).logoutTcApplication();
		} 
		catch (Exception exception) 
		{
			logger.error("Exception occured while logout operation. Retrying one more time.", exception);
			try 
			{
				new LogoutPage(driver).logoutTcApplication();
			} catch (Exception exception2) 
			{
				logger.error("Exception occured while logout operation second time, continue closing browser without logout. Please clear tcserver instance from pool manager", exception);
				isOperationSuccess = false;
			}
		}
		return isOperationSuccess;
	}

	private boolean closeBrowser() {

		boolean isOperationSuccess = true;

		try 
		{
			driver.close();
			driver.quit();
		} 
		catch (Exception exception) 
		{
			logger.error("Exception occured while closing Browser. Retrying one more time.", exception);
			try 
			{
				driver.close();
				driver.quit();				
			} 
			catch (Exception exception2) 
			{
				logger.error("Exception occured while closing Browser second time.", exception);
				isOperationSuccess = false;
			}
		}

		return isOperationSuccess;
	}

	public void getBrowser(String browser){
		
		String driverPath = System.getProperty("user.dir")+"/src/main/resources/drivers/";
				
		if(System.getProperty("os.name").contains("Window")){
			if(browser.equalsIgnoreCase("firefox")){
				//https://github.com/mozilla/geckodriver/releases
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
				//driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				//https://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
				ChromeOptions options = new ChromeOptions(); 
				options.addArguments("disable-infobars"); 
				driver = new ChromeDriver(options);
			}
		}
		else if(System.getProperty("os.name").contains("Mac")){
			System.out.println(System.getProperty("os.name"));
			if(browser.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver");
				//driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver");
				ChromeOptions options = new ChromeOptions(); 
				options.addArguments("disable-infobars"); 
				driver = new ChromeDriver(options);
			}
		}
	}

	public void loadPropertiesFile() throws IOException{

		OR = new Properties();
		f1 = new File(System.getProperty("user.dir")+"/src/main/resources/config/config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading config.properties");
		
	}

	public String getScreenShot(String imageName) throws IOException{

		if(imageName.equals("")){
			imageName = "blank";
		}
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = System.getProperty("user.dir")+"/report/screenshot/";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);
		return actualImageName;
	}

	public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {

			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + " test is failed" + result.getThrowable());
			String screen = getScreenShot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	
	@AfterClass(alwaysRun = true)
	public void endTest() {		
		extent.endTest(test);
		extent.flush();
	}
	
	public String[][] getData(String excelName, String sheetName){
		System.out.println(System.getProperty("user.dir"));
		String excellocation = System.getProperty("user.dir")+"/src/main/resources/data/"+excelName;
		System.out.println(excellocation);
		excelReader = new ExcelReader();
		return excelReader.getExcelData(excellocation, sheetName);
	}
	
	public File getPhysicalDocument(String filePath){

		if(filePath != null && new File(filePath).exists())
			return new File(filePath);

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		
		String destinationDocument = formater.format(calendar.getTime());
		String sourceDocument = System.getProperty("user.dir")+"/src/main/resources/sample-data/document1.pdf";

		File sourceFile = new File(sourceDocument);
		File destinationFile = new File(System.getProperty("user.dir")+"/cache/"+destinationDocument+".pdf");

		try {
			FileUtils.copyFile(sourceFile, destinationFile);
		} catch (Exception exception) {
			Assert.fail("Expected document does not exist", exception);
		}

		return destinationFile;

	}

	public static void updateResultupdateResult(int indexSI,  String screenShotLocation,String response) throws IOException {
		String startDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
		System.out.println("startDateTime---"+startDateTime);

		String resultFile = System.getProperty("user.dir")+"/report/TestReport.html";

		File file = new File(resultFile);
		System.out.println(file.exists());

		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>" + "\n");
			bw.write("<head><title>" + "Test execution report" + "</title>" + "\n");
			bw.write("</head>" + "\n");
			bw.write("<body>");
			bw.write("<font face='Tahoma'size='2'>" + "\n");
			bw.write("<u><h1 align='center'>" + "Test execution report" + "</h1></u>" + "\n");
			bw.flush();
			bw.close();
		}
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
		if (indexSI == 1) {

			bw1.write("<table align='center' border='0' width='70%' height='10'>");
			bw1.write("<tr><td width='70%' </td></tr>");
			bw1.write("<table align='center' border='1' width='70%' height='47'>");
			bw1.write("<tr>");
			bw1.write("<td colspan='1' align='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF'' face='Tahoma' size='2'>Resiliency Test </font></b></td>");
			bw1.write("<td colspan='2' align='left'><b><font color='#000000' face='Tahoma' size='2'>Start Time :&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='1'>" + startDateTime + " </font></td>");
			bw1.write("</tr>");
			bw1.write("</tr>");
			bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
			bw1.write("<td  bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Screen Shot</font></b></td>");
			bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Response </font></b></td>");
			bw1.write("</tr>");
		}

		bw1.write("<tr>" + "\n");
		bw1.write("<td bgcolor='#FFFFDC'align='Center'><font color='#000000' face='Tahoma' size='2'>" + indexSI + "</font></td>" + "\n");
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + "<img src="+screenShotLocation+" alt='Smiley face' height='500' width='750'>" + "</font></b></td>" + "\n");
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + response + "</font></b></td>" + "\n");
		bw1.write("</tr>" + "\n");
		bw1.write("</body>" + "\n");
		bw1.write("</html>");
		bw1.flush();
		bw1.close();
	}	

}
