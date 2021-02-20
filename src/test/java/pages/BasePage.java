package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForCityNameUpdated(By element) {
        WebDriverWait waitForUpdate = new WebDriverWait(driver, 5);
        waitForUpdate.until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
