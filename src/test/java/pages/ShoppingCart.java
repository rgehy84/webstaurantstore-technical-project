package pages;

import framework.WebDriverActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCart {
    private WebDriverActions wda;
    public ShoppingCart(WebDriver driver){
        wda = new WebDriverActions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button.emptyCartButton")
    private WebElement emptyShoppingCartButton;

    @FindBy(css = "div.cartItem span.itemDescription a")
    private List<WebElement> itemsInShoppingCart;

    @FindBy(xpath = "//div[contains(@class,'ReactModal__Content')]//button[.='Empty Cart']")
    private WebElement emptyCartConfirmationButton;

    @FindBy(css = "div.empty-cart__text  p.header-1")
    private WebElement shoppingCartIsEmptyTextHeader;

    public WebElement getEmptyShoppingCartButton() {
        return emptyShoppingCartButton;
    }

    public List<WebElement> getItemsInShoppingCart() {
        return itemsInShoppingCart;
    }

    public WebElement getEmptyCartConfirmationButton() {
        return emptyCartConfirmationButton;
    }

    public WebElement getShoppingCartIsEmptyTextHeader() {
        return shoppingCartIsEmptyTextHeader;
    }

    public void clickEmptyShoppingCartButton() {
        wda.click(getEmptyShoppingCartButton(), "Empty Cart button");
    }

    public void confirmEmptyShoppingCartActionInModal() {
        wda.click(getEmptyCartConfirmationButton(), "Empty Cart confirmation button");
    }


}
