package common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class IOSBaseTest {

    public IOSDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() throws IOException, URISyntaxException {

        //set hàm properties
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//data.properties");

        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAnddress");
        String UR = prop.getProperty("port");
        prop.load(fis);

        service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//lenovo//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")).withIPAddress(ipAddress).usingPort(4724).build();
        service.start();

        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName(prop.getProperty("PlatformName"));
        options.setDeviceName(prop.getProperty("AndroidDeviceName"));
        options.setAutomationName(prop.getProperty("AutomationName"));
//        options.setApp("//Users//rahulshetty//workingcode//Appium//src//test//java//resources//TestApp 3.app"));

        driver = new IOSDriver(new URI(prop.getProperty("URL")).toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        service.stop();
    }

}
