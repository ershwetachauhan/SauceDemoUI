package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
    @FindBy(id = "first-name")
    private WebElement fnameLabel;

    @FindBy(id = "last-name")
    private WebElement lnameLabel;
    @FindBy(id = "postal-code")
    private WebElement postCodeLabel;

    @FindBy(id = "continue")
    private WebElement continueBtn;
    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    @FindBy(css = ".error-message-container h3")
    private WebElement errorMessage;

    public CheckOutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setCustDetails(String fname, String lname, String postCode) {
        fnameLabel.sendKeys(fname);
        lnameLabel.sendKeys(lname);
        postCodeLabel.sendKeys(postCode);
    }

    public void proceedWithOrderFromCheckOutPage(){
        continueBtn.click();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }


}
