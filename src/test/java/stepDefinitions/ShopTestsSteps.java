package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import helpers.SeleniumDriverHelper;

import static org.junit.jupiter.api.Assertions.*;

import models.User;
import pages.*;

public class ShopTestsSteps extends SeleniumDriverHelper {
    HomePage homePage = new HomePage(driver);
    SearchPage searchPage = new SearchPage(driver);
    CartPage cartPage = new CartPage(driver);
    CategoryPage categoryPage = new CategoryPage(driver);
    RegisterPage registerPage = new RegisterPage(driver);
    WishListPage wishListPage = new WishListPage(driver);
    BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
    User newUser;

    @Before
    public static void beforeEach() {
        createBrowserInstance("chrome");
    }

    @After
    public static void afterEach() {
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
        System.out.println("11111 " + oldPrice);
        cartPage.updateQuantityCount(count);
        float newPrice = cartPage.getTotalPrice();
        System.out.println("2222 " + newPrice);
        assertEquals(newPrice, (count * oldPrice));
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
        homePage.visitRegisterPage();
        newUser = registerPage.createUserAccount();
    }

    @And("I visit book details page of first book and add it to wishlist")
    public void iVisitBookDetailsPageOfFirstBookAndAddItToWishlist() {
        categoryPage.visitBookDetailsPage(1);
    }

    @Then("^(\\d+) books{0,1} is existing in wishlist$")
    public void theBookIsExistingInWishlist(int count) {
        bookDetailsPage.clickOnFavoriteLink();
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
}
