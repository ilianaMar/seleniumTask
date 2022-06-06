package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class WishListPage extends BasePage{
    public WishListPage(WebDriver driver) {
        super(driver);
    }

    By wishlistTable = By.className("main-book-list");
    By bookContainer = By.className("book");
    By removeWishListBook = By.cssSelector("a.bardbutton_silver");

    public void verifyWishlistData(int count){

        if(count != 0 ){
            List<WebElement> wishListRows = findElements(wishlistTable, bookContainer);
            System.out.println(wishListRows);
            assertEquals(wishListRows.size(), count);
        }else {
            assertFalse(find(wishlistTable).findElement(bookContainer).isDisplayed());
        }

    }

    public void removeWishListBook(){
        find(removeWishListBook).click();
    }
}
