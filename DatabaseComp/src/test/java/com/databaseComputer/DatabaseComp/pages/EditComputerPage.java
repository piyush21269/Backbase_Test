package com.databaseComputer.DatabaseComp.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.Reporter;

import com.databaseComputer.DatabaseComp.commonUtils.CommonMethods;

public class EditComputerPage extends CommonMethods{
	String editComputerPageChecker_Xpath = readFromLocatorPropFile("editComputerPageChecker_Xpath");
	String compNameTextbox_Id= readFromLocatorPropFile("compNameTextbox_Id");
	String introducedDateTextbox_Id= readFromLocatorPropFile("introducedDateTextbox_Id");
	String discontinuedDateTextbox_Id = readFromLocatorPropFile("discontinuedDateTextbox_Id");
	String companySelectbox_Id= readFromLocatorPropFile("companySelectbox_Id");

	String editCompCompanyselectedSelectbox_Xpath= readFromLocatorPropFile("editCompCompanyselectedSelectbox_Xpath");
	String editCompSaveThisComputerBtn_Xpath= readFromLocatorPropFile("editCompSaveThisComputerBtn_Xpath");
	String editCompDeleteThisComputerBtn_Xpath= readFromLocatorPropFile("editCompDeleteThisComputerBtn_Xpath");
    private String dateNTime = replaceSlashesAndColonsWithUnderscore(getDateAndTime());

	public boolean isOnEditComputerPage(){
		boolean flag;
		try {
			flag = false;
			String expectedStr="Edit computer";
			waitForAnyObject(By.xpath(editComputerPageChecker_Xpath), 20);
			String actualStr=driver.findElement(By.xpath(editComputerPageChecker_Xpath)).getText();
			if(expectedStr.equalsIgnoreCase(actualStr.trim())){
				flag=true;
				Reporter.log("Navigated to Edit Computer Page", true);

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
	public void updateComputer(String computerName, String introducedDate, String discontinuedDate, String company) throws IOException{
		computerName =computerName+"_"+dateNTime;
		enterTextInAnyTextbox(By.id(compNameTextbox_Id), computerName);
		createAndWriteDataToPropFile(readFromConfigPropFile("runTimePropFile"),"computerName", computerName);
		enterTextInAnyTextbox(By.id(introducedDateTextbox_Id), introducedDate);
		createAndWriteDataToPropFile(readFromConfigPropFile("runTimePropFile"),"introducedDate", introducedDate);
		enterTextInAnyTextbox(By.id(discontinuedDateTextbox_Id), discontinuedDate);
		createAndWriteDataToPropFile(readFromConfigPropFile("runTimePropFile"),"discontinuedDate", discontinuedDate);
		selectSelectBoxByText(By.id(companySelectbox_Id), company);
		createAndWriteDataToPropFile(readFromConfigPropFile("runTimePropFile"),"company", company);
		clickAnyElement(By.xpath(editCompSaveThisComputerBtn_Xpath));
		waitForPageToLoad();
	}
	public void deleteAComputer(){
		clickAnyElement(By.xpath(editCompDeleteThisComputerBtn_Xpath));
	}
	
	public void readValuesAndDeleteComputer(){
		String compName = driver.findElement(By.id(compNameTextbox_Id)).getAttribute("value");
		String introducedDate = driver.findElement(By.id(introducedDateTextbox_Id)).getAttribute("value");
		String discontinuedDate = driver.findElement(By.id(discontinuedDateTextbox_Id)).getAttribute("value");
		String companySelected = driver.findElement(By.xpath(editCompCompanyselectedSelectbox_Xpath)).getText();
		String fileNameAndPath = readFromConfigPropFile("runTimeInfoFolderNameAndPath")+readFromConfigPropFile("runTimePropFile");
		String lastCreatedCompName = readFromPropertiesFile(fileNameAndPath, "computerName");
		if(compName.equalsIgnoreCase(lastCreatedCompName)){
			Reporter.log("Company Name - "+compName, true);

		}else{
			Reporter.log("Company Name - "+compName+" didnt match", true);
		}
		String lastCreatedIntroducedDate = readFromPropertiesFile(fileNameAndPath, "introducedDate");
		if(introducedDate.equalsIgnoreCase(lastCreatedIntroducedDate)){
			Assert.assertTrue(true);
			Reporter.log("Introduced Date - "+introducedDate, true);

		}else{
			Reporter.log("Introduced Date - "+introducedDate+" didnt match", true);
		}
		String lastCreatedDiscontinuedDate = readFromPropertiesFile(fileNameAndPath, "discontinuedDate");
		if(discontinuedDate.equalsIgnoreCase(lastCreatedDiscontinuedDate)){
			Reporter.log("Discontinued Date - "+discontinuedDate, true);

		}else{
			Reporter.log("Discontinued Date - "+discontinuedDate+" didnt match", true);

		}
		String lastCreatedCompanySelected = readFromPropertiesFile(fileNameAndPath, "company");
		if(companySelected.equalsIgnoreCase(lastCreatedCompanySelected)){
			Reporter.log("Company Selected - "+companySelected, true);
		}else{
			Reporter.log("Company Selected - "+companySelected+" didnt match", true);

		}
		
		deleteAComputer();
	
	}

}
