package com.demoqa;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RadioButtonPageTest extends BasePageTest {
    public final By YES_LABEL_BUTTON_LOCATOR = By.xpath("//label[@for='yesRadio']");
    public final By YES_RADIO_BUTTON_LOCATOR = By.xpath("//input[@id='yesRadio']");
    public final By IMPRESSIVE_LABEL_BUTTON_LOCATOR = By.xpath("//label[@for='impressiveRadio']");
    public final By IMPRESSIVE_RADIO_BUTTON_LOCATOR = By.xpath("//input[@id='impressiveRadio']");
    public final By NO_LABEL_BUTTON_LOCATOR = By.xpath("//label[@for='noRadio']");
    public final By NO_RADIO_BUTTON_LOCATOR = By.xpath("//input[@id='noRadio']");
    public final By SUCCESS_TEXT_LOCATOR = By.xpath("//p[@class='mt-3']");

    @Test(priority = 1)
    void testYesRadioButton() {
        // Click Yes radio button only if not already selected
        if (!driver.findElement(YES_RADIO_BUTTON_LOCATOR).isSelected()) {
            driver.findElement(YES_LABEL_BUTTON_LOCATOR).click();
        }
        assertTrue(driver.findElement(YES_RADIO_BUTTON_LOCATOR).isSelected(), "Yes button is not selected");
        assertEquals(driver.findElement(SUCCESS_TEXT_LOCATOR).getText(), "You have selected Yes");

        if (driver.findElement(YES_RADIO_BUTTON_LOCATOR).isSelected()) {
            System.out.println(("The radio button is selected"));
        }
    }

    @Test(priority = 2)
    void testImpressiveRadioButton() {
        if (!driver.findElement(IMPRESSIVE_RADIO_BUTTON_LOCATOR).isSelected()) {
            driver.findElement(IMPRESSIVE_LABEL_BUTTON_LOCATOR).click();
        }
        assertEquals(driver.findElement(SUCCESS_TEXT_LOCATOR).getText(), "You have selected Impressive");
        assertTrue(driver.findElement(IMPRESSIVE_RADIO_BUTTON_LOCATOR).isSelected(), "Impressive button is not selected");
    }

    @Test(priority = 3)
    void testNoRadioButton() {
        if (!driver.findElement(NO_RADIO_BUTTON_LOCATOR).isSelected()) {
            driver.findElement(NO_LABEL_BUTTON_LOCATOR).click();
        }
        //Test failed: ER:true AR:false : No button is not selected
        assertTrue(driver.findElement(NO_RADIO_BUTTON_LOCATOR).isSelected(), "No button is not selected");
    }
}