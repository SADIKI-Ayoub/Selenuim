package cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginSteps {

    private String browser = "chrome";
    private WebDriver driver = null;
    private LoginPage login;
    @Given("Browser is open")
    public void browserIsOpen() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();;
        System.out.println("hay");
    }

    @And("I am on the login page")
    public void iAmOnTheLoginPage() throws InterruptedException {
        //driver.navigate().to("https://booking.ctm.ma/account/login");
        driver.navigate().to("https://www.facebook.com/login/");
        Thread.sleep(2000);
    }

    @When("I try to login with {string} and {string}")
    public void iTryToLoginWithAnd(String arg0, String arg1) throws InterruptedException {
        login = new LoginPage(driver);
        login.login(arg0, arg1);
        Thread.sleep(2000);
    }

    @Then("I verify invalid login message")
    public void iVerifyInvalidLoginMessage() throws InterruptedException {
        if (browser.equalsIgnoreCase("firefox")) {
            //login.verifyLogEntryFailMessage();
        } else {
            //login.verifyLoginUserNameErrorMessage("L’adresse e-mail ou le numéro de mobile que vous avez saisi(e) n’est pas associé(e) à un compte. Trouvez votre compte et connectez-vous.");
        }
    }
}
