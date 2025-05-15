package pompages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class DashboardPage {
    WebDriver driver;

    @FindBy(xpath = "//h4[normalize-space()='Your Calling List']")
    WebElement callingListTitle;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardVisible() {
        return callingListTitle.isDisplayed();
    }
}
