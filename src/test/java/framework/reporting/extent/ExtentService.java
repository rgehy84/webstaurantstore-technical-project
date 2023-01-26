package framework.reporting.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.*;
import framework.BaseUtilities;
import org.testng.TestNG;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;


public class ExtentService implements Serializable{
    private static final long serialVersionUID = -5008231199972325650L;

    public static synchronized ExtentReports getInstance() {
        return ExtentReportsLoader.INSTANCE;
    }
    @SuppressWarnings("unused")
    private ExtentReports readResolve() {
        return ExtentReportsLoader.INSTANCE;
    }

    private static class ExtentReportsLoader {
        private static final ExtentReports INSTANCE = new ExtentReports();
        private static final String[] DEFAULT_SETUP_PATH = new String[] { "extent.properties",
                "com/aventstack/adapter/extent.properties" };
        private static final String OUTPUT_PATH = "test-output/";
        private static final String EXTENT_REPORTER = "extent.reporter";
        private static final String START = "start";
        private static final String CONFIG = "config";
        private static final String OUT = "out";
        private static final String DELIM = ".";

        private static final String AVENT = "avent";
        private static final String BDD = "bdd";
        private static final String CARDS = "cards";
        private static final String EMAIL = "email";
        private static final String HTML = "html";
        private static final String KLOV = "klov";
        private static final String LOGGER = "logger";
        private static final String TABULAR = "tabular";

        private static final String INIT_HTML_KEY = EXTENT_REPORTER + DELIM + HTML + DELIM + START;
        private static final String INIT_KLOV_KEY = EXTENT_REPORTER + DELIM + KLOV + DELIM + START;
        private static final String INIT_LOGGER_KEY = EXTENT_REPORTER + DELIM + LOGGER + DELIM + START;

        private static final String CONFIG_HTML_KEY = EXTENT_REPORTER + DELIM + HTML + DELIM + CONFIG;
        private static final String CONFIG_KLOV_KEY = EXTENT_REPORTER + DELIM + KLOV + DELIM + CONFIG;
        private static final String CONFIG_LOGGER_KEY = EXTENT_REPORTER + DELIM + LOGGER + DELIM + CONFIG;

        private static final String OUT_LOGGER_KEY = EXTENT_REPORTER + DELIM + LOGGER + DELIM + OUT;
        private static String getDate = BaseUtilities.timestamp("yyyy-MM-dd");
        private static String getTime = BaseUtilities.timestamp("HHmmss");

        private ExtentReportsLoader() {
        }


        private static void createViaProperties() {
            ClassLoader loader = ExtentReportsLoader.class.getClassLoader();
            Optional<InputStream> is = Arrays.stream(DEFAULT_SETUP_PATH).map(x -> loader.getResourceAsStream(x))
                    .filter(x -> x != null).findFirst();
            if (is.isPresent()) {
                Properties properties = new Properties();
                try {
                    properties.load(is.get());

                    if (properties.containsKey(INIT_HTML_KEY)
                            && "true".equals(String.valueOf(properties.get(INIT_HTML_KEY))))
                        initHtml(properties);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private static void createViaSystem() {

            if ("true".equals(System.getProperty(INIT_HTML_KEY)))
                initHtml(null);


        }


        private static String getOutputPath(Properties properties, String key) {
            String out;
            if (properties != null && properties.get(key) != null)
                out = String.valueOf(properties.get(key));
            else
                out = System.getProperty(key);
            out = out == null || out.equals("null") || out.isEmpty() ? OUTPUT_PATH + key.split("\\.")[2] + "/" : out;
            return out;
        }

        private static File reportPath() {
            String reportPathStr = System.getProperty("reportPath");
            File reportPath;
            try {
                reportPath = new File(reportPathStr);
            } catch (NullPointerException e) {
                reportPath = new File(TestNG.DEFAULT_OUTPUTDIR + "/reports/" + getDate);
            }
            if (!reportPath.exists()) {
                if (!reportPath.mkdirs()) {
                    throw new RuntimeException("Failed to create output run directory");
                }
            }
            return reportPath;
        }


        /**
         * creates HTML report using date/timestamp
         * @param properties
         */
        private static void initHtml(Properties properties) {
//            String out = getOutputPath(properties, OUT_HTML_KEY);
//            ExtentHtmlReporter html = new ExtentHtmlReporter(out);
            File reportFile = new File(reportPath(), "report_"+ getDate + "_" + getTime +".html");
            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
            INSTANCE.attachReporter(spark);
        }




    }
}
