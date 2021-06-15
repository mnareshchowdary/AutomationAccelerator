package com.accelerator.factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public WebDriver driver;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public static ThreadLocal<RemoteWebDriver> wtlDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize the threadlocal driver on the basis of given
     * browser
     *
     * @param browser
     * @return this will return tldriver.
     * @throws MalformedURLException 
     */
    public WebDriver init_driver(String browser,String run_env) throws MalformedURLException {

        System.out.println("browser value is: " + browser);

        if (browser.equals("chrome")) {
           if (run_env.equals("local"))  {
        		   WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());  }
           else {
        	   DesiredCapabilities chrome = DesiredCapabilities.chrome();
        	   URL  url = new URL("http://localhost:4444/wd/hub");
        	  RemoteWebDriver rc = new RemoteWebDriver(url,chrome);
        	   wtlDriver.set(rc);
           }
        	   
        } else if (browser.equals("firefox")) {
        	if (run_env.equals("local")){
                WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver());}
        else {
     	   DesiredCapabilities ff = DesiredCapabilities.firefox();
     	   URL  url = new URL("http://localhost:4444/wd/hub");
     	   RemoteWebDriver rc = new RemoteWebDriver(url,ff);
     	  wtlDriver.set(rc);
        }

        } else if (browser.equals("safari")) {
            tlDriver.set(new SafariDriver());
        } else {
            System.out.println("Please pass the correct browser value: " + browser);
        }

          
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();

    }

    /**
     * this is used to get the driver with ThreadLocal
     *
     * @return
     */
    public static synchronized WebDriver getDriver() {
         return    tlDriver.get();
    }
    	    		
    
    
    
}