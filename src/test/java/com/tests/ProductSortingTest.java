package com.tests;

import com.pages.Header;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.PageRepo;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;

public class ProductSortingTest extends BaseTest {

    private LoginPage loginPage;
    private Header header;
    private HomePage homePage;

    @BeforeMethod
    public void setUpTest() {
        driver.get(BASE_URL);
        loginPage = PageRepo.loginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        header = PageRepo.header(driver);
        homePage = PageRepo.homePage(driver);
    }

    @Test
    public void defaultSorting() {

        header.selectFilter("Name (A to Z)");

        Assertions.assertThat(homePage.getProductCount()).isEqualTo(6);
        Assertions.assertThat(homePage.getProductTitles()).isSortedAccordingTo(Comparator.naturalOrder());

    }

    @Test
    public void reverseNameSorting() {

        header.selectFilter("Name (Z to A)");
        HomePage homePage = new HomePage(driver);
        Assertions.assertThat(homePage.getProductCount()).isEqualTo(6);
        Assertions.assertThat(homePage.getProductTitles()).isSortedAccordingTo(Comparator.reverseOrder());

    }

    @Test
    public void increasingPriceOrder() {

        header.selectFilter("Price (low to high)");
        HomePage homePage = new HomePage(driver);
        Assertions.assertThat(homePage.getProductCount()).isEqualTo(6);
        Assertions.assertThat(homePage.getProductPricesInNumber()).isSortedAccordingTo(Comparator.naturalOrder());

    }

    @Test
    public void decreasingPriceOrder() {


        header.selectFilter("Price (high to low)");
        HomePage homePage = new HomePage(driver);
        Assertions.assertThat(homePage.getProductCount()).isEqualTo(6);
        Assertions.assertThat(homePage.getProductPricesInNumber()).isSortedAccordingTo(Comparator.reverseOrder());

    }


}
