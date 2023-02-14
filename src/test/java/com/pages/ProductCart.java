package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCart {

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingBtn;
    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(css= ".inventory_item_name")
    private List<WebElement> itemsInCart;

    public ProductCart(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getItemsInCart(){
        return itemsInCart.stream().map(element->element.getText()).collect(Collectors.toList());
    }

    public void setCheckoutBtn(){
        checkoutBtn.click();
    }


}
