package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DropdownPageTest {
    WebDriver driver;
    WebElement dropdown;
    WebElement option1;
    WebElement option2;
    final String BASE_URL = "https://the-internet.herokuapp.com/dropdown";
    final By DROPDOWN_LOCATOR = By.id("dropdown");
    final By OPTION_1_LOCATOR = By.xpath("//option[@value=\"1\"]");
    final By OPTION_2_LOCATOR = By.xpath("//option[@value=\"2\"]");

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        dropdown = driver.findElement(DROPDOWN_LOCATOR);
        option1 = driver.findElement(OPTION_1_LOCATOR);
        option2 = driver.findElement(OPTION_2_LOCATOR);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testSelectFromDropdown() {
        Select dropdownElement = new Select(dropdown);

        dropdownElement.selectByValue("1");
        Assert.assertTrue(option1.isSelected());

        dropdownElement.selectByVisibleText("Option 2");
        Assert.assertTrue(option2.isSelected());
    }
}
