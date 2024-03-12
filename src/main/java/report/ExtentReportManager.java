package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import helpers.SystemsHelper;
public class ExtentReportManager {
    static ExtentReports extentReports;
    static String path = SystemsHelper.getCurrentDir() + "//ExtentReports//ExtentReport.html";

    public synchronized static ExtentReports initReports() {
        if (extentReports == null) {
            extentReports = new ExtentReports();
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Test Results");
        spark.config().setReportName("Mobile Automation Results | Ngọc Anh Tester");

        extentReports.attachReporter(spark);
        extentReports.setSystemInfo("Tester", "Ngọc Anh Từ");

        System.out.println("Extent Reports is installed.");

        return extentReports;
    }


}
