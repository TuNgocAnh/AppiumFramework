package pages;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import keywords.AppiumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static drivers.DriverManager.getDriver;
import static keywords.AndroidActions.longPressAction;
import static keywords.AppiumUtils.*;

public class CartPage {
    private By productName = By.id("com.androidsample.generalstore:id/productName");
    private By titleCart = By.id("com.androidsample.generalstore:id/toolbar_title");
    private By btnBack = By.id("com.androidsample.generalstore:id/appbar_btn_back");
    private By productPrice = By.id("com.androidsample.generalstore:id/productPrice");
    private By totalPrice = By.id("com.androidsample.generalstore:id/totalAmountLbl");
    private By terms = By.id("com.androidsample.generalstore:id/termsButton");
    private By acceptButton = By.id("android:id/button1");
    private By checkBox = By.className("android.widget.CheckBox");
    private By proceed = By.id("com.androidsample.generalstore:id/btnProceed");

    public void verifyProductMatch(int i, String name) {
        waitForElementVisible(titleCart);
        List<WebElement> elements = getElements(productName);

        // Tạo danh sách để lưu trữ các tên sản phẩm
        List<String> productNamesList = new ArrayList<>();

        // Lặp qua danh sách WebElement và lấy tên sản phẩm
        for (WebElement element : elements) {
            String productName = element.getText();
            productNamesList.add(productName);
        }

        Assert.assertEquals(productNamesList.get(i),name);
    }

    public void verifyTotalAmount () {
        List <WebElement> productPrices = getElements(productPrice);
        int countPrice = productPrices.size();

        double totalSum = 0;
        for (int i = 0 ; i < countPrice; i++) {
            String totalProductPriceString = productPrices.get(i).getText();
            Double totalProductPrice = getFormattedAmount(totalProductPriceString);
            totalSum = totalSum + totalProductPrice;
        }
        String totalAmountString = getTextElement(totalPrice);
        Double totalAmount = getFormattedAmount(totalAmountString);

        Assert.assertEquals(totalSum, totalAmount);
    }

    public void acceptTermsConditions()
    {
        AppiumUtils.waitForElementVisible(terms);
        WebElement termsElement = getElement(terms);
        longPressAction(termsElement);
        clickElement(acceptButton);
    }

    public void submitOrder() throws InterruptedException {
        clickElement(checkBox);
        clickElement(proceed);

        Thread.sleep(3000);

        Set<String> contexts =getDriver().getContextHandles();

        for(String contextName :contexts)
        {
            System.out.println(contextName);
        }
        getDriver().context("WEBVIEW_com.androidsample.generalstore");//chrome driver

        waitForElementVisible(By.name(("q")));
        setText(By.name("q"),"Appium Redirect ChromeDriver");

        Thread.sleep(1000);
        getDriver().findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        getDriver().pressKey(new KeyEvent(AndroidKey.BACK));

    }

    public void back() {
        clickElement(btnBack);
    }


}
