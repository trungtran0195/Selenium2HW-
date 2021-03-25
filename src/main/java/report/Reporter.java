package report;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.log4j.Logger;

public class Reporter {

    private static final Logger logger = Logger.getLogger(Reporter.class);

    public static void log(String message) {
        logger.info(message);
        ExtentTestManager.getTest().log(Status.INFO, message);
    }

    public static void log(String[][] data) {
        ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createTable(data));
    }

    public static void logPass(String message) {
        logger.info(message);
        ExtentTestManager.getTest().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        logger.info(message);
        ExtentTestManager.getTest().log(Status.FAIL, message);
    }


    public static void logCode(String code) {
        ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createCodeBlock(code));
    }

    public static void logWarn(String message) {
        logger.info(message);
        ExtentTestManager.getTest().log(Status.WARNING, message);
    }

    public static void logSkip(String message) {
        logger.info(message);
        ExtentTestManager.getTest().log(Status.SKIP, message);
    }
}
