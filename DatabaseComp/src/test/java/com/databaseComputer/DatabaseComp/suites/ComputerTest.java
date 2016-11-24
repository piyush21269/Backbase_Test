package com.databaseComputer.DatabaseComp.suites;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.databaseComputer.DatabaseComp.commonUtils.CommonMethods;
import com.databaseComputer.DatabaseComp.pages.AddAComputerPage;
import com.databaseComputer.DatabaseComp.pages.EditComputerPage;
import com.databaseComputer.DatabaseComp.pages.ProjectHomePage;

public class ComputerTest extends CommonMethods{
	@BeforeTest
	public void initTest(){
		initDriver();
		navigateToProjectUrlUrl();
	}
	@Test(priority=1)
	public void addANewCompTest(){
		try {
			String computerName = readDataFromJson("computerName", "addANewCompTest");
			String introducedDate = readDataFromJson("introducedDate", "addANewCompTest");
			String discontinuedDate = readDataFromJson("discontinuedDate", "addANewCompTest");
			String company = readDataFromJson("company", "addANewCompTest");

			
			ProjectHomePage homePage = new ProjectHomePage();
			Assert.assertTrue( homePage.isOnHomePage());
			homePage.clickOnAddNewComputer();
			
			AddAComputerPage addComp = new AddAComputerPage();
			Assert.assertTrue( addComp.isOnAddAComputerPage());
			addComp.enterInfoAndCreateComputer(computerName, introducedDate, discontinuedDate, company);
			
			if(homePage.verifyDoneMessage()){
				Reporter.log(homePage.getDoneMessage(), true);
			}
			
		}catch(Exception e){
			Assert.fail("Test Failed", e);
			//e.printStackTrace();
		}
		
	}
	@Test(priority=2)
	public void deleteComputer(){
		
		try {
			ProjectHomePage homePage = new ProjectHomePage();
			Assert.assertTrue( homePage.isOnHomePage());
			homePage.searchAndClickLastCreatedComputer();
			
			EditComputerPage editComp = new EditComputerPage();
			Assert.assertTrue( editComp.isOnEditComputerPage());
			editComp.deleteAComputer();
			if(homePage.verifyDoneMessage()){
				Reporter.log(homePage.getDoneMessage(), true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail("Test Failed", e);
		}
		
		
	}
	
	@Test(priority=3)
	public void readAndDeleteComputer(){
		try {
			String computerName = readDataFromJson("computerName", "addANewCompTest");
			String introducedDate = readDataFromJson("introducedDate", "addANewCompTest");
			String discontinuedDate = readDataFromJson("discontinuedDate", "addANewCompTest");
			String company = readDataFromJson("company", "addANewCompTest");

			
			ProjectHomePage homePage = new ProjectHomePage();
			Assert.assertTrue( homePage.isOnHomePage());
			homePage.clickOnAddNewComputer();
			
			AddAComputerPage addComp = new AddAComputerPage();
			Assert.assertTrue( addComp.isOnAddAComputerPage());
			addComp.enterInfoAndCreateComputer(computerName, introducedDate, discontinuedDate, company);
			
			if(homePage.verifyDoneMessage()){
				Reporter.log(homePage.getDoneMessage(), true);
			}
			
			Assert.assertTrue( homePage.isOnHomePage());
			homePage.searchAndClickLastCreatedComputer();
			
			EditComputerPage editComp = new EditComputerPage();
			Assert.assertTrue( editComp.isOnEditComputerPage());
			editComp.readValuesAndDeleteComputer();
			if(homePage.verifyDoneMessage()){
				Reporter.log(homePage.getDoneMessage(), true);
			}
			
		}catch(Exception e){
			Assert.fail("Test Failed", e);
		}
		
		
	}
	@Test(priority=4)
	public void createAndUpdateComputer(){
		try {
			String computerName = readDataFromJson("computerName", "createAndUpdateComputer");
			String introducedDate = readDataFromJson("introducedDate", "createAndUpdateComputer");
			String discontinuedDate = readDataFromJson("discontinuedDate", "createAndUpdateComputer");
			String company = readDataFromJson("company", "createAndUpdateComputer");

			
			ProjectHomePage homePage = new ProjectHomePage();
			Assert.assertTrue( homePage.isOnHomePage());
			homePage.clickOnAddNewComputer();
			
			AddAComputerPage addComp = new AddAComputerPage();
			Assert.assertTrue( addComp.isOnAddAComputerPage());
			addComp.enterInfoAndCreateComputer(computerName, introducedDate, discontinuedDate, company);
			
			if(homePage.verifyDoneMessage()){
				Reporter.log(homePage.getDoneMessage(), true);
			}
			
			Assert.assertTrue( homePage.isOnHomePage());
			homePage.searchAndClickLastCreatedComputer();
			
			EditComputerPage editComp = new EditComputerPage();
			Assert.assertTrue( editComp.isOnEditComputerPage());
			editComp.updateComputer(computerName, introducedDate, discontinuedDate, company);
			wait(5000);
			if(homePage.verifyDoneMessage()){
				Reporter.log(homePage.getDoneMessage(), true);
			}
			
		}catch(Exception e){
			Assert.fail("Test Failed", e);
			e.printStackTrace();
		}
		
		
	}
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
