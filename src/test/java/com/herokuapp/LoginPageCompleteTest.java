package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageCompleteTest {

    // WebDriver instance for interacting with the browser
    private WebDriver driver;

    // Constants for test data and locators
    private final String BASE_URL = "https://the-internet.herokuapp.com/login";
    private final String USERNAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";
    private final By USERNAME_LOCATOR = By.id("username");
    private final By PASSWORD_LOCATOR = By.id("password");
    private final By LOGIN_BUTTON_LOCATOR = By.className("radius");
    private final By FLASH_MESSAGE_LOCATOR = By.id("flash-messages");

    // Test data for invalid login attempts
    String inputUsername = "wrongUserName";
    String inputPassword = "wrongPassword";

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

    // Test login with an invalid username
    @Test(priority = 1)
    public void testLoginWithWrongUserName() {
        setupLoginInputs(inputUsername, PASSWORD);
        String flashMessage = getFlashMessage();
        Assert.assertTrue(flashMessage.contains("Your username is invalid!"));
    }

    // Test login with an invalid password
    @Test(priority = 2)
    public void testLoginWithWrongPassword() {
        setupLoginInputs(USERNAME, inputPassword);
        //String flashMessage = getFlashMessage();
        Assert.assertTrue(getFlashMessage().contains("Your password is invalid!"));
    }

    // Test login with correct credentials
    @Test(priority = 3)
    public void testLoginWithCorrectCredentials() {
        setupLoginInputs(USERNAME, PASSWORD);
        //String flashMessage = getFlashMessage();
        Assert.assertTrue(getFlashMessage().contains("You logged into a secure area!"));
    }
}
