package pages;

import com.google.common.net.InternetDomainName;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import propertiesReader.ConfProperties;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    private String pageUrl = "https://www.tut.by/";

    @FindBy(xpath = "//a[@data-target-popup='authorize-form']")
    WebElement authorisedFormIcon;

    @FindBy(xpath = "//input[@name='login']")
    WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//input[@value='Войти']")
    WebElement enterButton;

    @FindBy(xpath = "//*[@id='authorize']//*[@class='uname']")
    WebElement usernameSignedIn;

    @FindBy(xpath = "//a[@class='button wide auth__reg'][text()='Выйти']")
    WebElement signOutButton;

    @FindBy(xpath = "//*[@id='authorize']//*[@class='social__btn social__btn--fb']")
    WebElement fbLoginIcon;

    @FindBy(id = "email")
    WebElement fbUsername;

    @FindBy(id = "pass")
    WebElement fbPassword;

    @FindBy(id = "loginbutton")
    WebElement fbLoginButton;

    @FindBy(name = "__CONFIRM__")
    WebElement fbConfirmButton;

    @Step("Open page")
    public void open() {
        driver.get(pageUrl);
        authorisedFormIcon.click();
    }

    @Step("Set username")
    public void setUsername() {
        username.sendKeys(ConfProperties.getProperty("username"));
        LOGGER.info("Set the username - " + ConfProperties.getProperty("username"));
    }

    @Step("Set password")
    public void setPassword() {
        password.sendKeys(ConfProperties.getProperty("password"));
        LOGGER.info("Set the username - " + ConfProperties.getProperty("password"));
    }

    @Step("Click on the 'Войти' button")
    public void clickOnEnterButton() {
        enterButton.click();
    }

    public String getTheButtonName() {
        LOGGER.info("The button name is changed to " + driver.getCurrentUrl());
        return authorisedFormIcon.getText();
    }

    @Step("Get the username set after signing in")
    public String getUsernameSigned() {
        LOGGER.info("The following user was loged in " + usernameSignedIn.getText());
        return usernameSignedIn.getText();
    }

    //TODO will remove waitForPageIsLoaded method after previous pr with waiters moved to base class is merged
    @Step("Wait for Page is loaded")
    public void waitForPageIsLoaded() {
        WebDriverWait waitForPageIsLoaded = new WebDriverWait(driver, 5);
        waitForPageIsLoaded.until(
                webDriver -> Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"),
                        "complete"));
    }

    @Step("The whole sign in process")
    public void signIn() {
        open();
        setUsername();
        setPassword();
        clickOnEnterButton();
    }

    @Step("Sign out")
    public void ClickOnSignOut() {
        usernameSignedIn.click();
        signOutButton.click();
    }

    @Step("Open Facebook login page")
    public void redirectToFacebookLoginPage() {
        fbLoginIcon.click();
    }

    @Step("Check the current URL.")
    public String getCurrentHostname() throws MalformedURLException {
        URL aURL = new URL(driver.getCurrentUrl());
        return InternetDomainName.from(aURL.getHost()).topPrivateDomain().toString();
    }

    @Step("Login via Facebook account")
    public void loginViaFacebookAccount() {
        fbUsername.sendKeys(ConfProperties.getProperty("fbUsername"));
        fbPassword.sendKeys(ConfProperties.getProperty("fbPassword"));
        fbLoginButton.click();
        if (isFbConfirmButtonExist()) {
            fbConfirmButton.click();
        }
    }

    private boolean isFbConfirmButtonExist() {
        try {
            fbConfirmButton.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
