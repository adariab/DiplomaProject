package utils;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.github.bonigarcia.wdm.managers.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static WebDriver driver = null;
    private static Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

    public enum Browser {
        FF("firefox"),
        GC("chrome"),
        IE("internet explorer");
        private String value;
        Browser(String value) {
            this.value = value;
        }
    }

    public static WebDriver getDriver(Browser browser) {
        switch (browser) {
            case FF:
                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driver = new FirefoxDriver();
                break;
            case IE:
                InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER).arch32().setup();
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                caps.setCapability("ignoreZoomSetting", true);
                caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
                driver = new InternetExplorerDriver(caps);
                break;
            case GC:
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(options);
                break;
            default:
                LOGGER.info("Wrong browser has been selected.");
        }
        LOGGER.info("Selected Browser: " + browser.value);
        return driver;
    }
}
