package homework.tests.day02;

import homework.pages.Heroku;
import homework.utilities.ConfigReader;
import homework.utilities.DataProviderUtils;
import homework.utilities.Driver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class C03 {
  /*
Negative Test
1.  https://id.heroku.com/login sayfasina gidin
2.  Faker class'ı kullanarak Dataprovider ile 5 adet email ve passwordleri giriniz
3.  Login butonuna tiklayiniz
4.  "There was a problem with your login." texti gorunur olduğunu test edin
5.  sayfalari kapatiniz
*/

    @Test(dataProvider = "faker", dataProviderClass = DataProviderUtils.class)
    public void testName(String email, String password) {
        Heroku heroku = new Heroku();

//        1.  https://id.heroku.com/login sayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("herokuUrl"));

//        2.  Faker class'ı kullanarak Dataprovider ile 5 adet email ve passwordleri giriniz

        heroku.email.sendKeys(email);
        heroku.password.sendKeys(password);


//        3.  Login butonuna tiklayiniz
        heroku.login.click();

//        4.  "There was a problem with your login." texti gorunur olduğunu test edin
        assertTrue(heroku.alert.isDisplayed());

//        5.  sayfalari kapatiniz
    Driver.closeDriver();
}

}
