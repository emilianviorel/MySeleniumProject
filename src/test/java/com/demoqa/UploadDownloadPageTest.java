package com.demoqa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class UploadDownloadPageTest extends BasePageTest {

    public final By UPLOAD_FILE_BUTTON = By.id("uploadFile");
    public final By UPLOADED_FILE_PATH = By.id("uploadedFilePath");
    public final By DOWNLOAD_FILE_BUTTON = By.id("downloadButton");
    public final String TXT_FILE_PATH = System.getProperty("user.dir")
            + "/src/test/resources/TestUpload.txt";

    @Test(priority = 1)
    public void testUploadFileButtonFunctionality() {

        driver.findElement(UPLOAD_FILE_BUTTON).sendKeys(TXT_FILE_PATH);
        Assert.assertTrue(driver.findElement(UPLOADED_FILE_PATH).isDisplayed());
    }

    @Test(priority = 2)
    public void testDownloadFileButtonFunctionality() {

        driver.findElement(DOWNLOAD_FILE_BUTTON).click();
        sleep(3000);

        String downloadFilePath = System.getProperty("user.home") + "/Downloads/sampleFile.jpeg";
        System.out.println(downloadFilePath);
        File downloadedFile = new File(downloadFilePath);
        Assert.assertTrue(downloadedFile.exists(), "Downloaded file does not exist");
    }
}
