package keywords;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static drivers.DriverManager.getDriver;

public abstract class AppiumUtils {
    private static int EXPLICIT_TIME_OUT = 10;
    private static AppiumDriverLocalService service;
    public static WebElement getElement(By by) {
        return getDriver().findElement(by);
    }
    public static List<WebElement> getElements(By by) {
        return getDriver().findElements(by);
    }

    public static void clickElement(By by) {
        waitForElementVisible(by);
        getElement(by).click();
        LogUtils.info("Click on element " + by);
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getElement(by).sendKeys(value);
    }


    public static String getTextElement(By by) {
        waitForElementVisible(by);
        return getElement(by).getText();
    }


    public static String getAttributeElement(By by, String attributeName) {
        waitForElementVisible(by);
        return getElement(by).getAttribute(attributeName);
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_TIME_OUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementVisible(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementToAppear(By by, String value) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_TIME_OUT));
        wait.until(ExpectedConditions.attributeContains(getElement(by), "text", "value"));
    }

    public static void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_TIME_OUT));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void setActivity(String intent) {

        ((JavascriptExecutor) getDriver()).executeScript("mobile: startActivity", ImmutableMap.of("intent", intent));
    }

    public static AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {

        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress(ipAddress);
        builder.usingPort(port);
        builder.withAppiumJS(new File("C://Users//lenovo//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"));

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        //Xóa đi phần khởi động
        service.clearOutPutStreams();
        service.start();
        return service;
    }

    public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public static Double getFormattedAmount(String amouunt) {
        Double price = Double.parseDouble(amouunt.substring(1));
        return price;
    }

    public static boolean checkElementExist(By by) {
        List<WebElement> listElement = getDriver().findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " existing.");
            return true;
        } else {
            System.out.println("Element " + by + " NOT exist.");
            return false;
        }
    }

    public static Boolean checkElementExist(String xpath) {
        List<WebElement> listElement = getDriver().findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist.");
            return false;
        }
    }

    public static void assertAttributeEquals (By by, String attributeName, String expectedText) {
        waitForElementPresent(by);
        Assert.assertEquals(getAttributeElement(by, attributeName), expectedText);
    }

    public static String assertAttributeContains (By by, String attributeName, String expectedText, String message) {
        waitForElementVisible(by);
        Assert.assertTrue(getAttributeElement(by, attributeName).contains(expectedText), message);

        return getElement(by).getAttribute(attributeName);
    }

}
