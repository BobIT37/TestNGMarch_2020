package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;

public class ContactsPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//constructor
	public ContactsPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//Locators
	//Locators
		By createContactButton = By.xpath("//span[contains(text(), 'Create contact')]");
		By email = By.xpath("//input[@name='textInput']");
		By firstName = By.xpath("//input[@class='form-control private-form__control' and @data-field='firstname']");
		By lastName = By.xpath("//input[@class='form-control private-form__control' and @data-field='lastname']");
		By jobTitle = By.xpath("//input[@class='form-control private-form__control' and @data-field='jobtitle']");
		By createContactFormBtn = By.xpath("//div[@class='private-layer']//li[1]");
		
		public String getConctactsPageTitle(){
			return elementUtil.waitForGetPageTitle(Constants.CONTACTS_PAGE_TITLE);
		}
		
		public void createNewConctact(String emailID, String FN, String LN, String jobTitleVal) throws InterruptedException{
			
			elementUtil.doClick(createContactButton);
			Thread.sleep(2000);
			elementUtil.doSendKeys(email, emailID);
			Thread.sleep(2000);
			elementUtil.doSendKeys(firstName, FN);
			Thread.sleep(2000);
			elementUtil.doSendKeys(lastName, LN);
			Thread.sleep(2000);
			elementUtil.doSendKeys(jobTitle, jobTitleVal);
			Thread.sleep(2000);
			elementUtil.doClick(createContactFormBtn);
			Thread.sleep(2000);
			
		}
		
		
		
		
		

}
