package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Header {

    WebDriver driver;
    @FindBy(css = ".product_sort_container")
    private WebElement dropDownFilter;

    @FindBy(css = ".shopping_cart_link")
    private WebElement shoppingCart;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement shoppingCartProductCount;

    @FindBy(css = ".title")
    private WebElement headerTitle;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFilter(String option) {
        dropDownFilter.click();
        Select select = new Select(dropDownFilter);
        select.selectByVisibleText(option);
    }

    public String getProductCountInCart() {
        return shoppingCart.getText();
    }

    public void goTocart() {
        shoppingCart.click();
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

//    public boolean isProductCountDisplayed() {
////        WebDriverWait wait = new WebDriverWait(driver, 15);
////        wait.until(ExpectedConditions.invisibilityOfAllElements(shoppingCart));
//        if (shoppingCartProductCount == null) {
//            return false;
//        }
//        return shoppingCart.isDisplayed();
//    }

}
