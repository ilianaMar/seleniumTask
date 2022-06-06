package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private String cartUrl = "shopping-cart";

    By productListId = By.id("search_results");
    By productModal = By.id("shopcart-dropdown-container");
    By cartLink = By.xpath("//a[contains(@href, '/shopping-cart/')]");
    By bookFieldId = By.className("book");
    By orderLinkId = By.className("btn-order");


    public void assertPageUrl(String url) {
        assertCurrentPageUrl(url);
    }

    public void addProductToBasket(int index) {
        List<WebElement> items = findElements(productListId, bookFieldId);
        items.get(index).findElement(orderLinkId).click();
        find(productModal).isDisplayed();
    }

    public void visitCartPage() {
        click(cartLink);
        assertCurrentPageUrl(this.cartUrl);
    }
}
