package com.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BasePageTest {
    public WebDriver driver;

    @Parameters({"testPageUrl"})
    @BeforeTest
    public void driverSetup(String testPageUrl) {
        driver = new ChromeDriver();
        driver.get(testPageUrl);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
