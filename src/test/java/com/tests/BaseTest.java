package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterSuite
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}

