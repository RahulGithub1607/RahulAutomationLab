package stepdefinations;

import com.cucumber.framework.helper.TestBase.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    private TestBase base =new TestBase();
    private Scenario scenario;

    @Before
    public void beforeScenario(){
        System.out.println(">>> [Hooks] Before Scenario - initializing driver");
        // This will initilaize the driver using config + Driverfactory
        base.initializeDriver();

    }
    @After
    public void afterScenario() {
        if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) base.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Screenshot");
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        base.quitDriver();
    }

    }

