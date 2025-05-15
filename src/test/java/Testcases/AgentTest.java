package Testcases;




import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pompages.AgentPage;
import pompages.LoginPage;
import utilities.DriverSetup;

public class AgentTest {

    WebDriver driver;
    LoginPage loginPage;
    AgentPage agentPage;

    @BeforeClass
    public void setup() {
        driver = DriverSetup.getDriver();
        loginPage = new LoginPage(driver);
        agentPage = new AgentPage(driver);
        driver.get("https://app.getcalley.com/Login.aspx");
        loginPage.login("pavansep2001@gmail.com", "Pavan@194701");
    }

    @Test
    public void addAgentTest() {
        if (!agentPage.isAgentTabAvailable()) {
            System.out.println(" Agent feature not available in this account.");
            Assert.fail("Agent tab is not present. Please ensure your account has access to Team/Agent features.");
            return;
        }

       
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
