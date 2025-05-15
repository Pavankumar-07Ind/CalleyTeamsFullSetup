package Testcases;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import pompages.CSVUploadPage1;

public class CSVUploadTest1 {

    WebDriver driver;
    CSVUploadPage1 uploadPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://app.getcalley.com/Login.aspx");
        uploadPage = new CSVUploadPage1(driver);
        // Login
        driver.findElement(By.id("txtEmailId")).sendKeys("pavansep2001@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("Pavan@194701");
        driver.findElement(By.id("btnLogIn")).click();
        //close welcome popup
        uploadPage.closeWelcomePopupIfPresent();

        // Wait for dashboard to load
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
            .until(d -> d.findElement(By.xpath("//a[normalize-space()='CREATE YOUR FIRST LIST & START AUTO DIALING']")))
            .click();

        // Initialize POM
        uploadPage = PageFactory.initElements(driver, CSVUploadPage1.class);
    }

    @Test
    public void uploadCSVTest1() throws InterruptedException {
        uploadPage.enterListName("Test Call List");

        // Upload file
        uploadPage.uploadCSV("C:\\Users\\Hari\\eclipse-workspace\\CalleyTeamsFullSetup\\TestData\\calllist.csv");
        //click upload
        uploadPage.clickCSVUploadButton();
        uploadPage.handlePopupIfPresent();

        // Map fields and click import
        Thread.sleep(3000);
        uploadPage.mapFields();
        //importbutton
        uploadPage.clickImportButton();

        
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
