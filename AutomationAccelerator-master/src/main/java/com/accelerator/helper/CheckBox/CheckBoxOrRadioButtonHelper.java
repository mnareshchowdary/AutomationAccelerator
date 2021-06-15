
package com.accelerator.helper.CheckBox;

//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.accelerator.helper.Logger.LoggerHelper;
import com.accelerator.interfaces.IwebComponent;
import com.accelerator.helper.Wait.WaitHelper;


public class CheckBoxOrRadioButtonHelper implements IwebComponent {
	com.accelerator.helper.Wait.WaitHelper waithelper;
	
	private WebDriver driver;
//	private Logger oLog = LoggerHelper.getLogger(CheckBoxOrRadioButtonHelper.class);

	public CheckBoxOrRadioButtonHelper(WebDriver driver) {
		this.driver = driver;
//		oLog.debug("CheckBoxOrRadioButtonHelper : " + this.driver.hashCode());
	}
	
	public void selectCheckBox(By locator) {
//		oLog.info(locator);
		waithelper.waitForExpectedElement(driver.findElement(locator)).click();
	//	selectCheckBox(driver.findElement(locator));
	}
	
	public void unSelectCheckBox(By locator) {
//		oLog.info(locator);
		waithelper.waitForExpectedElement(driver.findElement(locator)).click();
	//	unSelectCheckBox(driver.findElement(locator));
	}
	
	public boolean isIselected(By locator) {
//		oLog.info(locator);
		waithelper.waitForExpectedElement(driver.findElement(locator)).isSelected();
	return isIselected(driver.findElement(locator));
	}
	
	public boolean isIselected(WebElement element) {
		//boolean flag = element.isSelected();
		boolean flag = waithelper.waitForExpectedElement(element).isSelected();
	//	oLog.info(flag);
		return flag;
	}
	
	public void selectCheckBox(WebElement element) {
		if(!isIselected(element))
			waithelper.waitForExpectedElement(element).click();
		//	element.click();
//		oLog.info(element);
	}
	
	public void unSelectCheckBox(WebElement element) {
		if(isIselected(element))
			waithelper.waitForExpectedElement(element).click();
		//	element.click();
//		oLog.info(element);
	}
}
