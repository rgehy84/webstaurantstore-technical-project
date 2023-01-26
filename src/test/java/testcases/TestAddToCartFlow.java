package testcases;

import framework.Assertions;
import framework.BaseDriver;
import framework.WebDriverActions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainNavigation;
import pages.ProductDetails;
import pages.SearchResults;
import pages.ShoppingCart;

public class TestAddToCartFlow extends BaseDriver {

    /*
     * Test case:
     * 1. Go to https://www.webstaurantstore.com/
     * 2. Search for "stainless work table"
     * 3. Check the search result ensuring every product has the word "Table" in its title.
     * 4. Add the last of found items to Cart.
     * 5. Empty Cart.
     */

    private WebDriverActions wda;
    private MainNavigation mainNav;
    private SearchResults searchResults;
    private ProductDetails productDetails;
    private ShoppingCart shoppingCart;
    private Assertions assertions;
    @BeforeMethod
    public void setupTest() {
        wda = new WebDriverActions(getDriver());
        mainNav = new MainNavigation(getDriver());
        searchResults = new SearchResults(getDriver());
        productDetails = new ProductDetails(getDriver());
        shoppingCart = new ShoppingCart(getDriver());
        assertions = new Assertions();
    }

    @Test
    public void testAddProductToCartViaProductDetailsPageFlow() {
        wda.goToUrl(getUrlToStartTests());
        mainNav.searchForItemOnSite("stainless work table");
        for (WebElement item : searchResults.getProductDescriptionInSearchResults()) {
            assertions.assertTrue("Validate each product in results contains the word table. \nValidating: " + item.getText(),
                    item.getText().contains("Table"), false);
        }

        searchResults.clickOnProduct(searchResults.getProductDescriptionInSearchResults().size() - 1);
        String productName = productDetails.getProductName().getText();
        productDetails.addProductToCart();
        mainNav.clickCartIcon();
        assertions.assertEquals("Validate product selected is product added to cart",
                shoppingCart.getItemsInShoppingCart().get(shoppingCart.getItemsInShoppingCart().size()-1).getText(),
                productName);
        shoppingCart.clickEmptyShoppingCartButton();
        shoppingCart.confirmEmptyShoppingCartActionInModal();
        assertions.assertEquals("Validate shopping cart is empty",
                shoppingCart.getShoppingCartIsEmptyTextHeader().getText(),
                "Your cart is empty.");
        assertions.assertTrue("Validate no items in shopping cart",
                shoppingCart.getItemsInShoppingCart().size() == 0);
    }

    @Test
    public void testAddProductToCartViaSearchResultsDetailsPageFlow() {
        wda.goToUrl(getUrlToStartTests());
        mainNav.searchForItemOnSite("stainless work table");

        for (WebElement item : searchResults.getProductDescriptionInSearchResults()) {
            assertions.assertTrue("Validate each product in results contains the word table. \nValidating: " + item.getText(),
                    item.getText().contains("Table"), false);
        }

        String productName = searchResults.getProductDescriptionInSearchResults().get(searchResults.getProductsInSearchResults().size() - 1).getText();
        searchResults.addProductToCart(searchResults.getProductsInSearchResults().size() - 1);
        mainNav.clickViewCartButtonAfterAddingItemToCart();
        assertions.assertEquals("Validate product selected is product added to cart ",
                shoppingCart.getItemsInShoppingCart().get(shoppingCart.getItemsInShoppingCart().size()-1).getText(),
                productName);
        shoppingCart.clickEmptyShoppingCartButton();
        shoppingCart.confirmEmptyShoppingCartActionInModal();
        assertions.assertEquals("Validate shopping cart is empty",
                shoppingCart.getShoppingCartIsEmptyTextHeader().getText(),
                "Your cart is empty.");
        assertions.assertTrue("Validate no items in shopping cart",
                shoppingCart.getItemsInShoppingCart().size() == 0);
    }
}
