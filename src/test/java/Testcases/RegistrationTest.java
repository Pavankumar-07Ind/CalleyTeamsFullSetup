package Testcases;



import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pompages.RegistrationPage;

public class RegistrationTest {

    WebDriver driver;
    RegistrationPage registrationPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver(); // Ensure ChromeDriver is set up correctly
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://app.getcalley.com/registration.aspx");
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    public void registerUser() throws InterruptedException {
        Random random = new Random();

        String name = "User" + random.nextInt(10000);
        String email = "user" + System.currentTimeMillis() + "@mail.com";
        String phone = (7 + random.nextInt(3)) + String.format("%09d", random.nextInt(1000000000));
        String password = "Test@" + random.nextInt(999999);

        registrationPage.registerUser(name, email, phone, password);

        Thread.sleep(2000);  // Optional wait

        Assert.assertTrue(registrationPage.isRegistrationSuccessful(), "❌ User registration failed.");
    }


    @AfterClass
    public void tearDown() {
        
            driver.quit();
        
    }
}
