import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{

    String newPlaylistName = "Sample Edited Playlist";

    @Test
    public void renamePlaylist(){

        String updatedPlaylistMsg = "Updated playlist \"Sample Edited Playlist.\"";
        //Steps
        enterEmail("demo@testpro.io");
        enterPassword("te$t$tudent");
        submit();
        doubleClickPlaylist();
        enterNewPlaylistName();
        //Assertions
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), updatedPlaylistMsg);

    }

    public void enterNewPlaylistName() {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public void doubleClickPlaylist() {
        WebElement playListElement = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(".playlist:nth-child(7)")));
        actions.doubleClick(playListElement).perform();
    }

    public String getRenamePlaylistSuccessMsg(){
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        return notification.getText();
    }

}
