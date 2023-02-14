package com.pages;

import org.openqa.selenium.WebDriver;

public class PageRepo {

    public static LoginPage loginPage(WebDriver driver) {
        return new LoginPage(driver);
    }

    public static CheckOutPage checkOutPage(WebDriver driver) {
        return new CheckOutPage(driver);
    }

    public static CheckOutOverview checkOutOverview(WebDriver driver) {
        return new CheckOutOverview(driver);
    }

    public static CheckOutComplete checkOutComplete(WebDriver driver) {
        return new CheckOutComplete(driver);
    }

    public static Footer footer(WebDriver driver) {
        return new Footer(driver);
    }

    public static Header header(WebDriver driver) {
        return new Header(driver);
    }

    public static HomePage homePage(WebDriver driver) {
        return new HomePage(driver);
    }

    public static ProductCart productCart(WebDriver driver) {
        return new ProductCart(driver);
    }

    public static ProductView productView(WebDriver driver) {
        return new ProductView(driver);
    }

    public static LeftPanel leftPanel(WebDriver driver) {
        return new LeftPanel(driver);
    }


}
