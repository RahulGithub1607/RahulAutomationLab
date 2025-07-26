package com.cucumber.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {
    private WebDriver driver;

    private By searchBox = By.name("q");

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.get("https://www.google.com");
    }

    public void enterSearchText(String text) {
        driver.findElement(searchBox).sendKeys(text);
        driver.findElement(searchBox).submit();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
