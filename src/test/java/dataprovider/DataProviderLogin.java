package dataprovider;

import helpers.ExcelHelper;
import helpers.SystemsHelper;
import org.testng.annotations.DataProvider;
import keywords.AppiumUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataProviderLogin {

    @DataProvider (name = "Login_Json")
    public Object[][] getDataJson() throws IOException {
        List<HashMap<String, String>> data = AppiumUtils.getJsonData(SystemsHelper.getCurrentDir() + "//src//test//resources//testData//eCommerce.json");

//        List<HashMap<String, String>> data = AppiumUtils.getJsonData(System.getProperty("user.dir") + "//src//test//resources//testData//eCommerce.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

    @DataProvider(name = "Login_Excel_Custom_Row")
    public Object[][] dataLoginCRMFromExcelCustomRow() {
        ExcelHelper excelHelpers = new ExcelHelper();
        Object[][] data = excelHelpers.getDataHashTable(SystemsHelper.getCurrentDir() + "//src//test//resources//testData//Book1.xlsx", "Form", 1, 2);
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "Login_Excel_Cell_Data")
    public Object[][] dataLoginFromExcelCellData() {
        ExcelHelper excelHelpers = new ExcelHelper();
        excelHelpers.setExcelFile(SystemsHelper.getCurrentDir() + "//src//test//resources//testData//Book1.xlsx", "Form");

        String name = excelHelpers.getCellData("name", 1);
        String gender = excelHelpers.getCellData("gender", 1);
        String country = excelHelpers.getCellData("country", 1);

        Object[][] data = {
                {name, gender, country}
        };
        return data;
    }

}
