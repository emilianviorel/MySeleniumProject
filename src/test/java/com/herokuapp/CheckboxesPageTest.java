package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckboxesPageTest {
    WebDriver driver;
    WebElement checkbox1;
    WebElement checkbox2;
    final String BASE_URL = "https://the-internet.herokuapp.com/checkboxes";
    final By CHECKBOX_1_LOCATOR = By.xpath("//*[@id=\"checkboxes\"]/input[1]");
    final By CHECKBOX_2_LOCATOR = By.xpath("//*[@id=\"checkboxes\"]/input[2]");


    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        checkbox1 = driver.findElement(CHECKBOX_1_LOCATOR);
        checkbox2 = driver.findElement(CHECKBOX_2_LOCATOR);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testAllCheckboxesAreChecked() {
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }
        if (!checkbox2.isSelected()) {
            checkbox2.click();
        }
        //Assert.assertTrue(checkbox1.isSelected() && checkbox2.isSelected());
        Assert.assertTrue(checkbox1.isSelected());
        Assert.assertTrue(checkbox2.isSelected());
    }

    @Test
    public void testAllCheckboxesAreUnchecked() {
        if (checkbox1.isSelected()) {
            checkbox1.click();
        }
        if (checkbox2.isSelected()) {
            checkbox2.click();
        }
        //Assert.assertFalse(checkbox1.isSelected() && checkbox2.isSelected());
        Assert.assertFalse(checkbox1.isSelected());
        Assert.assertFalse(checkbox2.isSelected());
    }
}
