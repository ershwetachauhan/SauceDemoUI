package com.tests;

import com.pages.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class OrderTest extends BaseTest {
    private Header header;
    private HomePage hp;
    private LoginPage loginPage;
    private LeftPanel leftPanel;
    private ProductCart productCart;
    private CheckOutPage checkOutPage;
    private CheckOutOverview checkOutOverview;
    private ProductView productView;

    @BeforeMethod
    public void setUpTest() {
        driver.get(BASE_URL);
        loginPage = PageRepo.loginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        hp = PageRepo.homePage(driver);
        header = PageRepo.header(driver);
        leftPanel = PageRepo.leftPanel(driver);
        productCart = PageRepo.productCart(driver);
        checkOutPage = PageRepo.checkOutPage(driver);
        checkOutOverview = PageRepo.checkOutOverview(driver);
        productView = PageRepo.productView(driver);
    }

    @AfterMethod
    public void resetCart() {
        leftPanel.resetApp();

    }

    @Test
    public void orderProductCountTest() {
        hp.addProductToCart("Sauce Labs Backpack");
        hp.addProductToCart("Sauce Labs Bolt T-Shirt");
        hp.addProductToCart("Test.allTheThings() T-Shirt (Red)");
        Assertions.assertThat(header.getProductCountInCart()).isEqualTo("3");
        hp.removeProductToCart("Test.allTheThings() T-Shirt (Red)");
        Assertions.assertThat(header.getProductCountInCart()).isEqualTo("2");

    }


    @Test
    public void orderProductsTest() {
        List<String> products = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt");
        hp.addProductToCart(products.get(0));
        hp.addProductToCart(products.get(1));
        header.goTocart();
        Assertions.assertThat(productCart.getItemsInCart()).isEqualTo(products);
        productCart.setCheckoutBtn();
        Assertions.assertThat(header.getHeaderTitle()).isEqualTo("CHECKOUT: YOUR INFORMATION");
        checkOutPage.setCustDetails("Abc", "efg", "2809");
        checkOutPage.proceedWithOrderFromCheckOutPage();
        Assertions.assertThat(header.getHeaderTitle()).isEqualTo("CHECKOUT: OVERVIEW");
        Assertions.assertThat(checkOutOverview.getPaymentInfo()).isEqualTo("SauceCard #31337");
        Assertions.assertThat(checkOutOverview.getShippingInfo()).isEqualTo("FREE PONY EXPRESS DELIVERY!");
        Assertions.assertThat(checkOutOverview.getPriceBeforeTax()).isEqualTo("Item total: $45.98");
        Assertions.assertThat(checkOutOverview.getTaxPrice()).isEqualTo("Tax: $3.68");
        Assertions.assertThat(checkOutOverview.getTotalPrice()).isEqualTo("Total: $49.66");
        checkOutOverview.finishOrdering();
        Assertions.assertThat(header.getHeaderTitle()).isEqualTo("CHECKOUT: COMPLETE!");

    }

    @Test
    public void emptyFieldsOnCheckOutpageTest() {
        List<String> products = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt");
        hp.addProductToCart(products.get(0));
        hp.addProductToCart(products.get(1));
        header.goTocart();
        Assertions.assertThat(productCart.getItemsInCart()).isEqualTo(products);
        productCart.setCheckoutBtn();
        Assertions.assertThat(header.getHeaderTitle()).isEqualTo("CHECKOUT: YOUR INFORMATION");
        checkOutPage.proceedWithOrderFromCheckOutPage();
        Assertions.assertThat(checkOutPage.getErrorMessage()).isEqualTo("Error: First Name is required");
        checkOutPage.setCustDetails("abc", "", "");
        checkOutPage.proceedWithOrderFromCheckOutPage();
        Assertions.assertThat(checkOutPage.getErrorMessage()).isEqualTo("Error: Last Name is required");
        checkOutPage.setCustDetails("abc", "def", "");
        checkOutPage.proceedWithOrderFromCheckOutPage();
        Assertions.assertThat(checkOutPage.getErrorMessage()).isEqualTo("Error: Postal Code is required");
    }

    @Test
    public void verifyProductinCartTest() {
        hp.addProductToCart("Sauce Labs Backpack");
        Assertions.assertThat(header.getProductCountInCart()).isEqualTo("1");
        hp.selectProductInProductView("Sauce Labs Backpack");
        Assertions.assertThat(productView.getBtnName()).isEqualTo("REMOVE");
        productView.clickOnAddOrRemoveBtn();
        Assertions.assertThat(header.getProductCountInCart()).isNullOrEmpty();
        Assertions.assertThat(productView.getBtnName()).isEqualTo("ADD TO CART");
    }
}
