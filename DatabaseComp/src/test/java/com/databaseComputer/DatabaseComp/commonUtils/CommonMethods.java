package com.databaseComputer.DatabaseComp.commonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class CommonMethods {
	private static final String configPropFilePath="src\\test\\resources\\config.properties";

	public static WebDriver driver; 
	private static String osName;
	private static final String browser=readFromConfigPropFile("browser");
	private static final String testDataJsonFilePath=readFromConfigPropFile("jsonFilePath");
	private static final String locatorPropFilePath=readFromConfigPropFile("locatorPropertiesFilePath");
	private static final String chromeDriverPath=readFromConfigPropFile("chromeDriverPath");
	private static final String ieDriverPath=readFromConfigPropFile("ieDriverPath");
	private static final String runTimeInfoFolderNameAndPath=readFromConfigPropFile("runTimeInfoFolderNameAndPath");
	private static final String downloadFilepath =readFromConfigPropFile("downloadFilepath");
	private static final String projectUrl =readFromConfigPropFile("projectUrl");

	private static int createAndWriteDataToPropFile_COUNT=0;
	
	public static WebDriver getExistingWebDriver() {
		return driver;
	}
	public static String getConfigPropFilePath() {
		return configPropFilePath;
	}
	public static String getTestDataJsonFilePath() {
		return testDataJsonFilePath;
	}
	
	
	
	public static void initDriver(String browser){
		if(browser.equalsIgnoreCase("chrome")){
			if(getOsName().toLowerCase().contains("windows")){
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				//ChromeDriverManager.getInstance().setup();
		       //String downloadFilepath = "D:\\fadv_git\\SerenityCucumberDemo\\Downloads";
		 
		       HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		       chromePrefs.put("profile.default_content_settings.popups", 0);
		       chromePrefs.put("download.default_directory", downloadFilepath);
		       ChromeOptions options = new ChromeOptions();
		       HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		       options.setExperimentalOption("prefs", chromePrefs);
		       options.addArguments("--test-type");
		  
		       DesiredCapabilities cap = DesiredCapabilities.chrome();
		       cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		       cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		       cap.setCapability(ChromeOptions.CAPABILITY, options);
		       driver = new ChromeDriver(cap);  
			}
			//driver= new ChromeDriver();
			driver.manage().window().maximize();
			
		}else if(browser.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			//InternetExplorerDriverManager.getInstance().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();

		}else if(browser==null || browser.isEmpty() || browser.equalsIgnoreCase("firefox")){
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference(
					"browser.download.manager.showWhenStarting", false);
			firefoxProfile
					.setPreference(
							"browser.helperApps.neverAsk.saveToDisk",
							"application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
			firefoxProfile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
			driver = new FirefoxDriver(firefoxProfile);
			driver.manage().deleteAllCookies();
			//driver = new FirefoxDriver();
			driver.manage().window().maximize();

		}else{
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference(
					"browser.download.manager.showWhenStarting", false);
			firefoxProfile
					.setPreference(
							"browser.helperApps.neverAsk.saveToDisk",
							"application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
			firefoxProfile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
			driver = new FirefoxDriver(firefoxProfile);
			driver.manage().deleteAllCookies();
			//driver= new FirefoxDriver();
			driver.manage().window().maximize();

		}
		
	}
	public static void initDriver(){
		if(browser.equalsIgnoreCase("chrome")){
			if(getOsName().toLowerCase().contains("windows")){
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				//ChromeDriverManager.getInstance().setup();
		 
		       HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		       chromePrefs.put("profile.default_content_settings.popups", 0);
		       chromePrefs.put("download.default_directory", downloadFilepath);
		       ChromeOptions options = new ChromeOptions();
		       HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		       options.setExperimentalOption("prefs", chromePrefs);
		       options.addArguments("--test-type");
		  
		       DesiredCapabilities cap = DesiredCapabilities.chrome();
		       cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		       cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		       cap.setCapability(ChromeOptions.CAPABILITY, options);
		       driver = new ChromeDriver(cap);  
			}
			//driver= new ChromeDriver();
			driver.manage().window().maximize();
			
		}else if(browser.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			//InternetExplorerDriverManager.getInstance().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();

		}else if(browser==null || browser.isEmpty() || browser.equalsIgnoreCase("firefox")){
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference(
					"browser.download.manager.showWhenStarting", false);
			firefoxProfile
					.setPreference(
							"browser.helperApps.neverAsk.saveToDisk",
							"application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
			firefoxProfile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
			driver = new FirefoxDriver(firefoxProfile);
			driver.manage().deleteAllCookies();
			//driver = new FirefoxDriver();
			driver.manage().window().maximize();

		}else{
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference(
					"browser.download.manager.showWhenStarting", false);
			firefoxProfile
					.setPreference(
							"browser.helperApps.neverAsk.saveToDisk",
							"application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
			firefoxProfile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
			driver = new FirefoxDriver(firefoxProfile);
			driver.manage().deleteAllCookies();
			//driver= new FirefoxDriver();
			driver.manage().window().maximize();

		}
	}
	/**
	 * Description: Method to check for Windows OS
	 * 
	 */
	
	public static boolean isWindows() {
		return getOsName().startsWith("Windows");
	}

	/**
	 * Description: Method to check for Mac OS
	 * 
	 */
	private static boolean isMac() {
		return (getOsName().indexOf("mac") >= 0);
	}

	/**
	 * Description: Method to delete Cookies from IE Browser.
	 * 
	 */
	public static void deleteCookiesIe() {
		try {
			Runtime.getRuntime().exec(
					"RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static String getOsName(){
		if(osName==null || osName.equalsIgnoreCase(null)){
			osName= System.getProperty("os.name");
		}
		return osName;
		
	}
	/**
	 * Description: Navigate to URL.
	 *  
	 */
	public static void navigateToUrl(String url) 
	{
		driver.get(url);
		waitForPageToLoad();
	}
	public static void navigateToProjectUrlUrl() 
	{
		driver.get(projectUrl);
		waitForPageToLoad();
	}
	/**
	 * Description: Method to maximize Window.
	 *  
	 */
	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public static String readFromConfigPropFile(String key) {
		Properties prop =new Properties();
		File file= new File(configPropFilePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);
		
	}
	public static String readFromLocatorPropFile(String key) {
		Properties prop =new Properties();
		
		File file= new File(locatorPropFilePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	public static String readFromPropertiesFile(String fileNameAndPath, String key) {
		Properties prop =new Properties();
		File file= new File(fileNameAndPath+".properties");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	public void createAndWriteDataToPropFile(String fileName, String key, String value) throws IOException{
		/*String dateNTime = replaceSlashesAndColonsWithUnderscore(getDateAndTime());
		fileName = fileName+"_"+dateNTime;*/
		File file = new File(runTimeInfoFolderNameAndPath);
		/*if(createAndWriteDataToPropFile_COUNT==0 || !file.exists()){
			file.mkdirs();
		}*/
		if(!file.exists()){
			file.mkdirs();
		}
		File fileNameForIO= new File(runTimeInfoFolderNameAndPath+File.separator+fileName+".properties");
		if(!fileNameForIO.exists()){
			fileNameForIO.createNewFile();
		}
		FileInputStream fis= new FileInputStream(fileNameForIO);
		Properties prop = new Properties();
		prop.load(fis);
		prop.setProperty(key, value);
		FileOutputStream fos = new FileOutputStream(fileNameForIO);
		prop.store(fos, "Data Written During Runtime");
		fos.close();
		//createAndWriteDataToPropFile_COUNT++;
	}
	/*public static  void createAndWriteDataToPropFile(String fileName, String key, String value) throws IOException{
		
		File file = new File(runTimeInfoFolderNameAndPath+fileName+".properties");
		if(createAndWriteDataToPropFile_COUNT==0 || !file.exists()){
			file.createNewFile();
		}
		FileInputStream fis= new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		prop.setProperty(key, value);
		FileOutputStream fos = new FileOutputStream(file);
		prop.store(fos, "Data Written During Runtime");
		fos.close();
		createAndWriteDataToPropFile_COUNT++;
	}*/
	public static String readDataFromJson(String key, String testCaseName) throws IOException, ParseException{
		
		FileInputStream fis= new FileInputStream(testDataJsonFilePath);
		InputStreamReader isr= new InputStreamReader(fis);
		//BufferedReader br= new BufferedReader(isr);
		JSONParser jParser = new JSONParser();
		JSONObject jFileObj = (JSONObject) jParser.parse(isr);
		JSONObject jDataObj = (JSONObject) jFileObj.get(testCaseName);
		String value = (String) jDataObj.get(key);
		
		
		return value;
		
	}
	
	public static void wait(int timeToWait){
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void waitForPageToLoad(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	public static void waitForPageToLoad(int timeInSeconds){
		driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}
	public static void waitForElement(By locator, int waitTime){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public static void waitForElementVisible(By locator, int waitTime){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public static WebElement waitForAnyObject(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static WebElement waitForAnyObject(By locator, int waitTime) {
		WebElement element = driver.findElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static boolean waitForVisibleText(By locator, int waitTime, String expectedText){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
	}
	
	public static  void waitForElementToBeClickable(By locator, int waitTime){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public static  boolean waitForElementToBeSelected(By locator, int waitTime){
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.elementToBeSelected(locator));
	}
	public static String clickAnyElement(By locator){
		waitForElementToBeClickable(locator, 20);
		WebElement we = driver.findElement(locator);
		String textDisplayed = we.getText();
		we.click();
		Reporter.log("Click on WebElement - "+locator.toString(), true);
		return textDisplayed;

	}
	public static boolean clearTextbox(WebElement we){
		boolean flag=false;
		we.clear();
		return flag;
		
	}
	public static void enterTextInAnyTextbox(By locator, String textToEnter){
		waitForElement(locator, 20);
		WebElement we = driver.findElement(locator);
		if(!we.getAttribute("value").isEmpty() || !we.getText().isEmpty()){
			clearTextbox(we);
			
		}
		we.sendKeys(textToEnter);;
		Reporter.log(locator.toString()+" Text Enter - "+textToEnter, true);

	}
	
	public static void selectSelectBoxByText(By locator, String textToSelect){
		try{
			Select sel= new Select(driver.findElement(locator));
		sel.selectByVisibleText(textToSelect);
		}catch(NoSuchElementException nse){
			WebElement select = driver.findElement(locator);
			List<WebElement> options = select.findElements(By.tagName("option"));
			for (WebElement option : options) {
				//System.out.println(option.getText());
				if(option.getText().contains(textToSelect))
					option.click();   
			}
		}
		
	}
	
	public static String clickYesNoRadioBtn(String YesOrNo, By yesLocator, By noLocator){
		if(YesOrNo.equalsIgnoreCase("yes") || YesOrNo.equalsIgnoreCase("true")){
			driver.findElement(yesLocator).click();
			return "Yes";
		}else if(YesOrNo.equalsIgnoreCase("no") || YesOrNo.equalsIgnoreCase("false")){
			driver.findElement(noLocator).click();
			return "No";
		}else{
			return "Not able to click yes or no";
		}
		
	}
	public static String checkOrUncheckAnyCheckBox(By locator, String checkOrUncheck){
		if(checkOrUncheck.equalsIgnoreCase("check")||checkOrUncheck.equalsIgnoreCase("yes") || checkOrUncheck.equalsIgnoreCase("true")){
			if(driver.findElement(locator).isSelected() == true){
				//do Nothing
			}else{
				driver.findElement(locator).click();
			}
			
			return "Checked";
		}else if(checkOrUncheck.equalsIgnoreCase("uncheck")||checkOrUncheck.equalsIgnoreCase("no") || checkOrUncheck.equalsIgnoreCase("false")){
			if(driver.findElement(locator).isSelected() == true){
				driver.findElement(locator).click();
			}else{
				//do nothing
			}
			
			return "Uncheck";
		}else{
			return "Not able to check Or Uncheck";
		}
		
	}

    public static String handleAlert(String action) {
		Alert alert = driver.switchTo().alert();
		if(action.equalsIgnoreCase("accept")){
			alert.accept();
		}else if(action.equalsIgnoreCase("dismiss")){
			alert.dismiss();
		}
		
		return alert.getText();
	}
    
    /**
	 * Description: Method to Drag and drop a element.
	 *  
	 */
    
    public static void dragAndDrop(WebElement sourceElement, WebElement destinationElement)
    {
        (new Actions(driver)).dragAndDrop(sourceElement, destinationElement).perform();
    }
    public static void dragAndDrop(By sourceLocator, By destinationLocator)
    {
    	WebElement sourceElement = driver.findElement(sourceLocator);
    	WebElement destinationElement = driver.findElement(destinationLocator);
    	Actions action = new Actions(driver);
    	action.dragAndDrop(sourceElement, destinationElement).perform();
        //(new Actions(driver)).dragAndDrop(sourceElement, destinationElement).perform();
    }
    public static void rightClick(WebElement element) {
			Actions action = new Actions(driver).contextClick(element);
			action.build().perform();
	}
    public static void rightClick(By locator) {
    	WebElement element = driver.findElement(locator);
		Actions action = new Actions(driver).contextClick(element);
		action.build().perform();
}
    public static void mouseOverElement(WebElement element)
    {
    	Actions action = new Actions(driver);
    	action.moveToElement(element).perform();
    }
    public static void mouseOverElement(By by)
    {
    	WebElement element = driver.findElement(by);
    	Actions action = new Actions(driver);
    	action.moveToElement(element).perform();
    }
    public static  Set<String> getWindowHandles(){
 		Set<String> st = driver.getWindowHandles();
 		return st;
 	}
 	public static  String getParentWindowId(){
 	
 		Set<String> st = getWindowHandles();
 		if(!(st==null)){
 			Iterator<String> it = st.iterator();
 			String parentWindowId= it.next();
 			return parentWindowId;
 		}else{
 			System.out.println("No window Handles found");
 			return null;
 		}
 	}
 	public static  List<String> getAllWindowIds(){
 		
 		Set<String> st = getWindowHandles();
 		List<String> allIds= new ArrayList<String>();
 		if(!(st==null)){
 			Iterator<String> it = st.iterator();
 			while(it.hasNext()){
 				allIds.add(it.next());
 			}
 			return allIds;
 		}else{
 			System.out.println("No window Handles found");
 			return null;
 		}
 	}
 	public static  void switchToParentWindow(){
 		driver.switchTo().window(getParentWindowId());
 	}
 	public static  void switchToChildWindow(int childWindowIndex){
 		driver.switchTo().window(getAllWindowIds().get(childWindowIndex));

 	}
 	public static  void closeChildWindow(){
 		driver.close();
 	}
 	public static  void switchToFrame(String frameId){
 		driver.switchTo().frame(frameId);
 	}
 	public static  void switchBackToDefalutFrame(){
 		driver.switchTo().defaultContent();
 	}
 	
	public static String getDateAndTime(){
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
			   //get current date time with Date()
			   Date date = new Date();
			   String dateAndTime=dateFormat.format(date);
			   //System.out.println(dateFormat.format(date));
			return dateAndTime;
		}
	public static String[] splitDateAndTime(String dateAndTimeVar){
		String[] dateNTimeAry = dateAndTimeVar.split(" ");
		return dateNTimeAry;
	}
	public static String replaceSlashesAndColonsWithUnderscore(String stringVariable){
		
		stringVariable=stringVariable.replace("//", "-");
		stringVariable=stringVariable.replace("\\", "-");
		//stringVariable=stringVariable.replaceAll("\\", " ");
		stringVariable=stringVariable.replace("/", "-");
		stringVariable=stringVariable.replace(":", "_");
		return stringVariable;
	}
}
