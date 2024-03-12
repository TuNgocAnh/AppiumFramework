package testcases;

import common.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductCatalogue;

public class ProductTest extends AndroidBaseTest {
    private ProductCatalogue productCatalogue;
    private CartPage cartPage;

    @BeforeMethod(alwaysRun = true)
    public void preSetup() {

        productCatalogue = new ProductCatalogue();
        productCatalogue.setActivitydemo();
    }

    @Test (description = "Verify the product specific by index and add to Cart")
    public void addProductToCartByIndex () {
        productCatalogue.addProductToCartByIndex(0);
        productCatalogue.addProductToCartByIndex(0);
        cartPage = productCatalogue.goToCartPage();
    }

    @Test(description = "Verify 2 product scroll to specific and add to Cart")
    public void addProductToCartByName() {
        productCatalogue.addProductToCartByName("Jordan 6 Rings");
        productCatalogue.addProductToCartByName("Air Jordan 4 Retro");
        cartPage = productCatalogue.goToCartPage();
    }


}
