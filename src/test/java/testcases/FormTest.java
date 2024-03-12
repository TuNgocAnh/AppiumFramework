package testcases;

import common.AndroidBaseTest;
import dataprovider.DataProviderLogin;

import org.testng.annotations.*;
import pages.FormPage;
import pages.ProductCatalogue;

import java.util.HashMap;
import java.util.Hashtable;

public class FormTest extends AndroidBaseTest {
    private FormPage formPage;
    private ProductCatalogue productCatalogue;

    @BeforeMethod(alwaysRun = true)
    public void preSetup() {
        formPage = new FormPage();
        formPage.setActivitydemo();
    }

    @Test(priority = 1, dataProvider = "Login_Json", dataProviderClass = DataProviderLogin.class)
    public void fillFormDataJson_Validation(HashMap<String, String> input) {

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelection(input.get("country"));
        productCatalogue = formPage.submitForm();
    }

    @Test(priority = 2, dataProvider = "Login_Excel_Custom_Row", dataProviderClass = DataProviderLogin.class)
    public void fillFormDataExcel_Validation(Hashtable<String, String> data) {

        formPage.setNameField(data.get("name"));
        formPage.setGender(data.get("gender"));
        formPage.setCountrySelection(data.get("country"));
    }

    @Test(priority = 3, description = "Verify toast error message displayed appropriated for login with empty name")
    public void fillForm_EmptyName() {

        formPage.setGender("female");
        formPage.setCountrySelection("Aruba");
        formPage.submitForm();
        formPage.verifyToastMessage("Please enter your name");
    }

    @Test(priority = 4)
    public void checkPlaceHolder() {
        formPage.verifyHint("Enter name here");
    }

}
