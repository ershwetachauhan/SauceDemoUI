package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "user-name")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container h3")
    private WebElement errorMessage;
    @FindBy(css = ".error-message-container")
    private WebElement errorContainer;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public boolean isErrorDisplayed() {
        return errorContainer.isDisplayed();

    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void login(String username, String passWord) {
        userName.sendKeys(username);
        password.sendKeys(passWord);
        loginButton.click();
    }


}
