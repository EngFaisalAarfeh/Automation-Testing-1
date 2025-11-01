package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;

import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {
    ExtentReports extent = ExtentManager.getReporterObject();
    ExtentTest test;


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started");

        ExtentManager.startTest(result.getName(), result.getMethod().getDescription())
                .assignCategory(result.getMethod().getGroups())
                .assignAuthor("Eng.-Faisal-Arafeh");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed");

        ExtentManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed");
        test = ExtentManager.getTest();

        if (test != null) {

            test.log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());

            try {
                Object testClass = result.getInstance();
                if (testClass instanceof TestBase) {
                    WebDriver driver = ((TestBase) testClass).driver;


                    if (driver != null) {
                        // Ensure the Screenshots folder exists
                        File directory = new File("Screenshots");
                        if (!directory.exists()) directory.mkdirs();

                        // Take screenshot
                        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        String screenshotName = result.getName() + "_" + System.currentTimeMillis() + ".png";
                        String screenshotPath = "Screenshots/" + screenshotName;


                        // Save screenshot file
                        File destFile = new File(screenshotPath);
                        FileUtils.copyFile(src, destFile);
                       // test.addScreenCaptureFromPath("../" + screenshotPath, "Failure Screenshot");
                       test.addScreenCaptureFromPath("../" + screenshotPath, "<b><span style='color:red;'>Failure Screenshot</span></b>");
                    }
                }
            } catch (IOException e) {
                test.warning("Failed to capture screenshot: " + e.getMessage());
            }


        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped");
        ExtentManager.getTest().log(Status.SKIP, "Test Skipped");


    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Finished");
        extent.flush();
    }
}
