package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.UUID;

public class CustomLoggingListener extends AbstractWebDriverEventListener {
    private File screenshotsLocation = new File("target/");
    private String SCREENSHOT_LOCATION = screenshotsLocation.getAbsolutePath();
    public static Logger LOGGER = LoggerFactory.getLogger(CustomLoggingListener.class);

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        String messageId = UUID.randomUUID().toString();
        LOGGER.info(messageId + " : Navigating to [" + url + "] with driver [" + driver + "]");
        takeScreenShot(messageId, driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        String messageId = UUID.randomUUID().toString();
        LOGGER.info(messageId + " : Try to locate element using [" + by + "] ");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOGGER.info("WebDriver click on element - " + element);
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        String messageId = UUID.randomUUID().toString();
        LOGGER.info(messageId + " : The text is [" + text + "]");
        takeScreenShot(messageId, driver);
    }

    private void takeScreenShot(String name, WebDriver driver) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileChannel srcChannel = new FileInputStream(src).getChannel();
            File dst = new File(SCREENSHOT_LOCATION, name + ".png");
            FileChannel dstChannel = new FileOutputStream(dst).getChannel();
            dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
