package steps;

import excel.ExcelReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.SauceDemoLoginPage;
import tests.BaseTest;

import java.io.IOException;
import java.util.Map;

public class MainSteps extends BaseTest {
    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String quit = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("quit");
    Map<String, String> data;

    @Before
    public void setup () throws Exception {
        init(browser);
    }

    @After
    public void tearDown(){
        if(quit.equalsIgnoreCase("Yes")){
            quit();
        }
    }

    @Given("I load test data from {string} {string} {string}")
    public void iLoadTestDataFrom(String file, String sheet, String row) throws IOException {
        data = new ExcelReader().getRowData(file,sheet,Integer.parseInt(row));
    }

    @Given("I load test data from {string} {string} for {string}")
    public void iLoadTestDataFromFor(String file, String sheet, String tc_id) throws IOException {
        data = new ExcelReader().getRowDataByID(file, sheet, tc_id);
    }

    @Given("I am on the sauce demo page")
    public void iAmOnTheSauceDemoPage() {
        driver.get("https://www.saucedemo.com/");
    }

//    @When("I enter my username")
//    public void iEnterMyUsername() throws Exception {
//        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(driver);
//        sauceDemoLoginPage.enterUsername("standard_user");
//    }

    @When("I enter my username")
    public void iEnterMyUsername() throws Exception {
        new SauceDemoLoginPage(driver).enterUsername(data.get("username"));
    }

    @When("I enter my username {string}")
    public void iEnterMyUsername(String username) throws Exception {
        new SauceDemoLoginPage(driver).enterUsername(username);
    }

//    @And("I enter my password")
//    public void iEnterMyPassword() throws Exception {
//        new SauceDemoLoginPage(driver).enterPassword("secret_sauce");
//    }

    @And("I enter my password")
    public void iEnterMyPassword() throws Exception {
        new SauceDemoLoginPage(driver).enterPassword(data.get("password"));
    }

    @And("I enter my password {string}")
    public void iEnterMyPassword(String password) throws Exception {
        new SauceDemoLoginPage(driver).enterPassword(password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() throws Exception {
        new SauceDemoLoginPage(driver).clickLogin();
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        new SauceDemoLoginPage(driver).menuShouldBeVisible();
    }

    @And("I should be able to see products")
    public void iShouldBeAbleToSeeProducts() {
        new SauceDemoLoginPage(driver).titleShouldBeVisible();
    }

    @Then("I should get an error message {string}")
    public void iShouldGetAnErrorMessage(String errorMessage) {
        new SauceDemoLoginPage(driver).verifyErrorMessage(errorMessage);
    }

}
