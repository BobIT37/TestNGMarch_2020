package com.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hubspot.base.BasePage;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Short;

public class ElementUtil extends BasePage{
	
	WebDriver driver;
	
	//Constructor
	public ElementUtil (WebDriver driver){
		this.driver = driver;
	}
	
	public WebElement getElement(By locator){
		waitForElementPresentBy(locator);
		
		WebElement element = null;
		try{
		element = driver.findElement(locator);
		if(flash.equalsIgnoreCase("yes")){
		JavaScriptUtil.flash(element, driver);	
		}
		} catch (Exception e) {
			System.out.println("some exception occured while creating webelement "+ locator);
		}
		return element;
	}
	
	public void waitForElementPresentBy(By locator){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void doClick(By locator){
		try{
		getElement(locator).click();
		}
		catch(Exception e){
			System.out.println("Some exception occured while click on  webelement " +locator);
		}
	}

	public void doSendKeys(By locator, String value){
		try{
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
		}
		catch(Exception e){
			System.out.println("Some exception occured while sending to  webelement " + locator);
		}
	}
	
	public String doGetText(By locator){
		String text = null;
		try {
			text = getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception occured while get text to webelement "+ locator);
		}
		return text;
	}
	
	public String waitForGetPageTitle(String title){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	public boolean isElementDsiplayed(By locator){
		try{
			return getElement(locator).isDisplayed();
		}catch (Exception e) {
			System.out.println("some exception occured while checking webelement displayed "+ locator);
			return false;
		}
	}

}

