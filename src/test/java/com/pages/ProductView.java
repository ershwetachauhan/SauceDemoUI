package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductView {
    @FindBy(css = ".inventory_details_name large_size")
    private WebElement productName;

    @FindBy(css = ".btn_inventory")
    private WebElement addOrRemoveBtn;

    public ProductView(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getBtnName(){
        return addOrRemoveBtn.getText();
    }

    public void getProductName(){
        productName.getText();
    }

    public void clickOnAddOrRemoveBtn(){
        addOrRemoveBtn.click();
    }

}
