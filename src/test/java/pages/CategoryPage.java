package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryPage extends BasePage{
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    By baseListTable = By.cssSelector(".main-book-list");
    By baseListTableItemClass = By.cssSelector(".book");
    By orderButtonId = By.cssSelector(".btn-order");
    By imageUrl = By.xpath("//a[contains(@href, '/book/')]");
    By purchasePriceId = By.cssSelector(".purchase-price");
    By coverPriceId = By.cssSelector(".cover-price");
    By starsId = By.cssSelector(".stars");

    public void addToBasket(int index) {
        List<WebElement> categoryBooksRows = find(baseListTable).findElements(baseListTableItemClass);
        if (categoryBooksRows.get(index).findElement(orderButtonId).isEnabled()){
            categoryBooksRows.get(index).findElement(orderButtonId).click();
        }
    }

    public void assertTableData(){
        List<WebElement> categoryBooksRows = find(baseListTable).findElements(baseListTableItemClass);

        for (WebElement book : categoryBooksRows){
            assertFalse(book.findElement(imageUrl).findElement(By.tagName("img")).getAttribute("src").isEmpty());
            assertFalse(book.findElement(purchasePriceId).getText().isEmpty());
            assertFalse(book.findElement(coverPriceId).getText().isEmpty());
            assertTrue(book.findElement(starsId).isDisplayed());
        }
    }
}
