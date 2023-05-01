package homework.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider
    public Object[][] sehirVerileri(){
        return new Object[][] {{"İstanbul", "Marmara", "34"}, {"Diyarbakır", "Güneydoğu", "21"}, {"Ankara", "İçAnadolu", "06"}};


    }

    @DataProvider
    public Object[][] kullaniciBilgileri(){

        return new Object[][] {{"Ali", "Ali.123"}, {"Ayşe", "Ayşe.123"}, {"Fatma", "Ftm_987"}};
    }

    @DataProvider()
    public Object [][] customerData(){
        String path="src/test/java/resources/mysmoketestdata.xlsx";
        String sheetName="customer_info";

        ExcelUtils excelUtils= new ExcelUtils(path,sheetName);
        return excelUtils.getDataArrayWithoutFirstRow();

    }
}
