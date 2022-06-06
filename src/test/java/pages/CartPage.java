package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    By shoppingTable = By.className("shopping-cart-list");
    By quantityInput = By.xpath("//input[contains(@name, 'quantity')]");
    By quantityRowId = By.xpath("//td[@class='quantity']");
    By bookRowId = By.xpath("//div[@class='book']");


    public void verifyBasketCartContent(String bookName, int bookCount) {
        WebElement baseTable = find(shoppingTable);
        List<WebElement> bookRows = baseTable.findElements(bookRowId);
        List<WebElement> tdQuantityRows = baseTable.findElements(quantityRowId);
        List<Integer> rowInputs = new ArrayList<>();
        int sum = 0;

        for (WebElement row : tdQuantityRows) {
            rowInputs.add(Integer.parseInt(row.findElement(quantityInput).getAttribute("value")));
        }

        for (WebElement bRow : bookRows) {
            StringUtils.containsIgnoreCase(bRow.getText(), bookName);
        }

        for (Integer val : rowInputs) {
            sum += val;
        }
        assertEquals(sum, bookCount);
    }

}
