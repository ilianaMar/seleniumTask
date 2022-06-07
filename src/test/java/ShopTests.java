import helpers.SeleniumDriverHelper;
import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;


public class ShopTests extends SeleniumDriverHelper {
    private String siteName = "bard";

    @BeforeAll
    static void beforeAll() {
        createBrowserInstance("chrome");
    }

    @AfterAll
    static void afterAll() {
        teardown();
    }

    @Test
    @DisplayName("bard")
    void firstTest() {
        String searchCriteria = "роман";
        HomePage homePage = new HomePage(driver, siteName);
        SearchPage searchPage = new SearchPage(driver, siteName);
        CartPage cartPage = new CartPage(driver, siteName);
        homePage.visitHomePage();
        homePage.searchByName(searchCriteria);
        searchPage.assertPageUrl(searchCriteria);
        searchPage.addProductToBasket(0);
        searchPage.addProductToBasket(1);
        searchPage.visitCartPage();
        cartPage.verifyBasketBookName(searchCriteria);
        cartPage.verifyBasketCount(2);
    }

}
