package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pages.LoginPage;
import steps.Steps;

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
        Steps.assertResults(page().getUsernameSigned(), "Darya Test");
        LOGGER.info("Expected result: Darya Test");
    }

    @Test(description = "SignIn")
    public void signOut() {
        page().signIn();
        page().clickOnSignOut();
        Steps.assertResults(page().getTheButtonName(), "Войти");
        LOGGER.info("User logged out and the button name was changed to the default value 'Войти'");
    }

    @Test(description = "Checking redirection to the Facebook login page")
    public void openFacebookLoginPageTest() throws MalformedURLException {
        page().open();
        page().redirectToFacebookLoginPage();
        Steps.assertResults(page().getCurrentHostname(), "facebook.com");
    }

    @Test(description = "Login via Facebook account")
    public void loginViaFacebookAccountTest() {
        page().open();
        page().redirectToFacebookLoginPage();
        page().loginViaFacebookAccount();
        Steps.assertResults(page().getUsernameSigned(), "Darya Baranovich");
    }
}