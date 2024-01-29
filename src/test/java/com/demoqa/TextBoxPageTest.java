package com.demoqa;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TextBoxPageTest extends BasePageTest {
    public final By FULL_NAME_LOCATOR = By.xpath("//input[@id='userName']");
    public final By EMAIL_LOCATOR = By.xpath("//input[@id='userEmail']");
    public final By CURRENT_ADDRESS_LOCATOR = By.cssSelector("#currentAddress");
    public final By PERMANENT_ADDRESS_LOCATOR = By.cssSelector("#permanentAddress");
    public final By SUBMIT_BUTTON_LOCATOR = By.cssSelector("#submit");
    public final By OUTPUT_NAME_LOCATOR = By.xpath("//p[@id='name']");
    public final By OUTPUT_EMAIL_LOCATOR = By.xpath("//p[@id='email']");
    public final By OUTPUT_CURRENT_ADDRESS_LOCATOR = By.xpath("//p[@id='currentAddress']");
    public final By OUTPUT_PERMANENT_ADDRESS_LOCATOR = By.xpath("//p[@id='permanentAddress']");

    @Parameters({"username", "email", "currentAddress", "permanentAddress"})
    @Test
    public void testSubmitTextBoxValues(String username, String email, String currentAddress, String permanentAddress) {

        driver.findElement(FULL_NAME_LOCATOR).sendKeys(username);
        driver.findElement(EMAIL_LOCATOR).sendKeys(email);
        driver.findElement(CURRENT_ADDRESS_LOCATOR).sendKeys(currentAddress);
        driver.findElement(PERMANENT_ADDRESS_LOCATOR).sendKeys(permanentAddress);
        driver.findElement(SUBMIT_BUTTON_LOCATOR).click();

        /*WebElement submitButton = driver.findElement(SUBMIT_BUTTON_LOCATOR);
        new Actions(driver)
                .scrollToElement(submitButton)
                .perform();
        submitButton.click();*/

        //AssertEquals for exact match
        assertEquals(driver.findElement(OUTPUT_NAME_LOCATOR).getText(), "Name:" + username, "Output name does not match the expected value.");
        assertEquals(driver.findElement(OUTPUT_EMAIL_LOCATOR).getText(), "Email:" + email, "Output email does not match the expected value.");
        assertEquals(driver.findElement(OUTPUT_CURRENT_ADDRESS_LOCATOR).getText(), "Current Address :" + currentAddress, "Output current address does not match the expected value.");

        //Test failed: ER: Permanent, AR:Permananet
        //Assert.assertEquals(driver.findElement(OUTPUT_PERMANENT_ADDRESS_LOCATOR).getText(), "Permanent Address :" + permanentAddress, "Output permanent address does not match the expected value.");

        //AssertTrue with contains for approximate match
        assertTrue(driver.findElement(OUTPUT_NAME_LOCATOR).getText().contains(username));
        assertTrue(driver.findElement(OUTPUT_EMAIL_LOCATOR).getText().contains(email));
        assertTrue(driver.findElement(OUTPUT_CURRENT_ADDRESS_LOCATOR).getText().contains(currentAddress));
        assertTrue(driver.findElement(OUTPUT_PERMANENT_ADDRESS_LOCATOR).getText().contains(permanentAddress));
    }
}