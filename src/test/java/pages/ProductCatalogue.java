package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static drivers.DriverManager.getDriver;
import static keywords.AppiumUtils.*;
import static keywords.AndroidActions.*;

public class ProductCatalogue {
    private By productAddCart = By.xpath("//android.widget.TextView[@text = 'ADD TO CART']");
    private By productName = By.id ("com.androidsample.generalstore:id/productName");
    private By productPrice = By.id ("com.androidsample.generalstore:id/productPrice");
    private By cart = By.id ("com.androidsample.generalstore:id/appbar_btn_cart");

    public void setActivitydemo () {
        setActivity("com.androidsample.generalstore/com.androidsample.generalstore.AllProductsActivity");

    }


    public void addProductToCartByIndex(int index)
    {
        waitForElementVisible(productAddCart);
        getElements(productAddCart).get(index).click();
    }

    public void addProductToCartByName(String name) {
        scrollToText(name);

        int productCount = getElements(productName).size();

        for (int i=0; i <productCount; i++) {
            String productNames = getElements(productName).get(i).getText();

            if (productNames.equalsIgnoreCase(name)) {
                getElements(productAddCart).get(i).click();
            }
        }
    }

    public CartPage goToCartPage()
    {
        clickElement(cart);
        return new CartPage();
    }

}
