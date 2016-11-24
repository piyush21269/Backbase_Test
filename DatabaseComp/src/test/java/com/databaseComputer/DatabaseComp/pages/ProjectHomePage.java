package com.databaseComputer.DatabaseComp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Reporter;

import com.databaseComputer.DatabaseComp.commonUtils.CommonMethods;

public class ProjectHomePage extends CommonMethods{
	String HomePageChecker_Xpath = readFromLocatorPropFile("HomePageChecker_Xpath");
	String addANewCompBtn_Id= readFromLocatorPropFile("addANewCompBtn_Id");
	String addAComputerPageChecker_Xpath= readFromLocatorPropFile("addAComputerPageChecker_Xpath");
	String doneText_Xpath = readFromLocatorPropFile("doneText_Xpath");
	String doneMessageText_Xpath= readFromLocatorPropFile("doneMessageText_Xpath");
	String searchboxTextbox_Id = readFromLocatorPropFile("searchboxTextbox_Id");
	String filterByNameBtn_Id= readFromLocatorPropFile("filterByNameBtn_Id");
	//String clickSrchResult_LinkText= readFromLocatorPropFile("clickSrchResult_LinkText");
	
	public boolean isOnHomePage(){
		
		boolean flag;
		try {
			flag = false;
			String expectedStr="Play sample application — Computer database";
			waitForAnyObject(By.xpath(HomePageChecker_Xpath), 20);
			String actualStr=driver.findElement(By.xpath(HomePageChecker_Xpath)).getText();
			if(expectedStr.equalsIgnoreCase(actualStr.trim())){
				flag=true;
				Reporter.log("Navigated to Homepage", true);
			}
		} catch (NoSuchElementException e) {
			flag = false;
			e.printStackTrace();
			
		}catch(TimeoutException e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public void clickOnAddNewComputer(){
		clickAnyElement(By.id(addANewCompBtn_Id));
		waitForPageToLoad();
		waitForAnyObject(By.xpath(addAComputerPageChecker_Xpath), 20);
	}
	
	public boolean verifyDoneMessage(){
		boolean flag;
		try {
			flag = false;
			String expectedStr="Done!";
			waitForAnyObject(By.xpath(doneText_Xpath), 20);
			String actualStr=driver.findElement(By.xpath(doneText_Xpath)).getText();
			if(expectedStr.equalsIgnoreCase(actualStr.trim())){
				flag=true;
			}
		} catch (NoSuchElementException e) {
			flag = false;
			e.printStackTrace();
			
		}catch(TimeoutException e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
		
	}
	public String getDoneMessage(){
		return ""+driver.findElement(By.xpath(doneMessageText_Xpath)).getText();
	}
	
	public void searchAndClickLastCreatedComputer(){
		String fileNameAndPath = readFromConfigPropFile("runTimeInfoFolderNameAndPath")+readFromConfigPropFile("runTimePropFile");
		String lastCreatedCompName = readFromPropertiesFile(fileNameAndPath, "computerName");
		enterTextInAnyTextbox(By.id(searchboxTextbox_Id), lastCreatedCompName);
		clickAnyElement(By.id(filterByNameBtn_Id));
		clickAnyElement(By.linkText(lastCreatedCompName));
		waitForPageToLoad();
		
	}
}
