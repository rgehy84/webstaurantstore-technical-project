package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.Strings;

import java.time.Duration;

public class WebDriverActions extends BaseUtilities {
    WebDriver driver;
    WebDriverWait wait;

    public WebDriverActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofSeconds(50));
    }

    /***
     * Navigates the driver to the specified website
     * @param url => URL to navigate to
     */
    public void goToUrl(String url) {
        print("Navigate to URL: " + url);
        driver.get(url);
    }

    /***
     * Simplified click method. Waits for element to be clickable before clicking
     * @param element => WebDriverElement that should be clicked
     *
     */
    public void click(WebElement element, String elementNameOrDescription) {
        if (Strings.isNotNullAndNotEmpty(elementNameOrDescription)) {
            print("Click on " + elementNameOrDescription);
        }
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /***
     * Simplified send keys. Waits for element to be clickable before inputting text
     * @param element => WebDriverElement field that should have text inputted
     * @param inputTextFieldName => the name of the field for text to be inputted that will be logged in steps
     * @param textToInput => text to input
     */
    public void enterTextIntoInputField(WebElement element, String inputTextFieldName, String textToInput) {
        print("Enter text in " + inputTextFieldName + " field: " + textToInput);
//        element.sendKeys(textToInput);
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToInput);
    }

    /***
     * Simplified send keys. Waits for element to be clickable before inputting password. Does not output password text
     * @param element WebDriverElement field that should have password inputted
     * @param inputPasswordFieldName => name of password field
     * @param passwordToInput => password to input into field
     */
    public void enterTextIntoPasswordField(WebElement element, String inputPasswordFieldName, String
            passwordToInput) {
        print("Enter password in " + inputPasswordFieldName + " field");
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(passwordToInput);
    }
}
