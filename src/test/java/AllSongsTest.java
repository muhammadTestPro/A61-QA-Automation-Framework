import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllSongsPage;
import pages.HomePage;
import pages.LoginPage;

public class AllSongsTest extends BaseTest{

    /*@Test
    public void playSong() throws InterruptedException {
        //login
        enterEmail("demo@testPro.io");
        enterPassword("te$t$tudent");
        submit();
        //Thread.sleep(2000);
        //choose AllSongs list
        chooseAllSongsList();
        //Thread.sleep(2000);
        //contextClickFirstSong
        contextClickFirstSong();
        //Thread.sleep(2000);
        //ChoosePlayOption
        choosePlayOption();
        //Thread.sleep(2000);
        //verify that song is playing
        Assert.assertTrue(isSongPlaying());
    }*/


    @Test
    public void playSongWithContextClick() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongsPage = new AllSongsPage(driver);

        loginPage.login();
        homePage.chooseAllSongsList();
        homePage.contextClickFirstSong();
        allSongsPage.choosePlayOption();
        //verify that song is playing
        Assert.assertTrue(allSongsPage.isSongPlaying());
    }











}
