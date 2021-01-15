package utils;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static java.lang.System.getProperty;

public class FirefoxDriverManager extends DriverManager {


    private GeckoDriverService ffService;
    private final String os = getProperty("os.name").toLowerCase();

    @Override
    public void startService() {
        if (null == ffService) {
            try {
                String driverPath;
                if (os.contains("win")) {
                    driverPath = "src/test/resources/webdrivers/win/geckodriver.exe";
                } else if (os.contains("mac")) {
                    driverPath = "src/test/resources/webdrivers/mac/geckodriver";
                } else {
                    driverPath = "src/test/resources/webdrivers/linux/geckodriver";
                }
                ffService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(driverPath)).usingAnyFreePort()
                        .build();
                ffService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != ffService && ffService.isRunning())
            ffService.stop();
    }

    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver(ffService, capabilities);
    }
}
