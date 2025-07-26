package com.cucumber.framework.configreader;

import com.cucumber.framework.configuration.browser.BrowserType;
import com.cucumber.framework.utility.ResourceHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader implements ConfigReader {
    private Properties prop=null;

    public PropertyFileReader(){
    prop=new Properties();
    try {
        prop.load(ResourceHelper.getResourcePathInputStream(""));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    @Override
    public String getUserNamne() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getWebsite(String webApp) {
        return null;
    }

    @Override
    public int getPageLoadTimeOut() {
        return 0;
    }

    @Override
    public int getImplicitWait() {
        return 0;
    }

    @Override
    public int getExplicitWait() {
        return 0;
    }

    @Override
    public BrowserType getBrowser() {
        return null;
    }
}
