package tests;

import utils.CustomLoggingListener;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(description = "Get chrome driver")
    public void beforeMethod() {
        driver = DriverFactory.getDriver(DriverFactory.Browser.GC);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new CustomLoggingListener());
        driver = eventFiringWebDriver;
    }

    @AfterMethod(description = "Close webdriver after test")
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
