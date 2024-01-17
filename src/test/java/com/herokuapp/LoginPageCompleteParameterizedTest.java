package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPageCompleteParameterizedTest {

    // WebDriver instance for interacting with the browser
    private WebDriver driver;

    // Constants for test data and locators
    private final String BASE_URL = "https://the-internet.herokuapp.com/login";
    private final By USERNAME_LOCATOR = By.id("username");
    private final By PASSWORD_LOCATOR = By.id("password"); ///html//input[@id='password']
    private final By LOGIN_BUTTON_LOCATOR = By.className("radius");
    private final By FLASH_MESSAGE_LOCATOR = By.id("flash-messages");

    // Set up WebDriver and open the base URL before each test
    @Parameters({"browserP"})
    @BeforeTest
    public void setUp(String browserP) {
        //driver = new ChromeDriver();
        switch (browserP) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
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

    @Parameters({"inputUserNameP", "inputPasswordP", "flashMessageP"})
    @Test
    public void testLoginWithWrongAndCorrectCredentials(String inputUsernameP, String inputPasswordP, String flashMessageP) {
        setupLoginInputs(inputUsernameP, inputPasswordP);
        Assert.assertTrue(getFlashMessage().contains(flashMessageP));
    }
}