package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookDetailsPage extends BasePage {
    public BookDetailsPage(WebDriver driver) {
        super(driver);
    }

    By favoriteLink = By.cssSelector("a.link_wishlist");

    public void clickOnFavoriteLink() {
        find(favoriteLink).click();
        assertCurrentPageUrl("wishlist");
    }
}
