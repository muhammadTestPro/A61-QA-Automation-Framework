import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllSongsTest extends BaseTest{

    @Test
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
    }

    public void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("li.playback"))).click();
    }

    public void contextClickFirstSong() {
        WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        actions.contextClick(firstSongElement).perform();
    }

    public void chooseAllSongsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("li a.songs"))).click();
    }




}
