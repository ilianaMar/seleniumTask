package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import  org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class SeleniumDriverHelper {
    public static WebDriver driver;

    public static void setupDriver(String browserName){
        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
        } else if (browserName.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
        }

    }

    public static WebDriver createBrowserInstance(String browserName) {
        setupDriver(browserName);
        driver = ( browserName.equals("chrome")) ? new ChromeDriver() : new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterAll
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }



}
