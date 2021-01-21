package tests;

import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    @BeforeMethod(description = "Get chrome driver")
    public void beforeMethod() {
        driver = DriverFactory.getDriver(DriverFactory.Browser.GC);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod(description = "Close webdriver after test")
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}