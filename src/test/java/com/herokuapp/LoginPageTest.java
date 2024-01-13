package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/* TestData
//https://the-internet.herokuapp.com/login
//*[@id="username"]
//*[@id="password"]
//*[@id="login"]/button/i
*/
public class LoginPageTest {

    public final String USERNAME = "tomsmith";
    public final String PASSWORD = "SuperSecretPassword!";
    public final String LOGIN_URL = "https://the-internet.herokuapp.com/login";
    public final String SECURE_URL = "https://the-internet.herokuapp.com/secure";

    @Test
    public void loginPositiveTest() {

        //1.Open webpage FormAuthenticator
        System.out.println("Open webpage FormAuthenticator");
        WebDriver driver = new ChromeDriver();
        driver.get(LOGIN_URL);
        driver.manage().window().maximize();

        System.out.println("Wait 3 seconds");
        sleep(3000);

        //2.Click username & enter user : tomsmith
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(USERNAME);
        sleep(2000);

        //3.Click password & enter password:
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(PASSWORD);

        //4.Click login button.
        driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();

        //5.Expected result: "Welcome to the Secure Area" is displayed
        WebElement secureAreaSubHeader = driver.findElement(By.className("subheader"));
        String subHeaderContent = "Welcome to the Secure Area. When you are done click logout below.";

        Assert.assertTrue(secureAreaSubHeader.isDisplayed());
        Assert.assertEquals(subHeaderContent, secureAreaSubHeader.getText());

        Assert.assertEquals(driver.getCurrentUrl(), SECURE_URL);

        WebElement successMessage = driver.findElement(By.id("flash-messages"));
        String successMessageContent = "You logged into a secure area!";

        Assert.assertTrue(successMessage.getText().contains(successMessageContent));

        //secureAreaSubHeader.isDisplayed();

        //6.Close webpage
        System.out.println("Close page");
        driver.close();
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

