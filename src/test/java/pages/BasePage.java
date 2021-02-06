package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Wait till page is loaded")
    public void waitForPageIsLoaded() {
        WebDriverWait waitForPageIsLoaded = new WebDriverWait(driver, 5);
        waitForPageIsLoaded.until(
                webDriver -> Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"),
                        "complete"));
    }

    public void waitForCityNameUpdated(By element) {
        WebDriverWait waitForUpdate = new WebDriverWait(driver, 5);
        waitForUpdate.until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
