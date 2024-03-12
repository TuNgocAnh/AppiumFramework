package keywords;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import static drivers.DriverManager.getDriver;

public class AndroidActions {

    public static void longPressAction (WebElement ele) {
        ((JavascriptExecutor) getDriver()).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(), "duration", 2000));
    }

    public static void scrollToEndAction () {
        boolean canScrollMore;
        do
        {
            canScrollMore = (Boolean) ((JavascriptExecutor)getDriver()).executeScript("mobile: scrollGesture", ImmutableMap.of("left",100,"top",200,"width",200,"height",200,"percent",3.0));
        }
        while (canScrollMore);
    }

    public static void scrollToText(String text) {
        getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
    }


    public static void swipeAction (WebElement ele, String direction) {
        ((JavascriptExecutor)getDriver()).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),"direction", direction, "percent", 0.75));
    }

}
