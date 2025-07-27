package com.cucumber.framework.PageObject;

import org.openqa.selenium.WebDriver;

public class GoogleHomePage {
    WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.get("https://www.google.com");
    }
}
