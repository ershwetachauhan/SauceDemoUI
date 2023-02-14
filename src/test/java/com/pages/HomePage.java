package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {


    @FindBy(css = ".inventory_item")
    private List<WebElement> productBlock;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> titles;
    @FindBy(css = ".inventory_item_price")
    private List<WebElement> prices;

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getProductCount() {
        return productBlock.size();
    }

    public List<String> getProductTitles() {
        List<String> items = new ArrayList<>();
        for (WebElement webElement : titles) {
            items.add(webElement.getText());
        }

        return items;
    }

    public List<String> getProductPrices() {
        return prices.stream().map(x -> x.getText()).collect(Collectors.toList());
    }

    public List<Double> getProductPricesInNumber() {
        return this.getProductPrices().stream().map(x -> x.replace("$", "")).map(x -> Double.parseDouble(x)).collect(Collectors.toList());
    }

    public void addProductToCart(String productName) {
        String locater = "add-to-cart-" + productName.replaceAll(" ", "-").toLowerCase();
        driver.findElement(By.id(locater)).click();
    }

    public void removeProductToCart(String productName) {
        String locater = "remove-" + productName.replaceAll(" ", "-").toLowerCase();
        driver.findElement(By.id(locater)).click();
    }

    public void selectProductInProductView(String prd){
        titles.stream().filter(x->x.getText().equals(prd)).findFirst().ifPresent(x->x.click());
    }

}
