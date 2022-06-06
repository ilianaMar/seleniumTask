package pages;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.*;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By searchInput = By.xpath("//*[@id='header']/form/input[1]");
    By searchButton = By.xpath("//*[@id='header']/form/input[2]");
    By memberDropDown = By.cssSelector(".member-info .dropdown");
    By registerLink = By.xpath("//a[@href='/register/']");


    public String getBaseURL(String baseURLName) {
        JSONParser jsonParser = new JSONParser();
        String baseUrlValue = "";

        try (FileReader reader = new FileReader("src/test/resources/environments.json")) {
            if (!baseURLName.isEmpty() && !baseURLName.isBlank()) {
                JSONObject obj = (JSONObject) jsonParser.parse(reader);
                baseUrlValue = (String) obj.get(baseURLName);
            } else {
                throw new RuntimeException("Please, add valid key");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return baseUrlValue;
    }

    public void visitHomePage(String baseUrlName) {
        String baseUrl = getBaseURL(baseUrlName);
        driver.get(baseUrl);
    }

    public void searchByName(String searchCriteria) {
        writeText(searchInput, searchCriteria);
        click(searchButton);
    }

    public void selectBookCategory(String categoryUrl) {
        By categoryId = By.xpath("//a[contains(@href, '" + categoryUrl + "')]");
        click(categoryId);
        assertCurrentPageUrl(categoryUrl);
    }

    public void visitRegisterPage(){
        Actions action = new Actions(driver);
        action.moveToElement(find(memberDropDown)).perform();
        action.moveToElement(find(registerLink)).click().perform();
        assertCurrentPageUrl("register");
    }
}
