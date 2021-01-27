package tests;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pages.TutByMainPageElements;

public class TutByMainPageElementsTest extends BaseTest {

    private static Logger LOGGER = LoggerFactory.getLogger(TutByMainPageElementsTest.class);

    @Test(description = "Check the default location")
    public void defaultLocationTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        checkTheResults(page.CityNameSet(), "Минск");
        LOGGER.info("Expected result: Минск");
    }

    @Test(description = "Specify a particular geo location test")
    public void geoLocationTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.specifyGeoLocation();
        page.setSpecificCity();
        page.waitForCityNameUpdated();
        checkTheResults(page.CityNameSet(), "Борисов");
        LOGGER.info("Expected city name is Борисов");
    }

    @Step("Compare actual and expected results")
    public void checkTheResults(String actualResult, String expectedResult) {
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(description = "Open mail form from the main page")
    public void mailFormOpeningTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.clickOnMailHyperlink();
        checkTheResults(page.getCurrentUrl(), "https://mail.tut.by/");
        LOGGER.info("Mail service is opened. Expected URL is https://mail.tut.by/");
    }

    @Test(description = "Open finance.tut.by page from the main page")
    public void financeTutByPageOpeningTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.clickOnFinanceHyperlink();
        checkTheResults(page.getCurrentUrl(), "https://finance.tut.by/");
        LOGGER.info("Finance page is opened. Expected URL is https://finance.tut.by/");
    }

    @Test(description = "Open afisha.tut.by page from the main page")
    public void afishaTutByPageOpeningTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.clickOnAfishaHyperlink();
        checkTheResults(page.getCurrentUrl(), "https://afisha.tut.by/");
        LOGGER.info("Afisha page is opened. Expected URL is https://afisha.tut.by/");
    }

    @Test(description = "Open rabota.by page from the main page")
    public void rabotaByPageOpeningTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.clickOnJobsHyperlink();
        page.waitForPageIsLoaded();
        checkTheResults(page.getCurrentUrl(), "https://rabota.by/#ua:top_menu_www.tut.by~12");
        LOGGER.info("Rabota.by page is opened. Expected URL is https://rabota.by/#ua:top_menu_www.tut.by~12");
    }

    @Test(description = "Open pogoda.tut.by page from the main page via weather icon")
    public void pogodaByPageOpeningViaWeatherIconTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.clickOnWeatherIcon();
        checkTheResults(page.getCurrentUrl(), "https://pogoda.tut.by/");
        LOGGER.info("Pogoda page is opened. Expected URL is https://pogoda.tut.by/");
    }

    @Test(description = "Open pogoda.tut.by page from the main page via temperature icon")
    public void pogodaByPageOpeningViaTemperatureIconTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.clickOnTemperatureIcon();
        checkTheResults(page.getCurrentUrl(), "https://pogoda.tut.by/");
        LOGGER.info("Pogoda page is opened. Expected URL is https://pogoda.tut.by/");
    }

    @Test(description = "Open tv-set page from the main page via TV-program hyperlink")
    public void tvSetPageOpeningViaTvProgramTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.clickOnTvProgramHyperlink();
        page.waitForPageIsLoaded();
        checkTheResults(page.getCurrentUrl(), "https://tvset.tut.by/");
        LOGGER.info("TV-set page is opened. Expected URL is https://tvset.tut.by/");
    }

    @Test(description = "Search field test")
    public void searchForSpecificTextTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.searchForSpecificText();
        checkTheResults(page.getCurrentUrl(), "https://search.tut.by/?status=1&ru=1&encoding=1&page=0&how=rlv&query=tv");
        LOGGER.info("The following request should be reflected in the url - ''query=tv");
    }

    @Test(description = "Check the search results")
    public void listOfSearchResultsTest() {
        TutByMainPageElements page = new TutByMainPageElements(driver);
        page.open();
        page.searchForSpecificText();
        checkThatSearchResultsAreReturned(page.isListOfSearchResultsEmpty());
        LOGGER.info("Expected result: The list of search results returned shouldn't be empty");
    }

    @Step("Check that the results are returned after the search")
    public void checkThatSearchResultsAreReturned(boolean actualResult) {
        Assert.assertFalse(actualResult);
    }
}
