package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SeleniumDriverHelper {
    public static WebDriver driver;

    public static void setupDriver(String browserName) {
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
        }
    }

    public static void createBrowserInstance(String browserName) {
        setupDriver(browserName);
        driver = (browserName.equals("chrome")) ? new ChromeDriver() : new FirefoxDriver();
        driver.get("chrome://settings/clearBrowserData");
        driver.manage().window().maximize();
    }

    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
