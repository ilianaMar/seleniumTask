package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import helpers.SeleniumDriverHelper;

import static org.junit.jupiter.api.Assertions.*;

import models.User;
import pages.*;

public class ShopTestsSteps extends SeleniumDriverHelper {
    private String siteName = "bard";
    HomePage homePage = new HomePage(driver, siteName);
    SearchPage searchPage = new SearchPage(driver, siteName);
    CartPage cartPage = new CartPage(driver, siteName);
    CategoryPage categoryPage = new CategoryPage(driver, siteName);
    RegisterPage registerPage = new RegisterPage(driver, siteName);
    WishListPage wishListPage = new WishListPage(driver, siteName);
    BookDetailsPage bookDetailsPage = new BookDetailsPage(driver, siteName);
    User newUser;

    @Before
    public static void beforeEach() {
        createBrowserInstance("chrome");
    }

    @After
    public static void afterEach() {
        teardown();
    }

    @Given("I visit bard home page")
    public void iVisitHomePage() {
        homePage.visitHomePage();
    }

    @When("^I search by book name (.*)$")
    public void iSearchByBookNameName(String bookName) {
        homePage.searchByName(bookName);
    }

    @And("^I add (\\d+) books{0,1} from search page$")
    public void iAddBook(int count) {
        for (int i = 0; i <= count - 1; ++i) {
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
        float oldPrice = cartPage.getTotalPrice();
        cartPage.updateQuantityCount(count);
        float newPrice = cartPage.getTotalPrice();
        float totalPrice =  count * oldPrice;
        assertEquals(newPrice, Float.parseFloat(String.format("%.2f", totalPrice)));
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
        for (int i = 0; i <= count - 1; ++i) {
            categoryPage.addToBasket(i);
        }
        searchPage.visitCartPage();
    }

    @Then("^I verify that (\\d+) books{0,1} is added$")
    public void iVerifyThatBookIsAdded(int count) {
        cartPage.verifyBasketCount(count);
    }

    @When("I visit  register page and create user successfully")
    public void iVisitRegisterPageAndCreateUserSuccessfully() {
        User newUser = registerPage.buildUserFactory();
        System.out.println(newUser);
        homePage.visitRegisterPage();
        registerPage.createUserAccount(newUser);
    }

    @And("I visit book details page of first book")
    public void iVisitBookDetailsPageOfFirstBookAndAddItToWishlist() {
        categoryPage.visitBookDetailsPage(1);
    }

    @Then("^(\\d+) books{0,1} is existing in wishlist$")
    public void theBookIsExistingInWishlist(int count) {
        wishListPage.verifyWishlistData(count);
    }

    @And("^I remove (\\d+) books{0,1} from wishlist$")
    public void iRemoveBookFromWishlist(int count) {
        wishListPage.removeWishListBook();
    }

    @Then("^I check discount should be (\\d+) percentages{0,1}$")
    public void iCheckDiscountShouldBePercentage(int discountValue) {
        cartPage.assertDiscountValue(discountValue);
    }

    @And("I add to favourites this book")
    public void iAddToFavouritesThisBook() {
        bookDetailsPage.clickOnFavoriteLink();
    }
}
