package utilities;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {
    public static WebDriver getDriver() {
        //System.setProperty("webdriver.chrome.driver", "C:/Users/Hari/Downloads/chromedriver-win64/chromedriver.exe");
        return new ChromeDriver();
    }
}
