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
    By quantityButtonId = By.className("quantity-update");
    By currencyTotalPriceSpanId = By.cssSelector("td.totalprice .currency");
    By quantityRowId = By.xpath("//td[@class='quantity']");
    By totalPriceTdId = By.cssSelector("td.totalprice");
    By bookRowId = By.xpath("//div[@class='book']");
    By discountContainer = By.cssSelector(".discount-level-inner");


    public void verifyBasketBookName(String bookName) {
        List<WebElement> bookRows = findElements(shoppingTable, bookRowId);
        for (WebElement bRow : bookRows) {
            StringUtils.containsIgnoreCase(bRow.getText(), bookName);
        }

    }

    public void verifyBasketCount(int bookCount) {
        List<WebElement> tdQuantityRows = findElements(shoppingTable, quantityRowId);
        List<Integer> rowInputs = new ArrayList<>();
        int sum = 0;

        for (WebElement row : tdQuantityRows) {
            rowInputs.add(Integer.parseInt(row.findElement(quantityInput).getAttribute("value")));
        }

        for (Integer val : rowInputs) {
            sum += val;
        }
        assertEquals(sum, bookCount);
    }

    public void updateQuantityCount(int count) {
        List<WebElement> tdQuantityRows = findElements(shoppingTable, quantityRowId);
        for (WebElement row : tdQuantityRows) {
            row.findElement(quantityInput).clear();
            row.findElement(quantityInput).sendKeys(String.valueOf(count));
            row.findElement(quantityButtonId).click();
        }

    }

    public float getTotalPrice() {
        WebElement totalPriceField = find(shoppingTable).findElement(totalPriceTdId);
        WebElement currencyTotalPrice = find(shoppingTable).findElement(currencyTotalPriceSpanId);
        String totalPriceValue = StringUtils.trim(totalPriceField.getText()).replace(currencyTotalPrice.getText(),
                "");

        return Float.parseFloat(totalPriceValue);
    }

    public void assertDiscountValue(int value){
        List<WebElement> discounts = find(discountContainer).findElements(By.tagName("strong"));
        String discountValue = discounts.get(2).getText().replace("%", "");
        assertEquals(Integer.parseInt(discountValue), value);
    }

}
