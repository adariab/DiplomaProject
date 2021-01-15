package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chService;
    private final String os = System.getProperty("os.name").toLowerCase();

    @Override
    public void startService() {
        if (null == chService) {
            try {
                String driverPath;
                if (os.contains("win")) {
                    driverPath = "src/test/resources/webdrivers/win/chromedriver.exe";
                } else if (os.contains("mac")) {
                    driverPath = "src/test/resources/webdrivers/mac/chromedriver";
                } else {
                    driverPath = "src/test/resources/webdrivers/linux/chromedriver";
                }
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(driverPath))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(chService, capabilities);
    }
}
