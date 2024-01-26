package com.demoqa;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CheckBoxPageTest extends BasePageTest {
    public final By HOME_CHECKBOX_SVG_LOCATOR = By.xpath("//span[@class='rct-checkbox']//*[name()='svg']");
    public final By HOME_CHECKBOX_LOCATOR = By.xpath("//input[@id='tree-node-home']");
    public final By RESULTS_LOCATOR = By.xpath("//div[@id='result']");
    public final String[] SELECTED_FOLDER = {"home", "desktop", "notes", "commands", "documents",
            "workspace", "react", "angular", "veu", "office", "public", "private",
            "classified", "general", "downloads", "wordFile", "excelFile"};

    @Test
    void testAllCheckboxesAreChecked() {

        if (!driver.findElement(HOME_CHECKBOX_SVG_LOCATOR).isSelected()) {
            driver.findElement(HOME_CHECKBOX_SVG_LOCATOR).click();
        }
        assertTrue(driver.findElement(HOME_CHECKBOX_SVG_LOCATOR).getAttribute("class").contains("check"), "Home checkbox is not selected.");
        assertTrue(driver.findElement(RESULTS_LOCATOR).isDisplayed(), "Results are not displayed.");
        assertTrue(driver.findElement(HOME_CHECKBOX_LOCATOR).isSelected());

        for (String folder : SELECTED_FOLDER) {
            assertTrue(driver.findElement(RESULTS_LOCATOR).getText().contains(folder), "Folder'" + folder + "' not selected.");
        }
    }
}