package com.cucumber.framework.helper.TestBase;

import com.cucumber.framework.configreader.ConfigReader;
import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.configuration.browser.BrowserType;
import com.cucumber.framework.configuration.browser.DriverFactory;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.utility.DateTimeHelper;
import com.cucumber.framework.utility.Excel;
import com.cucumber.framework.utility.ResourceHelper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;



public class TestBase {

    private static final Logger log = LoggerHelper.getLogger(TestBase.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public String path;
    public Excel excelsheet_data;

    // Set the driver instance in ThreadLocal
    public void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Get current thread’s driver
    public WebDriver getDriver() {
        return driver.get();
    }

    // Initialize driver from config (browser + headless)
    public void initializeDriver() {
        ConfigReader config = new PropertyFileReader();
        BrowserType browser = config.getBrowser();
        boolean isHeadless = config.isHeadless();

        DriverFactory factory = new DriverFactory();
        WebDriver driverInstance = factory.createInstance(browser, isHeadless);
        setDriver(driverInstance);
    }

    // Gracefully quit the driver
    public void quitDriver() {
        getDriver().quit();
        driver.remove();
    }

    // Capture screenshot with timestamped file
    public String takeScreenshot(String name) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String filePath = ResourceHelper.getResourcePath("screenshots/")
                + DateTimeHelper.getCurrentDate() + "/" + name + ".png";
        try {
            File dest = new File(filePath);
            dest.getParentFile().mkdirs(); // ensures parent directories exist
            FileUtils.copyFile(srcFile, dest);
            log.info("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            log.error("Screenshot capture failed", e);
            throw new RuntimeException(e);
        }
        return filePath;
    }

    // Safe way to get element with logging and exception
    public WebElement getElement(By locator) {
        log.info("Locating element: " + locator);
        if (isElementPresentQuick(locator)) {
            return getDriver().findElement(locator);
        }
        throw new NoSuchElementException("Element not found: " + locator);
    }

    // Quick check for element presence
    private boolean isElementPresentQuick(By locator) {
        boolean exists = getDriver().findElements(locator).size() >= 1;
        log.info("Element present: " + exists);
        return exists;
    }

}

