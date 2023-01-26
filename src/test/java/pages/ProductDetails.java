package pages;

import framework.WebDriverActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetails {
    private WebDriverActions wda;

    public ProductDetails(WebDriver driver) {
        wda = new WebDriverActions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "buyButton")
    private WebElement buyButton;

    @FindBy(css = "h1[data-testid='product-detail-heading']")
    private WebElement productName;

    public WebElement getBuyButton() {
        return buyButton;
    }

    public WebElement getProductName() {
        return productName;
    }

    public void addProductToCart() {
        wda.click(getBuyButton(), " Add to Cart button for " + getProductName().getText());
    }
}
