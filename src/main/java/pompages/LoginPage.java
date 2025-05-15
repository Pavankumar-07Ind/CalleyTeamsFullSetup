package pompages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath="//input[@id='txtEmailId']")
    WebElement email;

    @FindBy(xpath="//input[@id='txtPassword']")
    WebElement password;

    @FindBy(xpath="//input[@id='btnLogIn']")
    WebElement loginBtn;

    @FindBy(xpath = "//span[@aria-hidden='true']")
    WebElement popupCloseBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String uemail, String upass) {
        email.sendKeys(uemail);
        password.sendKeys(upass);
        loginBtn.click();
    }

    public void closeWelcomePopupIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement closeBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@aria-hidden='true']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
            System.out.println("Welcome popup close button clicked.");
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
            System.out.println("Welcome popup dismissed from view.");
        } catch (Exception e) {
            System.out.println("No welcome popup appeared or it was already closed. Details: " + e.getMessage());
        }
    }

    public boolean isLoginSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h4[normalize-space()='Your Calling List']")));
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("Login verification failed: " + e.getMessage());
            return false;
        }
    }
}


