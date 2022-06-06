import helpers.SeleniumDriverHelper;
import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;


public class ShopTests extends SeleniumDriverHelper {

    @BeforeAll
    static void beforeAll() {
        createBrowserInstance("chrome");
    }

    @AfterAll
    static void afterAll(){
        teardown();
    }

    @Test
    @DisplayName("bard")
    void firstTest() {
        String searchCriteria = "роман";
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.visitHomePage("bard");
        homePage.searchByName(searchCriteria);
        searchPage.assertPageUrl(searchCriteria);
        searchPage.addProductToBasket(0);
        searchPage.addProductToBasket(1);
        searchPage.visitCartPage("shopping-cart");
        cartPage.verifyBasketCartContent(searchCriteria, 2);
    }

}
