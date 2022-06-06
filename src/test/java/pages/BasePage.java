package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        StringUtils.containsIgnoreCase(driver.getCurrentUrl(), url);
    }
}
