package pompages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CSVUploadPage1 {
    WebDriver driver;
    WebDriverWait wait;

    public CSVUploadPage1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this); // Initializes @FindBy elements
    }

    @FindBy(xpath = "//input[@id='txtEmailId']")
    WebElement email;

    @FindBy(xpath = "//input[@id='txtPassword']")
    WebElement password;

    @FindBy(xpath = "//input[@id='btnLogIn']")
    WebElement loginBtn;
    @FindBy(xpath = "//span[@aria-hidden='true']")
    WebElement initialpopup;
    

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_txtlistname']")
    WebElement listNameField;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_fileUpload']")
    WebElement csvUploadInput;
    
    @FindBy(xpath = "//a[@id='btnUp']")
    WebElement csvclickUpload;

   
    private By popupOkButton = By.xpath("//button[contains(text(),'OK')]");
    private By mapNameDropdown = By.xpath("//select[@id='ContentPlaceHolder1_Gridviewmap_ddlMapField_1']");
    private By mapPhoneDropdown = By.xpath("//select[@id='ContentPlaceHolder1_Gridviewmap_ddlMapField_2']");
    private By mapNotesDropdown = By.xpath("//select[@id='ContentPlaceHolder1_Gridviewmap_ddlMapField_3']");
    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_btnUpload']")
    WebElement importButton;
    private By successMessage = By.xpath("//button[normalize-space()='OK']");

    // Login
    public void login(String uemail, String upass) {
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(uemail);
        password.sendKeys(upass);
        loginBtn.click();
    }
    
    public void closeWelcomePopupIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for popup close button to be clickable
            WebElement closeBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@aria-hidden='true']/parent::button")));

            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
            System.out.println(" Welcome popup close button clicked.");

            
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
            System.out.println(" Welcome popup dismissed from view.");

        } catch (Exception e) {
            System.out.println("ℹ No welcome popup appeared or it was already closed. Details: " + e.getMessage());
        }
    }



    // Enter list name
    public void enterListName(String name) {
        wait.until(ExpectedConditions.visibilityOf(listNameField)).sendKeys(name);
    }

    // Upload CSV file
    public void uploadCSV(String filePath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='ContentPlaceHolder1_fileUpload']"))).sendKeys(filePath);
    }
    //clickupoad
    public void clickCSVUploadButton() {
        wait.until(ExpectedConditions.elementToBeClickable(csvclickUpload)).click();
    }
    public void handlePopupIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement okButton = shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Ok']")));

            if (okButton.isDisplayed()) {
                okButton.click();
                System.out.println(" Popup handled: 'Ok' button clicked.");
            }
        } catch (Exception e) {
            
            System.out.println(" No popup appeared after CSV upload.");
        }
    }



    

    // Map fields and click import
    public void mapFields() {
        try {
            // Wait until the dropdowns are visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select)[1]")));

            // First dropdown 
            Select nameSelect = new Select(driver.findElement(By.xpath("(//select)[1]")));
            nameSelect.selectByVisibleText("FirstName"); // Update based on exact option text

            // Second dropdown 
            Select phoneSelect = new Select(driver.findElement(By.xpath("(//select)[2]")));
            phoneSelect.selectByVisibleText("Phone"); // Update if exact text differs

            // Third dropdown 
            Select emailSelect = new Select(driver.findElement(By.xpath("(//select)[3]")));
            emailSelect.selectByVisibleText("Notes"); // Update if exact text differs

            System.out.println(" Fields mapped successfully.");
        } catch (Exception e) {
            System.out.println(" Failed to map fields: " + e.getMessage());
        }
    }
    public void clickImportButton() {
        try {
            // Scroll into view
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", importButton);
            wait.until(ExpectedConditions.elementToBeClickable(importButton)).click();
            System.out.println(" Import button clicked.");
        } catch (Exception e) {
            System.out.println(" Failed to click Import button: " + e.getMessage());
        }
    }



    // Get success message
    /*public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }*/

   

}
