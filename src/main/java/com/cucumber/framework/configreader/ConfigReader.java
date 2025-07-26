package com.cucumber.framework.configreader;

import com.cucumber.framework.configuration.browser.BrowserType;

public interface ConfigReader {
    public String getUserNamne();
    public String getPassword();
    public String getWebsite(String webApp);
    public int getPageLoadTimeOut();
    public int getImplicitWait();
    public int getExplicitWait();
    public BrowserType getBrowser();

    public boolean isHeadless();

}
