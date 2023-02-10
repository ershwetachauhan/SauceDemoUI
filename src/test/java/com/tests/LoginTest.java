package com.tests;

import com.pages.LoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void setUpTest() {
        driver.get(BASE_URL);
    }


    @Test
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assertions.assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    public void lockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        Assertions.assertThat(loginPage.isErrorDisplayed()).isTrue();
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void performanceGlitch() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("performance_glitch_user", "secret_sauce");
        Assertions.assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    public void emptyUserName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");
        Assertions.assertThat(loginPage.isErrorDisplayed()).isTrue();
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username is required");

    }

    @Test
    public void emptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");
        Assertions.assertThat(loginPage.isErrorDisplayed()).isTrue();
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Password is required");

    }

    @Test
    public void invalidUserName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("cdfghy", "secret_sauce");
        Assertions.assertThat(loginPage.isErrorDisplayed()).isTrue();
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username and password do not match any user in this service");

    }
}
