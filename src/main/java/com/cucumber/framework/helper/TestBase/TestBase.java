package com.cucumber.framework.helper.TestBase;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.configuration.browser.HeadlessBrowser;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.utility.DateTimeHelper;
import com.cucumber.framework.utility.Excel;
import com.cucumber.framework.utility.ResourceHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.IOException;

import static com.cucumber.framework.configuration.browser.BrowserType.Headless;

public class TestBase {
    private final Logger log = LoggerHelper.getLogger(TestBase.class);

    public static WebDriver driver;

    public String path;
    public Excel excelsheet_data;

    public String takeScreenShot(String name) throws IOException {
        if(driver instanceof HtmlUnitDriver) {
            log.fatal("This is instance of HtmlUnitdriver  can not take the Screenshot");
            return "";
        }
        File destDir=new File(ResourceHelper.getResourcePath("screenshots/")+ DateTimeHelper.getCurrentDate());
        if(!destDir.exists())
            destDir.mkdir();
        File destPath=new File(destDir.getAbsolutePath() + System.getProperty("file.separator") + name + ".jpg");
        try {
            FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),destPath);
        } catch (IOException e){
            log.error(e);
            throw e;
        }
        log.info(destPath.getAbsolutePath());
        return destPath.getAbsolutePath();
    }

    public WebElement getElement(By locator){
       log.info(locator);
       if(IsElementPresentQuick(locator))
           return driver.findElement(locator);
       try {
           throw new NoSuchElementException("Element Not Found :"+locator);
       } catch (RuntimeException re){
           log.error(re);
           throw re;

       }
    }

    private boolean IsElementPresentQuick(By locator) {
        boolean flag=driver.findElements(locator).size() >=1;
        log.info(flag);
        return flag;
    }

    public WebDriver getBrowserObject(BrowserType bType) throws Exception {
    try{
        log.info(bType);

        switch (bType){

            case Headless:
                 HeadlessBrowser headlessBrowser =HeadlessBrowser.class.newInstance();
                 return headlessBrowser.getChromeDriver(headlessBrowser.getChromeCapabilities());

            default:
                throw new Exception("Driver Not Found :"+ new PropertyFileReader().getBrowser());

        }
    } catch (Exception e){
        log.equals(e);
        throw e;
    }

    }

}

