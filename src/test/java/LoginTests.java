import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void loginValidEmailPassword() throws InterruptedException {

        //navigateToPage();
        enterEmail("demo@testpro.io");
        enterPassword("te$t$tudentdsad");
        submit();
        //WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("img[class='avatar']")));
        //WebElement avatarIcon = (WebElement) fluentWait.until(ExpectedConditions.visibilityOfElementLocated
                //(By.cssSelector("img[class='avatar']")));
        // Expected Result
        Assert.assertTrue(avatarIcon.isDisplayed());
    }

    @Test
    public void loginValidEmailPasswordWithFluentWait() throws InterruptedException {

        //navigateToPage();
        enterEmail("demo@testpro.io");
        enterPassword("te$t$tudentdsad");
        submit();
        //WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("img[class='avatar']")));
        // Expected Result
        Assert.assertTrue(avatarIcon.isDisplayed());
    }

    @Test
    public void loginInvalidEmailValidPassword() throws InterruptedException {

        //navigateToPage();
        String expectedUrl = "https://qa.koel.app/";
        // Steps
        enterEmail("invalid@testpro.io");
        enterPassword("te$t$tudent");
        submit();

        Thread.sleep(2000); // Sleep or pause for 2 seconds (adjust as needed)
        // Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl); // https://qa.koel.app/
    }

    @Test
    public void loginValidEmailEmptyPassword() throws InterruptedException {

        //navigateToPage();
        String expectedUrl = "https://qa.koel.app/";
        enterEmail("invalid@testpro.io");
        submit();

        Thread.sleep(2000); // Sleep or pause for 2 seconds (adjust as needed)
        // Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl); //https://qa.koel.app/
    }

    @Test(dataProvider = "NegativeLoginTestData", dataProviderClass = TestDataProvider.class)
    public void negativeLoginTest(String email, String password) throws InterruptedException {
        String expectedUrl = "https://qa.koel.app/";
        enterEmail(email);
        enterPassword(password);
        submit();
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

    //Login Test using Page Object Model
    @Test
    public void positiveLoginTest(){
        //Objects
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        //Steps
        loginPage.provideEmail("demo@testpro.io");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();
        //loginPage.login();
        //Expected vs Actual
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Test(dataProvider = "NegativeLoginTestData", dataProviderClass = TestDataProvider.class)
    public void negativeLoginTests(String email, String password){
        String expectedUrl = "https://qa.koel.app/";
        //Objects
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        //Steps
        loginPage.provideEmail(email);
        loginPage.providePassword(password);
        loginPage.clickSubmit();
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }




}