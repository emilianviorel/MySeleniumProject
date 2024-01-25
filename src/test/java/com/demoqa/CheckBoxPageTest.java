package com.demoqa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxPageTest extends BasePageTest {
    public final By HOME_CHECKBOX_LOCATOR = By.xpath("//label[@for='tree-node-home']//span[@class='rct-checkbox']//*[name()='svg']");
    //public final By HOME_CHECKBOX_LOCATOR = By.className("rct-checkbox");
    public final By RESULTS_LOCATOR = By.xpath("//div[@id='result']");

    @Test
    void testAllCheckboxesAreChecked() {

        if (!driver.findElement(HOME_CHECKBOX_LOCATOR).isSelected()) {
            driver.findElement(HOME_CHECKBOX_LOCATOR).click();
        }
        Assert.assertTrue(driver.findElement(HOME_CHECKBOX_LOCATOR).getAttribute("class").contains("check"), "Home checkbox is not selected.");
        Assert.assertTrue(driver.findElement(RESULTS_LOCATOR).isDisplayed(), "Results are not displayed.");
    }
}