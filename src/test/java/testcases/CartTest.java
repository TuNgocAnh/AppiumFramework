package testcases;

import common.AndroidBaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductCatalogue;

public class CartTest extends AndroidBaseTest {
    private CartPage cartPage;
    private ProductCatalogue productCatalogue;

    @BeforeMethod()
    public void preSetup() {
        productCatalogue = new ProductCatalogue();
        productCatalogue.setActivitydemo();
    }

//    @Test(description = "Verify 2 item in Product matching with Checkout ")
//    public void verifyProductMatch () {
//
//        productCatalogue.addProductToCartByName("Jordan 6 Rings");
//        productCatalogue.addProductToCartByName("Air Jordan 4 Retro");
//        cartPage = productCatalogue.goToCartPage();
//
//        cartPage.verifyProductMatch(0,"Jordan 6 Rings");
//        cartPage.verifyProductMatch(1,"Air Jordan 4 Retro");
//    }
//
//    @Test(priority = 1, description = "Verify that the total Amount displayed in the checkout page matches with sum of total amount in the product page")
//    public void verifyPriceTotal () {
//        productCatalogue.addProductToCartByIndex(0);
//
//        cartPage = productCatalogue.goToCartPage();
//
//        cartPage.verifyTotalAmount();
//
//    }

    @Test
    public void navigateToWeb () throws InterruptedException {
        productCatalogue.addProductToCartByIndex(0);
        cartPage = productCatalogue.goToCartPage();
        cartPage.acceptTermsConditions();
        cartPage.submitOrder();
    }


}
