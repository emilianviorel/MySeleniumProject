package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BasePageTest {
    public WebDriver driver;
    public final By CONSENT = By.xpath("//button[@aria-label='Consent']");

    @Parameters({"browser", "testPageUrl"})
    @BeforeTest
    public void driverSetup(String browser, String testPageUrl) {

        switch (browser) {
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        driver.get(testPageUrl);
        driver.manage().window().maximize();

        try {
            if (driver.findElement(CONSENT).isDisplayed()) {
                driver.findElement(CONSENT).click();
            }
        } catch (Exception error) {
            throw new RuntimeException("No Consent button");
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}