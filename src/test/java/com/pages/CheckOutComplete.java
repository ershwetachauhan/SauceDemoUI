package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutComplete {

    @FindBy(id = "back-to-products")
    private WebElement backBtn;

    public CheckOutComplete(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

