package pompages;




import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    private By nameField = By.id("txtName");
    private By emailField = By.id("txtEmail");
    private By phoneField = By.id("txt_mobile");
    private By passwordField = By.id("txtPassword");
    
    // Locate checkbox input instead of label
    private By termsCheckbox = By.xpath("//input[@id='checkbox-signup']");

    
    private By registerButton = By.id("btnSignUp");

    // Success message locator (adjust if needed)
    private By otpMessage = By.xpath("//p[normalize-space()='OTP send to whatsapp number!']");

    // Form filling methods
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField)).sendKeys(phone);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    // Accept terms checkbox
    public void acceptTermsAndConditions() {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(termsCheckbox));

        // Use JavaScript to click (more reliable if element is covered or styled)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

        //  Confirm it's selected
        boolean checked = checkbox.isSelected();
        System.out.println("✅ Checkbox selected: " + checked);
    }



    // Click register button
    public void clickRegister() {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));

            // Wait for possible loader to disappear if present
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
            } catch (Exception e) {
                System.out.println("⚠️ Loader not found or already gone.");
            }

            // Scroll to the button and force-click using JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            Thread.sleep(1000); // Small pause after scroll (optional)

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            System.out.println(" Sign Up button clicked successfully.");
        } catch (Exception e) {
            System.out.println("❌ Failed to click Register button: " + e.getMessage());
            throw new RuntimeException("Register button was not clickable.", e);
        }
    }


    // Verify registration
    public boolean isRegistrationSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(otpMessage));
            System.out.println(" OTP message is visible — registration was successful.");
            return true;
        } catch (Exception e) {
            System.out.println(" OTP message not found. Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }

    // Composite registration method
    public void registerUser(String name, String email, String phone, String password) {
        enterName(name);
        enterEmail(email);
        enterPhone(phone);
        enterPassword(password);
        acceptTermsAndConditions(); // ✅ Ensure checkbox is accepted
        clickRegister();
    }
}
