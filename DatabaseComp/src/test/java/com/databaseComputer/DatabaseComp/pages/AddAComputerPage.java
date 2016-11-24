package com.databaseComputer.DatabaseComp.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Reporter;

import com.databaseComputer.DatabaseComp.commonUtils.CommonMethods;

public class AddAComputerPage extends CommonMethods{
	String addAComputerPageChecker_Xpath = readFromLocatorPropFile("addAComputerPageChecker_Xpath");
	String compNameTextbox_Id= readFromLocatorPropFile("compNameTextbox_Id");
	String introducedDateTextbox_Id= readFromLocatorPropFile("introducedDateTextbox_Id");
	String discontinuedDateTextbox_Id = readFromLocatorPropFile("discontinuedDateTextbox_Id");
	String companySelectbox_Id= readFromLocatorPropFile("companySelectbox_Id");
	String createThisComputerBtn_Xpath= readFromLocatorPropFile("createThisComputerBtn_Xpath");
	String cancelBtn_Xpath= readFromLocatorPropFile("cancelBtn_Xpath");
    private String dateNTime = replaceSlashesAndColonsWithUnderscore(getDateAndTime());

	public boolean isOnAddAComputerPage(){
		boolean flag;
		try {
			flag = false;
			String expectedStr="Add a computer";
			waitForAnyObject(By.xpath(addAComputerPageChecker_Xpath), 20);
			String actualStr=driver.findElement(By.xpath(addAComputerPageChecker_Xpath)).getText();
			if(expectedStr.equalsIgnoreCase(actualStr.trim())){
				flag=true;
				Reporter.log("Navigated to Add A Computer Page", true);

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

	public void enterInfoAndCreateComputer(String computerName, String introducedDate, String discontinuedDate, String company) throws IOException  {
		computerName =computerName+"_"+dateNTime;
		enterTextInAnyTextbox(By.id(compNameTextbox_Id), computerName);
		createAndWriteDataToPropFile(readFromConfigPropFile("runTimePropFile"),"computerName", computerName);
		enterTextInAnyTextbox(By.id(introducedDateTextbox_Id), introducedDate);
		createAndWriteDataToPropFile(readFromConfigPropFile("runTimePropFile"),"introducedDate", introducedDate);
		enterTextInAnyTextbox(By.id(discontinuedDateTextbox_Id), discontinuedDate);
		createAndWriteDataToPropFile(readFromConfigPropFile("runTimePropFile"),"discontinuedDate", discontinuedDate);
		selectSelectBoxByText(By.id(companySelectbox_Id), company);
		createAndWriteDataToPropFile(readFromConfigPropFile("runTimePropFile"),"company", company);
		clickAnyElement(By.xpath(createThisComputerBtn_Xpath));
		waitForPageToLoad();
		
	}
}
