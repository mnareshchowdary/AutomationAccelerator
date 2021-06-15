
package com.accelerator.helper.Wait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accelerator.helper.Generic.GenericHelper;
import com.accelerator.helper.Javascript.JavaScriptHelper;
import com.accelerator.helper.Logger.LoggerHelper;
import com.accelerator.interfaces.IconfigReader;
//import com.gk.test.framework.PageObject;
import com.google.common.base.Function;

//import lombok.Getter;

public class WaitHelper extends GenericHelper {
	private static final long DRIVER_WAIT_TIME = 10;
	// private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);

	protected WebDriverWait wait;

	private WebDriver driver;
	private IconfigReader reader;
//	private Logger oLog = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(WebDriver driver, IconfigReader reader) {
		super(driver);
		this.driver = driver;
		this.reader = reader;
//		oLog.debug("WaitHelper : " + this.driver.hashCode());
	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
//		oLog.debug("");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
//		oLog.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	public void waitForElementVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
//		oLog.info(locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
	}
	
	
	public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
            return  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
   //         LOG.info(e.getMessage());
            return null;
        } catch (TimeoutException e) {
   //         LOG.info(e.getMessage());
            return null;
        }
    }
	
	public WebElement waitForExpectedElement(WebElement element, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
            return  wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
   //         LOG.info(e.getMessage());
            return null;
        } catch (TimeoutException e) {
   //         LOG.info(e.getMessage());
            return null;
        }
    }
	
    public WebElement waitForExpectedElement(final By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    
    public WebElement waitForExpectedElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public Alert waitForExpectedAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

	public void hardWait(int timeOutInMiliSec) throws InterruptedException {
//		oLog.info(timeOutInMiliSec);
		Thread.sleep(timeOutInMiliSec);
	}

	public WebElement handleStaleElement(By locator, int retryCount, int delayInSeconds) throws InterruptedException {
//		oLog.info(locator);
		WebElement element = null;

		while (retryCount >= 0) {
			try {
				element = driver.findElement(locator);
				return element;
			} catch (StaleElementReferenceException e) {
				hardWait(delayInSeconds);
				retryCount--;
			}
		}
		throw new StaleElementReferenceException("Element cannot be recovered");
	}

	public void elementExits(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
//		oLog.info(locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(elementLocatedBy(locator));
		setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
	}

	public void elementExistAndVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
//		oLog.info(locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(elementLocatedBy(locator));
		new JavaScriptHelper(driver).scrollIntoView(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);

	}

	public void waitForIframe(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		// oLog.info(locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		driver.switchTo().defaultContent();
		setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
	}

	private Function<WebDriver, Boolean> elementLocatedBy(final By locator) {
		return new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
//				oLog.debug(locator);
				return driver.findElements(locator).size() >= 1;
			}
		};
	}

}
