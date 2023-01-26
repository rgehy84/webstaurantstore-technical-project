package framework;

import com.aventstack.extentreports.Status;
import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static framework.reporting.extent.ExtentTestManager.getTest;

public class BaseUtilities {

    /***
     * simplified printing of the message to output since most statuses on the extent report will INFO.
     * For pass or fail, use the method with the status parameter
     * @param textToOutput => text that will be logged in reports
     */
    public void print(String textToOutput) {
        print(textToOutput, Status.INFO);
    }

    /***
     * this method is used in order to output the to the console, TestNG reporting, and ExtentReport. It prevents
     * having to use system output
     * @param textToOutput => text that will be logged in reports
     * @param status => message type displayed in the extent report
     */
    public void print(String textToOutput, Status status) {
        Reporter.log(textToOutput, true);

        try {
            getTest().log(status, textToOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses SimpleDateFormat to generate a timestamp
     * @param pattern format of the timestamp to generate
     * @return the generated timestamp
     */
    public static String timestamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * Generates a timestamp
     * @return Timestamp is returned in yyyy-MM-dd_HH-mm-ss format
     */
    public String timestamp() {
        String pattern = "yyyy-MM-dd_HH-mm-ss";
        return timestamp(pattern);
    }
}
