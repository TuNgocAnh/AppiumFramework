package common;

import drivers.DriverManager;
import helpers.PropertiesHelper;
import helpers.SystemsHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import keywords.AppiumUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class AndroidBaseTest {
    public AppiumDriverLocalService service;

    public AndroidDriver ConfigureAppium() throws IOException, URISyntaxException {
        PropertiesHelper.setDefaultFile();
        service = AppiumUtils.startAppiumServer(PropertiesHelper.getValue("ipAddress"), Integer.parseInt(PropertiesHelper.getValue("port")));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(PropertiesHelper.getValue("PlatformName"));
        options.setDeviceName(PropertiesHelper.getValue("AndroidDeviceName"));
        options.setAutomationName(PropertiesHelper.getValue("AutomationName"));

        options.setAppPackage(PropertiesHelper.getValue("AppPackage"));
        options.setAppActivity(PropertiesHelper.getValue("AppActivity"));

        // Tự động tải và cài đặt ChromeDriver
        WebDriverManager.chromedriver().setup();

        AndroidDriver driver = new AndroidDriver(new URI(PropertiesHelper.getValue("URL")).toURL(), options);

        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException, URISyntaxException {
        AndroidDriver driver = ConfigureAppium();
        DriverManager.setDriver(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
        service.stop();
    }

}



