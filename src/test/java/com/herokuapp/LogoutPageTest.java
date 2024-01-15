package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LogoutPageTest {

    // WebDriver instance for interacting with the browser
    private WebDriver driver;

    // Constants for test data and locators
    private final String BASE_URL = "https://the-internet.herokuapp.com/login";
    private final String USERNAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";
    private final By USERNAME_LOCATOR = By.id("username");
    private final By PASSWORD_LOCATOR = By.id("password"); ///html//input[@id='password']
    private final By LOGIN_BUTTON_LOCATOR = By.className("radius");
    private final By LOGOUT_BUTTON_LOCATOR = By.xpath("//a[@class ='button secondary radius']");
    private final By FLASH_MESSAGE_LOCATOR = By.id("flash-messages");

    // Set up WebDriver and open the base URL before each test
    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        sleep(3000);
    }

    // Close WebDriver after each test
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    // Utility method to introduce a pause in milliseconds
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Set up login inputs and perform a login attempt
    private void setupLoginInputs(String inputUsername, String inputPassword) {
        driver.findElement(USERNAME_LOCATOR).sendKeys(inputUsername);
        driver.findElement(PASSWORD_LOCATOR).sendKeys(inputPassword);
        driver.findElement(LOGIN_BUTTON_LOCATOR).click();
        sleep(2000);
    }

    // Get the text of the flash message
    private String getFlashMessage() {
        return driver.findElement(FLASH_MESSAGE_LOCATOR).getText();
    }

    // Test logout message
    @Test(priority = 1)
    public void testLogoutMessage() {
        setupLoginInputs(USERNAME, PASSWORD);
        driver.findElement(LOGOUT_BUTTON_LOCATOR).click();
        Assert.assertTrue(getFlashMessage().contains("You logged out of the secure area!"));
        //Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
    }
}