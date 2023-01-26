package framework;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static framework.reporting.extent.ExtentTestManager.getTest;

public class Assertions extends BaseUtilities {
    BaseDriver baseDriver = new BaseDriver();

    public void assertEquals(String validationMessage, String actualValidationValue, String expectedValidationValue) {
        assertEquals(validationMessage, actualValidationValue, expectedValidationValue, true);
    }
    public void assertEquals(String validationMessage, String actualValidationValue, String expectedValidationValue, boolean takeScreenshot) {
        try {
            Assert.assertEquals(actualValidationValue, expectedValidationValue);
            print(validationPassed(validationMessage)
                    + "\nExpected Result: " + expectedValidationValue
                    + "\nActual Result: " + actualValidationValue + "</pre>", Status.PASS);
            if(takeScreenshot) {
                takeScreenshot(Status.PASS);
            }
        } catch(Exception | AssertionError e) {
            print(validationFailed(validationMessage)
                    + "\nExpected Result: " + expectedValidationValue
                    + "\nActual Result: " + actualValidationValue + "</pre>", Status.FAIL);
            try {
                takeScreenshot(Status.FAIL);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
        if(baseDriver.getDriver() != null) {
            print("<pre>URL: <a href=\"" + baseDriver.getDriver().getCurrentUrl() + "\" target=\"_blank\" />" + baseDriver.getDriver().getCurrentUrl() + "</a></pre>");
        }
    }

    public void assertTrue(String validationMessage, boolean validationCondition) {
        assertTrue(validationMessage, validationCondition, true);
    }
    public void assertTrue(String validationMessage, boolean validationCondition, boolean takeScreenshot) {
        try {
            Assert.assertTrue(validationCondition);
            print(validationPassed(validationMessage) + "</pre>", Status.PASS);
            if(takeScreenshot) {
                takeScreenshot(Status.PASS);
            }
        } catch(Exception | AssertionError e) {
            print(validationFailed(validationMessage) + "</pre>", Status.FAIL);
            try {
                takeScreenshot(Status.FAIL);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
        if(baseDriver.getDriver() != null) {
            print("<pre>URL: <a href=\"" + baseDriver.getDriver().getCurrentUrl() + "\" target=\"_blank\" />" + baseDriver.getDriver().getCurrentUrl() + "</a></pre>");
        }
    }


    public void assertFalse(String validationMessage, boolean validationCondition) {
        assertFalse(validationMessage, validationCondition, true);
    }
    public void assertFalse(String validationMessage, boolean validationCondition, boolean takeScreenshot) {
        try {
            Assert.assertFalse(validationCondition);
            print(validationPassed(validationMessage) + "</pre>", Status.PASS);
            if(takeScreenshot) {
                takeScreenshot(Status.PASS);
            }
        } catch(Exception | AssertionError e) {
            print(validationFailed(validationMessage) + "</pre>", Status.FAIL);
            try {
                takeScreenshot(Status.FAIL);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
        if(baseDriver.getDriver() != null) {
            print("<pre>URL: <a href=\"" + baseDriver.getDriver().getCurrentUrl() + "\" target=\"_blank\" />" + baseDriver.getDriver().getCurrentUrl() + "</a></pre>");
        }
    }

    /**
     * Takes base64 screenshot for reporting
     * @param status pass the status to log to the reporting framework
     * @throws IOException throws exception
     */
    private void takeScreenshot(Status status) throws IOException {
        if(baseDriver.getDriver() != null) {
            try {

                getTest()
                        .log(status, "Screenshot",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64conversion(baseDriver.getDriver())).build());
            } catch (Exception e) {
                print("Screenshot could not be generated");
                print(e.getMessage());
            }
        }
    }

    /**
     * convert screenshot to base 64
     * @param driver pass in the driver to generate the screenshot
     * @return returns base64 of screenshot
     */
    private String base64conversion(WebDriver driver) {
        TakesScreenshot newScreen = (TakesScreenshot) driver;
        return newScreen.getScreenshotAs(OutputType.BASE64);
    }

    //simple method for failed validation to set the formatting
    private String validationFailed(String validationMessage) {
        return "<span style=\"color:red;font-weight:bold;\">VALIDATION FAILED</span>\n<pre>Validate: " + validationMessage;
    }

    //simple method for passed validation to set the formatting
    private String validationPassed(String validationMessage) {
        return "<span style=\"color:green;font-weight:bold;\">VALIDATION PASSED</span>\n<pre>Validate: " + validationMessage;
    }

    public void verifyLinks(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();

            if(httpURLConnect.getResponseCode()>=400)
            {
                print(linkUrl+" - "+httpURLConnect.getResponseMessage()+"is a broken link");
            }

            //Fetching and Printing the response code obtained
            else{
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
            }
            assertTrue("Validate that response codes are less than 400", httpURLConnect.getResponseCode() < 400, false);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
