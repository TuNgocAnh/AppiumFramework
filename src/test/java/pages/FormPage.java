package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import static drivers.DriverManager.getDriver;
import static keywords.AndroidActions.scrollToText;
import static keywords.AppiumUtils.*;

public class FormPage {
    private By nameField = By.id("com.androidsample.generalstore:id/nameField");
    private By femaleOption = By.id("com.androidsample.generalstore:id/radioFemale");
    private By maleOption = By.id("com.androidsample.generalstore:id/radioMale");
    private By countrySelection = By.id("com.androidsample.generalstore:id/spinnerCountry");
    private By shopButton = By.id("com.androidsample.generalstore:id/btnLetsShop");
    private By toastMessage = By.xpath("(//android.widget.Toast)[1]");
    private By hintNamefield = By.xpath("//android.widget.EditText[@hint = 'Enter name here']");

    public void setActivitydemo () {

        setActivity("com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    }
    public void setNameField (String name) {
        setText(nameField,name);
        getDriver().hideKeyboard();
    }
    public void setGender (String gender) {
        waitForElementVisible(femaleOption);
        waitForElementVisible(maleOption);
        if (gender.contains("female"))
            clickElement(femaleOption);
        else
            clickElement(maleOption);
    }

    public void setCountrySelection(String countryName) {
        clickElement(countrySelection);
        scrollToText(countryName);
        clickElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']"));
    }

    public void verifyToastMessage (String expectedText) {
        Assert.assertEquals(getElement(toastMessage).getAttribute("name"), expectedText);
    }

    public void verifyHint (String expectedText) {
        assertAttributeEquals(hintNamefield, "name", expectedText);
    }

    public void fillAllForm (String name, String gender, String countryName) {
        setText(nameField,name);
        getDriver().hideKeyboard();

        if (gender.contains("female"))
            clickElement(femaleOption);
        else
            clickElement(maleOption);

        clickElement(countrySelection);
        scrollToText(countryName);
        clickElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']"));

    }

    public ProductCatalogue submitForm () {
        clickElement(shopButton);
        return new ProductCatalogue();
    }

}
