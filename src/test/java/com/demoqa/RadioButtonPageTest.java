package com.demoqa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RadioButtonPageTest extends BasePageTest {
    public final By YES_RADIO_BUTTON_LOCATOR = By.xpath("//label[@for='yesRadio']");
    public final By IMPRESSIVE_RADIO_BUTTON_LOCATOR = By.xpath("//label[@for='impressiveRadio']");
    public final By SUCCESS_TEXT_LOCATOR = By.xpath("//p[@class='mt-3']");

    @Test
    void testYesAndImpressiveRadioButton() {
        // Click Yes radio button only if not already selected
        if (!driver.findElement(YES_RADIO_BUTTON_LOCATOR).isSelected()) {
            driver.findElement(YES_RADIO_BUTTON_LOCATOR).click();
        }
        Assert.assertEquals(driver.findElement(SUCCESS_TEXT_LOCATOR).getText(), "You have selected Yes");

        if (!driver.findElement(IMPRESSIVE_RADIO_BUTTON_LOCATOR).isSelected()) {
            driver.findElement(IMPRESSIVE_RADIO_BUTTON_LOCATOR).click();
        }
        Assert.assertEquals(driver.findElement(SUCCESS_TEXT_LOCATOR).getText(), "You have selected Impressive");
    }
}