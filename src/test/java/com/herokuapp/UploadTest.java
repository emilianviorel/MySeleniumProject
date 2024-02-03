package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UploadTest {

    WebDriver driver;
    public final By FILE_UPLOAD = By.id("file-upload");
    public final By UPLOAD = By.className("button");
    public final By UPLOADED = By.xpath("//h3");
    public final String BASE_URL = "https://the-internet.herokuapp.com/upload";
    public final String TXT_FILE_PATH = System.getProperty("user.dir")
            + "/src/test/resources/TestUpload.txt";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFileUpload() {

        //String currentDirectory = System.getProperty("user.dir");
        //System.out.println("Current working directory: " + currentDirectory);
        //File filePath = new File("/src/test/resources/TestUpload.txt");

        driver.findElement(FILE_UPLOAD).sendKeys(TXT_FILE_PATH);
        driver.findElement(UPLOAD).click();

        Assert.assertTrue(driver.findElement(UPLOADED).isDisplayed());
        Assert.assertTrue(driver.findElement(UPLOADED).getText().contains("Uploaded!"));
    }
}