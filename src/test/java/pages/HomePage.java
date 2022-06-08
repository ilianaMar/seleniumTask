package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, String name) {

        super(driver, name);

    }

    By searchInput = By.xpath("//*[@id='header']/form/input[1]");
    By searchButton = By.xpath("//*[@id='header']/form/input[2]");
    By memberDropDown = By.cssSelector(".member-info .dropdown");
    By registerLink = By.xpath("//a[@href='/register/']");


    public void visitHomePage() {
        String baseUrl = getBaseURL();
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

    public void visitRegisterPage() {
        Actions action = new Actions(driver);
        action.moveToElement(find(memberDropDown)).perform();
        find(registerLink).click();
//        action.moveToElement(find(registerLink)).click().perform();
        assertCurrentPageUrl(RegisterPage.urlPath);
    }
}
