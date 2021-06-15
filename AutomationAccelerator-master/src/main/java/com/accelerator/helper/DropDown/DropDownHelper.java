
package com.accelerator.helper.DropDown;

import java.util.LinkedList;
import java.util.List;

//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.accelerator.helper.Generic.GenericHelper;
import com.accelerator.helper.Logger.LoggerHelper;
import com.accelerator.helper.Wait.WaitHelper;


public class DropDownHelper extends GenericHelper {
	com.accelerator.helper.Wait.WaitHelper waithelper;
	private WebDriver driver;
//	private Logger oLog = LoggerHelper.getLogger(DropDownHelper.class);

	public DropDownHelper(WebDriver driver) {
		super(driver);
		this.driver = driver;
	//	oLog.debug("DropDownHelper : " + this.driver.hashCode());
	}
	
	public void SelectUsingVisibleValue(By locator,String visibleValue) {
		waithelper.waitForExpectedElement(driver.findElement(locator));
		SelectUsingVisibleValue(getElement(locator),visibleValue);
	}
	
	public void SelectUsingVisibleValue(WebElement element,String visibleValue) {
		waithelper.waitForExpectedElement(element);
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
//		oLog.info("Locator : " + element + " Value : " + visibleValue);
	}
	
	public void SelectUsingValue(By locator,String value) {
		waithelper.waitForExpectedElement(driver.findElement(locator));
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
//		oLog.info("Locator : " + locator + " Value : " + value);
	}
	
	public void SelectUsingIndex(By locator,int index) {
		waithelper.waitForExpectedElement(driver.findElement(locator));
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
//		oLog.info("Locator : " + locator + " Index : " + index);
	}
	
	public String getSelectedValue(By locator) {
		waithelper.waitForExpectedElement(driver.findElement(locator));
//		oLog.info(locator);
		return getSelectedValue(getElement(locator));
	}
	
	public String getSelectedValue(WebElement element) {
		waithelper.waitForExpectedElement(element);
		String value = new Select(element).getFirstSelectedOption().getText();
//		oLog.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	
	public List<String> getAllDropDownValues(By locator) {
		waithelper.waitForExpectedElement(driver.findElement(locator));
		Select select = new Select(getElement(locator));
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
//			oLog.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
}
