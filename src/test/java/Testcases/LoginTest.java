package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import BaseTest.BaseTest;
import pompages.LoginPage;

public class LoginTest extends BaseTest {
    @Test
    public void loginUser() {
        driver.get("https://app.getcalley.com/Login.aspx");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("pavansep2001@gmail.com", "Pavan@194701");
        loginPage.closeWelcomePopupIfPresent();

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed - Dashboard not visible");
    }
}

