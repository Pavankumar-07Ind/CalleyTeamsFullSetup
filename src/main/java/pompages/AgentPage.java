package pompages;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgentPage {
    WebDriver driver;
    WebDriverWait wait;

    
    private By agentMenu = By.xpath("//a[contains(text(), 'Agent') or contains(@href, 'agent')]");
    private By addAgentButton = By.xpath("//button[contains(text(), 'Add Agent')]");

    private By nameField = By.id("txtname");
    private By emailField = By.id("txtemail");
    private By mobileField = By.id("txtmobile");
    private By passwordField = By.id("txtpassword");
    private By confirmPasswordField = By.id("txtconfirmpassword");
    private By submitButton = By.id("btnsubmit");

    private By successMessage = By.xpath("//*[contains(text(),'Agent added successfully') or contains(@class,'alert-success')]");

    public AgentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToAgentSection() {
        wait.until(ExpectedConditions.elementToBeClickable(agentMenu)).click();
    }

    public void clickAddAgent() {
        wait.until(ExpectedConditions.elementToBeClickable(addAgentButton)).click();
    }

    public void fillAgentForm(String name, String email, String mobile, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(mobileField).sendKeys(mobile);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public boolean isAgentAddedSuccessfully() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

	public boolean isAgentTabAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	public void enterAgentDetails(String string, String string2, String string3, String string4) {
		// TODO Auto-generated method stub
		
	}

	

	
}

