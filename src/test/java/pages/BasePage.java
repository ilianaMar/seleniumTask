package pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public String siteName;

    //Constructor
    public BasePage(WebDriver driver, String name) {
        this.driver = driver;
        this.siteName = name;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public String getBaseURL() {
        JSONParser jsonParser = new JSONParser();
        String baseUrlValue = "";

        try (FileReader reader = new FileReader("src/test/resources/environments.json")) {
            if (!siteName.isEmpty() && !siteName.isBlank()) {
                JSONObject obj = (JSONObject) jsonParser.parse(reader);
                baseUrlValue = (String) obj.get(siteName);
            } else {
                throw new RuntimeException("Please, add valid key");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return baseUrlValue;
    }

    //Wait Wrapper Method
    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
    }

    //Click Method
    public void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    //Find Element
    public WebElement find(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy);
    }

    //Find Elements
    public List<WebElement> findElements(By firstElementBy, By secondElementBy) {
        waitVisibility(firstElementBy);
        waitVisibility(secondElementBy);
        return driver.findElement(firstElementBy).findElements(secondElementBy);
    }

    //Write Text
    public void writeText(By elementBy, String text) {
        this.find(elementBy).sendKeys(text);
    }

    //Read Text
    public String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    public void assertCurrentPageUrl(String url) {
        String urlGetParameter = driver.getCurrentUrl().replace(getBaseURL(), "");
        assertTrue(StringUtils.containsIgnoreCase(urlGetParameter, url));
    }
}
