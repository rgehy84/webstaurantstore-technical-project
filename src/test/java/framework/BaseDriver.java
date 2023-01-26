package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseDriver extends BaseUtilities {
    private String urlToStartTests;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /***
     * maximizes the browser window and sets in implicit wait of 15 seconds before timing out
     * @return driver that can be used in tests without making the original driver accessible to the public
     */
    public WebDriver getDriver() {
        return driver.get();
    }



    /***
     * this uses the WebDriverManager to set the driver binaries. It avoids having to
     * manually maintain the driver binaries. This will also download the latest version of the drivers
     * @param browser => specifies the browser to prepare to testing
     */
    @Parameters("browser_to_run_tests")
    @BeforeClass
    static void setupClass(@Optional("CHROME") Browsers browser) {
        switch (browser) {
            case FIREFOX -> WebDriverManager.firefoxdriver().setup();
            case CHROME -> WebDriverManager.chromedriver().setup();
            case SAFARI -> WebDriverManager.safaridriver().setup();
            case IE -> WebDriverManager.iedriver().setup();
            case EDGE -> WebDriverManager.edgedriver().setup();
        }

    }

    /***
     * this method is used to create a new instance of the driver
     * @param browser => specifies which browser to run the tests
     */
    @Parameters("browser_to_run_tests")
    @BeforeMethod
    public void testSetup(@Optional("CHROME") Browsers browser) {
        switch (browser) {
            case FIREFOX -> driver.set( new FirefoxDriver());
            case CHROME -> driver.set(new ChromeDriver());
            case SAFARI -> driver.set(new SafariDriver());
            case IE -> driver.set(new InternetExplorerDriver());
            case EDGE -> driver.set(new EdgeDriver());
        }
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /***
     * this method is used to tear down the webdriver instance after each method is executed
     */
    @Parameters("close_browser_after_after_test_run")
    @AfterMethod
    public void teardownDriver(@Optional("true") boolean shouldCloseBrowserAfterTestExecution) {
        if (shouldCloseBrowserAfterTestExecution == true) {
            getDriver().close();
        }
    }

    @Parameters("url_to_execute_tests")
    @BeforeMethod
    public void setUrlToTest(@Optional("http://www.webstaurantstore.com/") String urlToStartTest) {
        this.urlToStartTests = urlToStartTest;
    }

    public String getUrlToStartTests() {
        return urlToStartTests;
    }
}
