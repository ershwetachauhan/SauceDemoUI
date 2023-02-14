package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutOverview {

    @FindBy(css = ".inventory_item_name")
    private WebElement itemName;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement priceBeforeTax;

    @FindBy(css = ".summary_tax_label")
    private WebElement taxPrice;

    @FindBy(css = ".summary_total_label")
    private WebElement totalPrice;

    @FindBy(css = ".summary_value_label")
    private List<WebElement> paymentAndShippingInfo;

    public CheckOutOverview(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getPaymentInfo() {
        return paymentAndShippingInfo.get(0).getText();

    }

    public String getShippingInfo() {
        return paymentAndShippingInfo.get(1).getText();

    }


    public String getPriceBeforeTax() {
        return priceBeforeTax.getText();
    }

    public String getTaxPrice() {
        return taxPrice.getText();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void finishOrdering() {
        finishBtn.click();
    }

}
