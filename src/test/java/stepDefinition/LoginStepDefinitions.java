package stepDefinition;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;

import java.time.Duration;

public class LoginStepDefinitions {

    WebDriver driver;
    WebDriverWait wait;

    //@Given("I open browser")
    @Before
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }

    @And("I open Login Page")
    public void iOpenLoginPage() {
        driver.get("https://qa.koel.app/");
    }

    @When("I enter email {string}")
    public void iEnterEmail(String email) {
        /*wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[type='email']"))).sendKeys(email);*/
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email);
    }

    @And("I enter Password {string}")
    public void iEnterPassword(String password) {
        /*wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[type='password']"))).sendKeys(password);*/
        LoginPage loginPage = new LoginPage(driver);
        loginPage.providePassword(password);
    }


    @And("I submit")
    public void iSubmit() {
        /*wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[type='submit']"))).click();*/
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("img.avatar"))).isDisplayed());
    }

    @Then("I should not get logged in")
    public void iShouldNotLogIn() {
        String expectedUrl = "https://qa.koel.app/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
}
