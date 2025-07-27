package stepdefinations;

import com.cucumber.framework.helper.TestBase.TestBase;
import com.cucumber.framework.pageobjects.GoogleHomePage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSteps {

    private WebDriver driver;
    private GoogleHomePage google;
    private TestBase base = new TestBase(); // ✅ use same reference

    public GoogleSteps() {
        this.driver = base.getDriver();
    }

    @Given("user is on Google homepage")
    public void user_is_on_google_homepage() {
        if (driver == null) {
            throw new RuntimeException("Rahulll WebDriver is null. Ensure it is initialized in TestBase.");
        }
        google = new GoogleHomePage(driver);
        google.navigateToHomePage();
    }

    @When("user searches for {string}")
    public void user_searches_for(String query) {
        driver.findElement(By.name("q")).sendKeys(query + "\n");
    }

    @Then("page title should contain {string}")
    public void page_title_should_contain(String expectedTitle) {
        String title = driver.getTitle();
        Assert.assertTrue("Expected title to contain: " + expectedTitle, title.contains(expectedTitle));
    }
}
