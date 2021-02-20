package steps;

import io.qameta.allure.Step;
import org.junit.Assert;

public class Steps {
    @Step("Compare actual and expected results")
    public static void assertResults(String actualResult, String expectedResult) {
        Assert.assertEquals(actualResult, expectedResult);
    }
}
