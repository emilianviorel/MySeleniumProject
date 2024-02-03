package com.demoqa;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitPageTest extends BasePageTest {

    public final By CLICK_2ND_BUTTON = By.id("timerAlertButton");
    public final By CLICK_3RD_BUTTON = By.id("confirmButton");
    public final By RESULTS_3RD_BUTTON = By.id("confirmResult");
    public final By CLICK_4TH_BUTTON = By.id("promtButton");
    public final By RESULTS_4TH_BUTTON = By.id("promptResult");


    @Test
    public void testAlertAppearAfter5Seconds() {

        driver.findElement(CLICK_2ND_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        //Wait for the alert to be displayed and store it in a variable
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        //Store the alert text in a variable
        String text = alert.getText();
        System.out.println(text);
        //Press the OK button
        alert.accept();

        Assert.assertEquals(text, "This alert appeared after 5 seconds");
    }

    @Test
    public void testConfirmButton() {
        driver.findElement(CLICK_3RD_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        //Wait for the alert to be displayed
        wait.until(ExpectedConditions.alertIsPresent());
        //Store the alert in a variable
        Alert alert = driver.switchTo().alert();
        //Store the alert in a variable for reuse
        String text = alert.getText();
        System.out.println(text);
        //Press the Cancel button
        //alert.dismiss();
        alert.accept();

        Assert.assertTrue(driver.findElement(RESULTS_3RD_BUTTON).getText().contains("Ok"));
    }

    @Test
    public void testPromptBox() {
        driver.findElement(CLICK_4TH_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        //Wait for the alert to be displayed and store it in a variable
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        //Type your message
        alert.sendKeys("Selenium");

        //Press the OK button
        alert.accept();

        Assert.assertTrue(driver.findElement(RESULTS_4TH_BUTTON).getText().contains("Selenium"));
    }
}
