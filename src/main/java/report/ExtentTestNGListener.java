package report;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.LogUtils;

import java.io.IOException;
public class ExtentTestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Running test case " + result.getName());

        ExtentTestManager.saveToReport(result.getMethod().getMethodName(), result.getMethod().getDescription());
        ExtentTestManager.addAuthors("Ngọc Anh Từ");
        ExtentTestManager.addCategories("Regression");
        ExtentTestManager.addDevices("Android Emulator");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        test.log(Status.PASS, "Test Passed"); (old)
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        LogUtils.error("Test case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable().toString());

        try {
            ExtentTestManager.getTest().addScreenCaptureFromPath
                    (ExtentTestManager.getScreenshotPath(result.getMethod().getMethodName()), result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExtentTestManager.getTest().fail(result.getThrowable());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is skipped.");
        LogUtils.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.error("Đây là test case bị Fail nhưng có phần Success: " + result.getName());
        LogUtils.error(result.getThrowable().toString());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtils.info("End testing " + context.getName());
        ExtentTestManager.endReport();
    }
}
