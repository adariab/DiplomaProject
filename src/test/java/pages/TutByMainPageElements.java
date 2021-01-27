package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class TutByMainPageElements extends BasePage {

    public TutByMainPageElements(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static Logger LOGGER = LoggerFactory.getLogger(TutByMainPageElements.class);
    private String baseUrl = "https://www.tut.by/";
    @FindBy(id = "geotarget_top_selector")
    WebElement geoLocator;
    By cityName = By.xpath("//*[@id='geotarget_top_selector' ]//*[text()='Борисов']");
    @FindBy(xpath = "//a[text()='Борисов']")
    WebElement borisovCity;
    @FindBy(xpath = "//a[@title='Почта']")
    WebElement mailHyperlink;
    @FindBy(xpath = "//a[@title='Финансы']")
    WebElement financeHyperlink;
    @FindBy(xpath = "//a[@title='Афиша']")
    WebElement afishaHyperlink;
    @FindBy(xpath = "//a[@title='Работа']")
    WebElement jobsHyperlink;
    @FindBy(xpath = "//*[@class='forecast-icon']")
    WebElement weatherIcon;
    @FindBy(xpath = "//*[@class='weather']")
    WebElement temperatureIcon;
    @FindBy(xpath = "//a[@title='ТV-программа']")
    WebElement tvProgramHyperlink;
    @FindBy(id = "search_from_str")
    WebElement searchField;
    @FindBy(xpath = "//a[contains(text(),'tv')]")
    List<WebElement> searchResults;

    @Step("Open page")
    public void open() {
        driver.get(baseUrl);
    }

    @Step("Click on the geoLocator to specify the location")
    public void specifyGeoLocation() {
        LOGGER.info("Clicking geo location hyperlink...");
        geoLocator.click();
    }

    @Step("Select specific city in the list proposed, ex. Borisov")
    public void setSpecificCity() {
        LOGGER.info("Select specific city in the list proposed, ex. Borisov");
        borisovCity.click();
    }

    @Step("Extract the city name from the geo locator")
    public String CityNameSet() {
        LOGGER.info("Extract the city name from the geo locator...");
        return geoLocator.getText();
    }

    @Step("Click on the Почта hyperlink to open the mail service page")
    public void clickOnMailHyperlink() {
        LOGGER.info("Clicking mail hyperlink...");
        mailHyperlink.click();
    }

    @Step("Check the current URL.")
    public String getCurrentUrl() {
        LOGGER.info("The current URL is " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Click on the Финансы hyperlink to open Finance.tut.by page")
    public void clickOnFinanceHyperlink() {
        LOGGER.info("Clicking finance hyperlink...");
        financeHyperlink.click();
    }

    @Step("Click on the Афиша hyperlink to open afisha.tut.by page")
    public void clickOnAfishaHyperlink() {
        LOGGER.info("Clicking afisha hyperlink...");
        afishaHyperlink.click();
    }

    @Step("Click on the Работа hyperlink to open rabota.by page")
    public void clickOnJobsHyperlink() {
        LOGGER.info("Clicking Работа hyperlink...");
        jobsHyperlink.click();
    }

    @Step("Click on the weather icon to open pogoda.tut.by page")
    public void clickOnWeatherIcon() {
        LOGGER.info("Clicking Weather icon...");
        weatherIcon.click();
    }

    @Step("Click on the Работа hyperlink to open pogoda.tut.by/ page")
    public void clickOnTemperatureIcon() {
        LOGGER.info("Clicking Temperature icon...");
        temperatureIcon.click();
    }

    @Step("Click on the TV-program hyperlink to open tv-set page")
    public void clickOnTvProgramHyperlink() {
        LOGGER.info("Clicking TV-program hyperlink...");
        tvProgramHyperlink.click();
    }

    @Step("Wait till spinner gets disabled")
    public void waitForPageIsLoaded() {
        WebDriverWait waitForPageIsLoaded = new WebDriverWait(driver, 5);
        waitForPageIsLoaded.until(
                webDriver -> Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"),
                        "complete"));
    }

    public void waitForCityNameUpdated() {
        WebDriverWait waitForUpdate = new WebDriverWait(driver, 5);
        waitForUpdate.until(ExpectedConditions.presenceOfElementLocated(cityName));
    }

    @Step("Enter some text into a search field, ex. 'tv'")
    public void searchForSpecificText() {
        searchField.sendKeys("tv");
        searchField.sendKeys(Keys.ENTER);
    }

    @Step("Check that a list of search results is not empty")
    public boolean isListOfSearchResultsEmpty() {
        LOGGER.info("Is the list of results empty? - " + searchResults.isEmpty());
        return searchResults.isEmpty();
    }
}
