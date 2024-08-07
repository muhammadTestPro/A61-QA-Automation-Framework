import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomeTest extends BaseTest{

    String newPlaylistName = "Sample Edited Playlist";

    @Test
    public void hoverPlayButton() throws InterruptedException {
        //login
        enterEmail("demo@testPro.io");
        enterPassword("te$t$tudent");
        submit();
        Thread.sleep(2000);
        //verify Play or Resume button is visible with mouse hover
        Assert.assertTrue(hoverPlay().isDisplayed());
    }

    @Test
    public void countSongsInPlaylist(){
        //login
        enterEmail("demo@testPro.io");
        enterPassword("te$t$tudent");
        submit();
        //choosePlaylistByName
        choosePlaylistByName("TestPro Playlist");
        //displayAllSongs
        displayAllSongs();
        System.out.println("number of Songs: "+countSongs());
        //Assertion - contains the # of songs as mentioned in the playlist info section
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
    }


    @Test
    public void renamePlaylist() throws InterruptedException {
        String expectedSuccessMsg = "Updated playlist \"Sample Edited Playlist.\"";
        //Login
        enterEmail("demo@testPro.io");
        enterPassword("te$t$tudent");
        submit();
        Thread.sleep(2000);
        //doubleClickPlaylist
        doubleClickPlaylist();
        Thread.sleep(2000);
        //enter new name
        enterNewPlaylistName();
        Thread.sleep(2000);
        //Assert that new name has been updated.
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), expectedSuccessMsg);

    }

    public void doubleClickPlaylist() {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(".playlist:nth-child(6)")));
        actions.doubleClick(playlistElement).perform();
    }

    public void enterNewPlaylistName(){
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getRenamePlaylistSuccessMsg(){
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        return notification.getText();
    }


    public void choosePlaylistByName(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(text(),'"+playlistName+"')]"))).click();
    }

    public int countSongs(){
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }

    public String getPlaylistDetails(){
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }

    public void displayAllSongs(){
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of songs found: "+countSongs());
        for (WebElement e: songList){
            System.out.println(e.getText());
        }
    }

}
