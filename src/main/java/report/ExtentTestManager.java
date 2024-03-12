package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import helpers.SystemsHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static drivers.DriverManager.getDriver;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentReportManager.initReports();

    public static ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest saveToReport (String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
    public static synchronized void endReport () {
        extent.flush();
    }
    public synchronized static void addAuthors(String author) {
        getTest().assignAuthor(author);
    }
    public synchronized static void addCategories(String category) {
        getTest().assignCategory(category);
    }
    public synchronized static void addDevices(String device) {
        getTest().assignDevice(device);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }

    public static String getScreenshotPath(String testcaseName) throws IOException {
        String dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());

        File source = getDriver().getScreenshotAs(OutputType.FILE);

        String path = SystemsHelper.getCurrentDir() + "//reports//screenshots";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
            LogUtils.info("Folder created: " + folder);
        }
        String filePath = path + File.separator + testcaseName + " " + dateFormat + ".png";
        FileUtils.copyFile(source, new File(filePath));

        return filePath;
    }

}
