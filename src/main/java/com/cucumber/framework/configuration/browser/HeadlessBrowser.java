package com.cucumber.framework.configuration.browser;

import com.cucumber.framework.utility.ResourceHelper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class HeadlessBrowser {
    public Capabilities getChromeCapabilities(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        DesiredCapabilities chrome=DesiredCapabilities.chrome();
        chrome.setJavascriptEnabled(true);
        chrome.setCapability(ChromeOptions.CAPABILITY,options);
        return chrome;
    }
    public WebDriver getChromeDriver(Capabilities cap){

        if (System.getProperty("os.name").contains("Mac")){
            System.setProperty("wedriver.chrome.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/chromedriver.exe"));
           return new ChromeDriver(cap);
        }
        else if(System.getProperty("os.name").contains("Window")){
            System.setProperty("webdriver.chrome.driver","");
        }
        return null;
    }

}
