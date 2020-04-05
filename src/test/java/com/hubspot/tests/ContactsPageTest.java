package com.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.ContactsPage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;
import com.hubspot.util.ExcelUtil;

public class ContactsPageTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.gotoContactsPage();
		

	}
	
	@Test(priority=1)
	public void verifyContactsPage(){
		String title  = contactsPage.getConctactsPageTitle();
		System.out.println("contact page title is "+ title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider()
	public Object[][] getContactData(){
		Object data[][] = ExcelUtil.getTestData("contact");
		return data;
	}
	
	@Test(priority=2, dataProvider="getContactData")
	public void createNewPOMContactTest(String email, String firstname, String lastname, String jobtitle) throws InterruptedException{
		contactsPage.createNewConctact(email, firstname, lastname, jobtitle);
	}
	
	@AfterMethod
	public void tearDown(){
		basePage.quitBrowser();
	}

}
