
package com.accelerator.helper.Button;

//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.accelerator.helper.Logger.LoggerHelper;
import com.accelerator.interfaces.IwebComponent;
import com.accelerator.helper.Wait.WaitHelper;



public class ButtonHelper implements IwebComponent {
	com.accelerator.helper.Wait.WaitHelper waithelper;
	
	private WebDriver driver;
//	private Logger oLog = LoggerHelper.getLogger(ButtonHelper.class);
	
	public ButtonHelper(WebDriver driver) {
		this.driver = driver;
//		oLog.debug("Button Helper : " + this.driver.hashCode());
	}
	
	public void click(By locator) {
		waithelper.waitForExpectedElement(driver.findElement(locator)).click();
	//	click(driver.findElement(locator));
//		oLog.info(locator);
	}
	
	public void click(WebElement element){
		//com.accelerator.helper.Wait.WaitHelper.waitForElementVisible(element,30,5);
		waithelper.waitForExpectedElement(element).click();
	//		oLog.info(element);
	}
}
