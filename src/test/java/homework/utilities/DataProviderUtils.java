package homework.utilities;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider
    public Object[][] sehirVerileri(){
        return new Object[][] {{"İstanbul", "Marmara", "34"}, {"Diyarbakır", "Güneydoğu", "21"}, {"Ankara", "İçAnadolu", "06"}};


    }

    @DataProvider
    public Object[][] faker() {
        String email ="";
        String password ="";
        for (int i = 0; i < 5; i++) {
            Faker faker = new Faker();
             email = faker.internet().emailAddress();
             password = faker.internet().password();

        }
        return new Object[][]{{email, password}, {email, password},
                {email, password}, {email, password}, {email, password}};
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
