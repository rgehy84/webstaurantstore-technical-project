package pages;

import framework.WebDriverActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResults {
    private WebDriverActions wda;
    public SearchResults(WebDriver driver) {
        wda = new WebDriverActions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div#product_listing div[data-testid='productBoxContainer']")
    private List<WebElement> productsInSearchResults;

    @FindBy(css = "div#product_listing div[data-testid='productBoxContainer'] div#details a[data-testid='itemDescription']")
    private List<WebElement> productDescriptionInSearchResults;

    @FindBy(css = "div#product_listing input[data-testid='itemAddCart']")
    private List<WebElement> productAddToCartButtonsInSearchResults;


    public List<WebElement> getProductsInSearchResults() {
        return productsInSearchResults;
    }

    public List<WebElement> getProductDescriptionInSearchResults() {
        return productDescriptionInSearchResults;
    }

    public List<WebElement> getProductAddToCartButtonsInSearchResults() {
        return productAddToCartButtonsInSearchResults;
    }

    public void clickOnProduct(int positionInList) {
        wda.click(getProductDescriptionInSearchResults().get(positionInList),
                getProductDescriptionInSearchResults().get(positionInList).getText());
    }

    public void addProductToCart(int positionInList) {
        wda.click(getProductAddToCartButtonsInSearchResults().get(positionInList),
                "Add to Cart button for " + getProductDescriptionInSearchResults().get(positionInList).getText());
    }






}
