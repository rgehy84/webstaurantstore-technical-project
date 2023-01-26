package framework.reporting.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("WebstaurantStore Technical Project Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Application", "WebstaurantStore Technical Project");
        extentReports.setSystemInfo("Author", "Ralph Gehy");
        return extentReports;
    }
}
