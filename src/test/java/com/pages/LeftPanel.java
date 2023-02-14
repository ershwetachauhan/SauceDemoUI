package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeftPanel {

    WebDriver driver;

    @FindBy(id="inventory_sidebar_link")
    private WebElement allItemsBtn;

    @FindBy(id="about_sidebar_link")
    private WebElement aboutBtn;

    @FindBy(id="logout_sidebar_link")
    private WebElement logOutBtn;

    @FindBy(id="reset_sidebar_link")
    private WebElement resetBtn;

    @FindBy(id="react-burger-menu-btn")
    private WebElement leftPanelOpenBtn;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement crossBtn;


    public LeftPanel(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void resetApp(){
        leftPanelOpenBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(crossBtn));
        resetBtn.click();
    }
}
