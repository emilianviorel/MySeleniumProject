package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KeyPressTest {
    WebDriver driver;
    public final By INPUT_KEY = By.xpath("//*[@class='no-js']"); // //input[@id='target']
    public final By RESULT = By.xpath("//p[@id='result']");
    public final String BASE_URL = "https://the-internet.herokuapp.com/key_presses";

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
    public void testInputKeyResult() {

        driver.findElement(INPUT_KEY).sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.findElement(RESULT).getText().contains("You entered: ENTER"));

        driver.findElement(INPUT_KEY).sendKeys(Keys.SPACE);
        Assert.assertTrue(driver.findElement(RESULT).getText().contains("You entered: SPACE"));

    }
}