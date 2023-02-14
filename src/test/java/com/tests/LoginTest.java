package com.tests;

import com.pages.LoginPage;
import com.pages.PageRepo;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get(BASE_URL);
        loginPage = PageRepo.loginPage(driver);
    }


    @Test
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assertions.assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    public void lockedOutUser() {
        loginPage.login("locked_out_user", "secret_sauce");
        Assertions.assertThat(loginPage.isErrorDisplayed()).isTrue();
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void performanceGlitch() {
        loginPage.login("performance_glitch_user", "secret_sauce");
        Assertions.assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    public void emptyUserName() {
        loginPage.login("", "secret_sauce");
        Assertions.assertThat(loginPage.isErrorDisplayed()).isTrue();
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username is required");

    }

    @Test
    public void emptyPassword() {
        loginPage.login("standard_user", "");
        Assertions.assertThat(loginPage.isErrorDisplayed()).isTrue();
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Password is required");

    }

    @Test
    public void invalidUserName() {
        loginPage.login("cdfghy", "secret_sauce");
        Assertions.assertThat(loginPage.isErrorDisplayed()).isTrue();
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }
}
