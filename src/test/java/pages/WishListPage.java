package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class WishListPage extends BasePage {
    public WishListPage(WebDriver driver, String name) {

        super(driver, name);

    }

    public static String urlPath = "wishlist";

    By wishlistTable = By.className("main-book-list");
    By bookContainer = By.className("book");
    By removeWishListBook = By.cssSelector("a.bardbutton_silver");

    public void verifyWishlistData(int count) {
        List<WebElement> wishListRows = findElements(wishlistTable, bookContainer);
        assertEquals(count, wishListRows.size());
    }

    public void removeWishListBook() {
        find(removeWishListBook).click();
    }
}
