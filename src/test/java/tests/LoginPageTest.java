package tests;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.net.MalformedURLException;

public class LoginPageTest extends BaseTest {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginPageTest.class);

    private LoginPage page() {
        return new LoginPage(driver);
    }
    @Test(description = "SignIn")
    public void signIn() {
        page().open();
        page().setUsername();
        page().setPassword();
        page().clickOnEnterButton();
        page().waitForPageIsLoaded();
        assertResults(page().getUsernameSigned(), "Darya Test");
        LOGGER.info("Expected result: Darya Test");
    }
//TODO will remove assertResult method after previous pr with Steps class is merged
    @Step("Compare actual and expected results")
    public void assertResults(String actualResult, String expectedResult) {
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(description = "SignIn")
    public void signOut() {
        page().signIn();
        page().clickOnSignOut();
        assertResults(page().getTheButtonName(), "Войти");
        LOGGER.info("User logged out and the button name was changed to the default value 'Войти'");
    }

    @Test(description = "Checking redirection to the Facebook login page")
    public void openFacebookLoginPageTest() throws MalformedURLException {
        page().open();
        page().redirectToFacebookLoginPage();
        assertResults(page().getCurrentHostname(), "facebook.com");
    }

    @Test(description = "Login via Facebook account")
    public void loginViaFacebookAccountTest() {
        page().open();
        page().redirectToFacebookLoginPage();
        page().loginViaFacebookAccount();
        assertResults(page().getUsernameSigned(), "Darya Baranovich");
    }
}