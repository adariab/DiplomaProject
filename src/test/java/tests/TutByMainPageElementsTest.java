package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pages.TutByMainPageElements;
import steps.Steps;

public class TutByMainPageElementsTest extends BaseTest {

    private static Logger LOGGER = LoggerFactory.getLogger(TutByMainPageElementsTest.class);

    private String searchText = "tv";
    private String cityName = "Борисов";

    private TutByMainPageElements page() {
        return new TutByMainPageElements(driver);
    }

    @Test(description = "Check the default location")
    public void defaultLocationTest() {
        page().open();
        Steps.assertResults(page().getCityNameSet(), "Минск");
        LOGGER.info("Expected result: Минск");
    }

    @Test(description = "Specify a particular geo location test")
    public void geoLocationTest() {
        page().open();
        page().specifyGeoLocation();
        page().setSpecificCity();
        Steps.assertResults(page().getCityNameSet(), cityName);
        LOGGER.info("Expected city name is " + cityName);
    }

    @Test(description = "Open mail form from the main page")
    public void mailFormOpeningTest() {
        page().open();
        page().clickOnMailHyperlink();
        Steps.assertResults(page().getCurrentUrl(), "https://mail.tut.by/");
        LOGGER.info("Mail service is opened. Expected URL is https://mail.tut.by/");
    }

    @Test(description = "Open finance.tut.by page from the main page")
    public void financeTutByPageOpeningTest() {
        page().open();
        page().clickOnFinanceHyperlink();
        Steps.assertResults(page().getCurrentUrl(), "https://finance.tut.by/");
        LOGGER.info("Finance page is opened. Expected URL is https://finance.tut.by/");
    }

    @Test(description = "Open afisha.tut.by page from the main page")
    public void afishaTutByPageOpeningTest() {
        page().open();
        page().clickOnAfishaHyperlink();
        Steps.assertResults(page().getCurrentUrl(), "https://afisha.tut.by/");
        LOGGER.info("Afisha page is opened. Expected URL is https://afisha.tut.by/");
    }

    @Test(description = "Open rabota.by page from the main page")
    public void rabotaByPageOpeningTest() {
        page().open();
        page().clickOnJobsHyperlink();
        page().waitForPageIsLoaded();
        Steps.assertResults(page().getCurrentUrl(), "https://rabota.by/#ua:top_menu_www.tut.by~12");
        LOGGER.info("Rabota.by page is opened. Expected URL is https://rabota.by/#ua:top_menu_www.tut.by~12");
    }

    @Test(description = "Open pogoda.tut.by page from the main page via weather icon")
    public void pogodaByPageOpeningViaWeatherIconTest() {
        page().open();
        page().clickOnWeatherIcon();
        Steps.assertResults(page().getCurrentUrl(), "https://pogoda.tut.by/");
        LOGGER.info("Pogoda page is opened. Expected URL is https://pogoda.tut.by/");
    }

    @Test(description = "Open pogoda.tut.by page from the main page via temperature icon")
    public void pogodaByPageOpeningViaTemperatureIconTest() {
        page().open();
        page().clickOnTemperatureIcon();
        Steps.assertResults(page().getCurrentUrl(), "https://pogoda.tut.by/");
        LOGGER.info("Pogoda page is opened. Expected URL is https://pogoda.tut.by/");
    }

    @Test(description = "Open tv-set page from the main page via TV-program hyperlink")
    public void tvSetPageOpeningViaTvProgramTest() {
        page().open();
        page().clickOnTvProgramHyperlink();
        page().waitForPageIsLoaded();
        Steps.assertResults(page().getCurrentUrl(), "https://tvset.tut.by/");
        LOGGER.info("TV-set page is opened. Expected URL is https://tvset.tut.by/");
    }

    @Test(description = "Search field test")
    public void searchForSpecificTextTest() {
        page().open();
        page().searchForSpecificText();
        Steps.assertResults(page().getCurrentUrl(),
                "https://search.tut.by/?status=1&ru=1&encoding=1&page=0&how=rlv&query=" + searchText);
        LOGGER.info("The following request should be reflected in the url - 'query=" + searchText + "'");
    }

    @Test(description = "Check the search results")
    public void listOfSearchResultsTest() {
        page().open();
        page().searchForSpecificText();
        Steps.checkThatSearchResultsAreReturned(page().isListOfSearchResultsEmpty());
        LOGGER.info("Expected result: The list of search results returned shouldn't be empty");
    }
}
