package com.cucumber.framework.configreader;

import com.cucumber.framework.configuration.browser.BrowserType;
import com.cucumber.framework.utility.ResourceHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader implements ConfigReader {

    private final Properties prop=new Properties();

    public PropertyFileReader(){
        try(InputStream input=ResourceHelper.getResourcePathInputStream("config/config.properties")){
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to Lpoafd the config file",e);

        }
    }

    @Override
    public String getUserNamne() {
        return prop.getProperty("username");
    }

    @Override
    public String getPassword() {
        return prop.getProperty("password");
    }

    @Override
    public String getWebsite(String webApp) {
        return prop.getProperty("Test.url");
    }

    @Override
    public int getPageLoadTimeOut() {
        return Integer.parseInt(prop.getProperty("pageLoadTimeout"));
    }

    @Override
    public int getImplicitWait() {
        return Integer.parseInt(prop.getProperty("implicitWait"));
    }

    @Override
    public int getExplicitWait() {
        return Integer.parseInt(prop.getProperty("explicitWait"));
    }

    @Override
    public BrowserType getBrowser() {
        String browser= prop.getProperty("browser").toUpperCase();
        try {
            return BrowserType.valueOf(browser);
        } catch (IllegalArgumentException e) {
            return BrowserType.CHROME; // default fallback
        }
    }

    @Override
    public boolean isHeadless() {
        return Boolean.parseBoolean(prop.getProperty("headless","false"));
    }
}
