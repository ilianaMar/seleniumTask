package stepDefinitions;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import helpers.SeleniumDriverHelper;
import static org.junit.jupiter.api.Assertions.*;
import pages.CartPage;
import pages.CategoryPage;
import pages.HomePage;
import pages.SearchPage;

public class ShopTestsSteps extends SeleniumDriverHelper {
    HomePage homePage = new HomePage(driver);
    SearchPage searchPage = new SearchPage(driver);
    CartPage cartPage = new CartPage(driver);
    CategoryPage categoryPage = new CategoryPage(driver);

    @Before
    public static void beforeAll() {
        createBrowserInstance("chrome");
    }

    @After
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

    @And("^I add (\\d+) books{0,1} from search page$")
    public void iAddBook(int count) {
        for (int i=0 ; i <= count-1 ; ++i){
            searchPage.addProductToBasket(i);
        }
        searchPage.visitCartPage();
    }

    @Then("^I verify that (\\d+) books{0,1} with (.*) is added correctly$")
    public void iVerifyThatBookIsAddedCorrectly(int count, String bookName) {
        cartPage.verifyBasketBookName(bookName);
        cartPage.verifyBasketCount(count);
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

    @When("I visit new books category with url {string}")
    public void iVisitNewBooksCategoryWithUrl(String categoryUrl) {
        homePage.selectBookCategory(categoryUrl);
    }

    @Then("I verify that all data in listing page")
    public void iVerifyThatAllDataInListingPage() {
        categoryPage.assertTableData();
    }

    @And("^I add (\\d+) books{0,1} from category page$")
    public void iAddBookFromCategoryPage(int count) {
        for (int i=0 ; i <= count-1 ; ++i){
            categoryPage.addToBasket(i);
        }
        searchPage.visitCartPage();
    }

    @Then("^I verify that (\\d+) books{0,1} is added$")
    public void iVerifyThatBookIsAdded(int count) {
        cartPage.verifyBasketCount(count);
    }
}
