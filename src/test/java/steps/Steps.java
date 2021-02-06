package steps;

import io.qameta.allure.Step;
import org.junit.Assert;

public class Steps {
    @Step("Check that the results are returned after the search")
    public static void checkThatSearchResultsAreReturned(boolean actualResult) {
        Assert.assertFalse(actualResult);
    }

    @Step("Compare actual and expected results")
    public static void assertResults(String actualResult, String expectedResult) {
        Assert.assertEquals(actualResult, expectedResult);
    }
}
