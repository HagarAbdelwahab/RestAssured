package com.cyta.util.test.utilities;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ExtentReport {

    /**
     * @author RashwanM3 This Class manages reporting by Extent
     */
    public class ExtentReportManager {

        public static ExtentHtmlReporter htmlReporter;
        public static ExtentReports extent;
        public static ExtentTest test;

        /**
         * Initiates the extent report
         *
         * @throws MalformedURLException
         * @throws InterruptedException
         */
        public static void startReporting() throws MalformedURLException, InterruptedException {
            // start reporters
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\extentReport.html");

            // create ExtentReports and attach reporter(s)
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }

        /**
         * creates a toggle for the given test
         *
         * @param method
         * @throws IOException
         */
        public static void beforeMethod(Method method) throws IOException {
            // creates a toggle for the given test, adds all log events under it
            test = extent.createTest(method.getName());

        }

        /**
         * This method takes a screenshot after test method has run , method get the
         * result of test method then shows the result within the report
         *
         * @param result : get the status of the test case
         * @param driver
         * @throws MalformedURLException
         * @throws InterruptedException
         */
        public static void takeScreenshot(ITestResult result, AppiumDriver<MobileElement> driver)
                throws MalformedURLException, InterruptedException {

            String pathFail = "./screenshotsFailure/" + result.getName() + ".png";
            String pathSkip = "./screenshotsSkip/" + result.getName() + ".png";
            String pathSuccess = "./screenshotsSuccess/" + result.getName() + ".png";

            if (ITestResult.FAILURE == result.getStatus()) {
                try {

                    // Files.move(screenshot, new File("./screenshotsFailure/" + result.getName() +
                    // ".png"));
                    test.fail(result.getThrowable().getMessage(), MediaEntityBuilder
                            .createScreenCaptureFromPath(Utilities.captureScreenshot(pathFail, driver)).build());
                } catch (IOException e) {
                    e.printStackTrace();

                }
            } else if (ITestResult.SUCCESS == result.getStatus()) {
                try {
                    // Files.move(screenshot, new File("./screenshotsSuccess/" + result.getName() +
                    // ".png"));
                    test.pass("Test has passed", MediaEntityBuilder
                            .createScreenCaptureFromPath(Utilities.captureScreenshot(pathSuccess, driver)).build());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (ITestResult.SKIP == result.getStatus()) {
                try {
                    test.skip(result.getThrowable().getMessage(), MediaEntityBuilder
                            .createScreenCaptureFromPath(Utilities.captureScreenshot(pathSkip, driver)).build());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        /**
         * This method is to call flush writes everything to the log file
         */
        public static void flushReport() {
            // calling flush writes everything to the log file
            extent.flush();

        }
}
