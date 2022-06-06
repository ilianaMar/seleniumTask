package stepDefinitions;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.*;
import helpers.SeleniumDriverHelper;
import static org.junit.jupiter.api.Assertions.*;
import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;

public class ShopTestsSteps extends SeleniumDriverHelper {
    HomePage homePage = new HomePage(driver);
    SearchPage searchPage = new SearchPage(driver);
    CartPage cartPage = new CartPage(driver);

    @BeforeAll
    public static void beforeAll() {
        createBrowserInstance("chrome");
    }

    @AfterAll
    public static void afterAll() {
        teardown();
    }

    @Given("I visit {string} home page")
    public void iVisitHomePage(String shopName) {
        homePage.visitHomePage(shopName);
    }

    @When("^I search by book name (.*)$")
    public void iSearchByBookNameName(String bookName) {
        homePage.searchByName(bookName);
        searchPage.assertPageUrl(bookName);
    }

    @And("I add {int} book")
    public void iAddBook(int count) {
        searchPage.addProductToBasket(0);
        searchPage.visitCartPage("shopping-cart");
    }

    @Then("^I verify that (\\d+) books{0,1} with (.*) is added correctly$")
    public void iVerifyThatBookIsAddedCorrectly(int count, String bookName) {
        cartPage.verifyBasketCartContent(bookName, count);
    }

    @When("I update book quantity to be {int}")
    public void iUpdateBookQuantityToBe(int count) {
        float oldPrice  = cartPage.getTotalPrice();
        System.out.println(oldPrice);
        cartPage.updateQuantityCount(count);
        float newPrice  = cartPage.getTotalPrice();
        System.out.println(newPrice);
        assertEquals(newPrice, (count*oldPrice));
    }
}
