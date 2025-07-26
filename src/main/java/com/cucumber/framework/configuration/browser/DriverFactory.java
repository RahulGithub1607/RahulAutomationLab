package com.cucumber.framework.configuration.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public WebDriver createInstance(BrowserType browserType,boolean isHeadless){
    switch (browserType){
        case CHROME:
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions=new ChromeOptions();
            if (isHeadless){
                chromeOptions.addArguments("--headless=new\", \"--window-size=1920,1080");
            }
            chromeOptions.addArguments("--disable-notifications", "--start-maximized");
            return new ChromeDriver(chromeOptions);
        case FIREFOX:
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (isHeadless) {
                firefoxOptions.addArguments("--headless");
            }
            return new FirefoxDriver(firefoxOptions);

        case EDGE:
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            if (isHeadless) {
              //  edgeOptions.addArguments("--headless");
                System.out.println("Edge is not headless in framework");
            }
            return new EdgeDriver(edgeOptions);

        default:
            throw new IllegalArgumentException("Unsupported browser: " + browserType);
    }
    }

    }



