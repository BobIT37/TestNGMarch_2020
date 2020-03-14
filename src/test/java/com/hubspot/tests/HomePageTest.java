package com.hubspot.tests;

import java.util.Properties;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;

public class HomePageTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=1, description="home age title")
	public void verifyHomePageTitle(){
		String title = homePage.getHomePageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2, description="home page header")
	public void verifyHomePageHeader(){
		String header = homePage.getHomePageHeader();
		System.out.println(header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3, description="home page account verify")
	public void verifyAccounName(){
		String accountName = homePage.verifyLoggedInAccountName();
		System.out.println(accountName);
		Assert.assertEquals(accountName, prop.get("accountName"));
	}
	
	@AfterMethod
	public void tearDown(){
		basePage.quitBrowser();
	}
	
	

}
