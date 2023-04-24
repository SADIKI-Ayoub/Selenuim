package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginPage extends BasePage{
    //********* Web Elements by using Page Factory *********
    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id="pass")
    public WebElement password;

    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public LoginPage(WebDriver webDriver) {
        //this.webDriver = webDriver;
        //this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        //PageFactory.initElements(webDriver,  this);
        init(webDriver);
    }

    //********* Web Elements by using By Class *********
    By loginButtonBy          = By.id("loginbutton");
    By errorMessageUsernameBy = By.xpath("/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/form/div/div[1]/div[2]");
    By errorMessagePasswordBy = By.xpath("//*[@id=\"loginForm\"]/div[2]/div/div");
    By errorMessagePasswordCssBy = By.cssSelector("div[data-errormessagefor='password'] > .errorText");

    //*********Page Methods*********

    public void login1(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
    }

    public LoginPage login(String userName, String password) {
        writeText(this.email, userName);
        writeText(this.password, password);
        jsClick(loginButtonBy);
        return this;
    }

    public LoginPage verifyLoginUserNameErrorMessage(String expectedText) {
        assertEquals(expectedText, readText(errorMessageUsernameBy));
        return this;
    }

    public LoginPage verifyPasswordErrorMessage(String expectedText) {
        assertEquals(expectedText, readText(errorMessagePasswordBy));
        return this;
    }

    public LoginPage verifyPasswordErrorMessageWithCss(String expectedText) throws InterruptedException {
        assertEquals(expectedText, readTextErrorMessage(errorMessagePasswordCssBy));
        return this;
    }

    public LoginPage verifyLogEntryFailMessage() {
        logUtil.isLoginErrorLog(driver);
        return this;
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.email.isDisplayed());
    }
}
