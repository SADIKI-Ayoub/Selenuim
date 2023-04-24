package pages;

import Utils.LogUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected JavascriptExecutor javascriptExecutor;

    protected LogUtil logUtil;

    public void init(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        javascriptExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isAt();

    public <T> void waitElement(T elementAttr) {
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            wait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        }
    }

    public <T> void waitElements(T elementAttr) {
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) elementAttr));
        }
    }

    //Click Method by using JAVA Generics (You can use both By or Web element)
    public <T> void click(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            driver
                    .findElement((By) elementAttr)
                    .click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    //Write Text by using JAVA Generics (You can use both By or WebElement)
    public <T> void writeText(T elementAttr, String text) {
        waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            driver
                    .findElement((By) elementAttr)
                    .sendKeys(text);
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    //Read Text by using JAVA Generics (You can use both By or WebElement)
    public <T> String readText(T elementAttr) {
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            return driver
                    .findElement((By) elementAttr)
                    .getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }

    public <T> String readTextErrorMessage(T elementAttr) throws InterruptedException {
        Thread.sleep(5000); //This needs to be improved.
        return driver
                .findElement((By) elementAttr)
                .getText();
    }

    //Close popup if exists
    public void handlePopup(By by) throws InterruptedException {
        waitElements(by);
        List<WebElement> popup = driver.findElements(by);
        if (!popup.isEmpty()) {
            popup
                    .get(0)
                    .click();
            Thread.sleep(200);
        }
    }
}
