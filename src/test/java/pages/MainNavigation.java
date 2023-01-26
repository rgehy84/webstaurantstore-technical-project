package pages;

import framework.WebDriverActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainNavigation {
    private WebDriverActions wda;
    private WebDriverWait wait;
    public MainNavigation(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofSeconds(50));
        wda = new WebDriverActions(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy (css = "div#banner-container a.banner-logo")
    private WebElement logo;

    @FindBy(css = "input[data-testid='searchval']")
    private WebElement searchInputField;

    @FindBy(css = "form#searchForm button[value='Search']")
    private WebElement searchButton;

    @FindBy(css = "a[data-testid='cart-nav-link']")
    private WebElement goToCartIcon;

    @FindBy(xpath = "//div[contains(@class,'notification__action')]//a[.='View Cart']")
    private WebElement viewCartButtonAfterAddingItemToCart;

    public WebElement getLogo() {
        return logo;
    }

    public WebElement getSearchInputField() {
//        wda.click(awesompleteSearchOverlay, null);
        return searchInputField;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getGoToCartIcon() {
        return goToCartIcon;
    }

    public WebElement getViewCartButtonAfterAddingItemToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonAfterAddingItemToCart));
        return viewCartButtonAfterAddingItemToCart;
    }

    public void enterSearchTermInSearchField(String searchTerm) {
        wda.enterTextIntoInputField(getSearchInputField(), "Search ", searchTerm);
    }

    public void clickSearchButton() {
        wda.click(getSearchButton(), "Search button");
    }

    public void searchForItemOnSite(String searchTerm) {
        enterSearchTermInSearchField(searchTerm);
        clickSearchButton();
    }

    public void clickLogoToGoToHomePage() {
        wda.click(getLogo(), "WebStaurantStore logo");
    }

    public void clickCartIcon() {
        wda.click(getGoToCartIcon(), "Shopping Cart icon");
    }

    public void clickViewCartButtonAfterAddingItemToCart() {
        if(getViewCartButtonAfterAddingItemToCart().isDisplayed()) {
            wda.click(getViewCartButtonAfterAddingItemToCart(), "View Cart button");
        } else {
            clickCartIcon();
        }
    }
}
