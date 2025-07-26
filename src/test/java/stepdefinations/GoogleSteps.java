package stepdefinations;
import com.cucumber.framework.helper.TestBase.TestBase;
import com.cucumber.framework.pageobjects.GoogleHomePage;
import io.cucumber.java.en.*;
import org.junit.Assert;

    public class GoogleSteps {

        TestBase base = new TestBase();
        GoogleHomePage google;

        @Given("user is on Google homepage")
        public void user_is_on_google_homepage() {
            google = new GoogleHomePage(base.getDriver());
            google.navigateToHomePage();
        }

        @When("user searches for {string}")
        public void user_searches_for(String query) {
            google.enterSearchText(query);
        }

        @Then("page title should contain {string}")
        public void page_title_should_contain(String expectedTitle) {
            Assert.assertTrue(google.getPageTitle().toLowerCase().contains(expectedTitle.toLowerCase()));
        }
    }


