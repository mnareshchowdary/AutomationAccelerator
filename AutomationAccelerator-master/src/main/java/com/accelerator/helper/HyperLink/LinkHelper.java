
package com.accelerator.helper.HyperLink;

//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.accelerator.helper.Generic.GenericHelper;
import com.accelerator.helper.Logger.LoggerHelper;



public class LinkHelper extends GenericHelper {

	private WebDriver driver;
//	private Logger oLog = LoggerHelper.getLogger(LinkHelper.class);

	public LinkHelper(WebDriver driver) {
		super(driver);
		this.driver = driver;
//		oLog.debug("LinkHelper : " + this.driver.hashCode());
	}
	
	public void clickLink(String linkText) {
//		oLog.info(linkText);
		getElement(By.linkText(linkText)).click();
	}
	
	public void clickPartialLink(String partialLinkText) {
//		oLog.info(partialLinkText);
		getElement(By.partialLinkText(partialLinkText)).click();
	}
	
	public String getHyperLink(By locator){
//		oLog.info(locator);
		return getHyperLink(getElement(locator));
	}
	
	public String getHyperLink(WebElement element) {
		String link = element.getAttribute("hreg");
	//	oLog.info("Element : " + element + " Value : " + link);
		return link;
	}
	
	public boolean getHyperLink_visible(WebElement element) {
		boolean link_visibility = element.isDisplayed();
	//	oLog.info("Element : " + element + " Value : " + link);
		return link_visibility;
	}
}
