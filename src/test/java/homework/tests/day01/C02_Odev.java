package homework.tests.day01;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import homework.pages.BlueRentalPage;
import homework.utilities.ConfigReader;
import homework.utilities.Driver;
import homework.utilities.ReusableMethods;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
public class C02_Odev {



        @Test
        public void testName() {
/*
        Description:
        Kullanimda olmayan kullanıcı adi ve şifre ile giriş yapılamamalı
        Acceptance Criteria:
        Kullanici dogru email ve yanlis sifre girildiginde, hata mesajini alınmalı
        Hata Mesaji:
        Bad credentials
        Test Data:
        Customer email: jack@gmail.com
        Customer password: fakepass
*/
            //sayfaya gidelim
            Driver.getDriver().get(ConfigReader.getProperty("blue_Url"));
            BlueRentalPage blueRentalPage = new BlueRentalPage();

            //login olalim
            blueRentalPage.login.click();
            blueRentalPage.email.sendKeys(ConfigReader.getProperty("blueRentAcar_Email"),
                    Keys.TAB, ConfigReader.getProperty("blueRentAcar_Password"), Keys.ENTER);
            ReusableMethods.bekle(3);
            assertTrue(blueRentalPage.hataMesaji2.getText().equals("Bad credentials"));
        }
        @Test
        public void testName2() {
/*
ODEV-2
Description:
Geçerli email uzantısı olmadan kullanıcı girişi yapılamamalı
Acceptance Criteria:
Kullanici geçersiz email uzantısı yazdiginda, hata mesajini almalı
Error Message: email must be a valid email
Dogru email uzantisi girildiğinde hata mesajı alınmamalı
https://email-verify.my-addr.com/list-of-most-popularemail-
domains.php
User Story 4
*/
            Driver.getDriver().get(ConfigReader.getProperty("blueRentalAcar_Url"));
            BlueRentalPage blueRentalPage = new BlueRentalPage();
            blueRentalPage.login.click();
            blueRentalPage.email.sendKeys(ConfigReader.getProperty("blueRentAcar_FakeEmail"),
                    Keys.TAB, ConfigReader.getProperty("blueRentAcar_Password"), Keys.ENTER);
            ReusableMethods.bekle(3);
            assertTrue(blueRentalPage.hataMesaji.getText().contains("email must be a valid email"));
            Driver.closeDriver();

            Driver.getDriver().get(ConfigReader.getProperty("blueRentalAcar_Url"));
            blueRentalPage = new BlueRentalPage();
            blueRentalPage.login.click();
            blueRentalPage.email.sendKeys(ConfigReader.getProperty("blueRentAcar_Email"),
                    Keys.TAB, ConfigReader.getProperty("blueRentAcar_Password"), Keys.ENTER);
            ReusableMethods.bekle(3);
            assertTrue(blueRentalPage.verify.getText().contains("Sam Walker"));
            Driver.closeDriver();
        }
@Test
public void testName3() {
             /*   ODEV-3
Description:
Geçerli giriş yapmadan rezervasyon yapamamalı
Acceptance Criteria:
Kullanici arac bilgilerini girip CONTINUE RESERVATION butonuna tikladiginda
Ve giris yapilmadiginda
Hata mesaji almali : Please first login
Giris yapildiginda hata mesaji alınmamalı
User Story 5
     */
    //"https://www.bluerentalcars.com/" adresine git
    Driver.getDriver().get(ConfigReader.getProperty("blueRentalAcar_Url"));
    BlueRentalPage blueRentalPage = new BlueRentalPage();
    ReusableMethods.visibleWait(blueRentalPage.login, 15);

    //"Select a car" dan bir arac sec
    WebElement selectCar= blueRentalPage.selectACar;
    selectCar.click();
    ReusableMethods.ddmValue(selectCar,"11");
      ReusableMethods.bekle(3);

    //bilgileri doldur
    blueRentalPage.pickUp.sendKeys("Ankara", Keys.ENTER);
    blueRentalPage.dropOff.sendKeys("İstanbul", Keys.ENTER);
    blueRentalPage.pickUpDate.sendKeys("01.01.2023", Keys.ENTER);
    blueRentalPage.pickUpTime.sendKeys("08:00", Keys.ENTER);
    ReusableMethods.bekle(3);
    blueRentalPage.dropOffDate.sendKeys("05.01.2023", Keys.ENTER);
    blueRentalPage.dropOffTime.sendKeys("08:00", Keys.ENTER);
    ReusableMethods.bekle(3);

    //"continue reservation" butonunu tikla
    blueRentalPage.continueButton.click();
    ReusableMethods.bekle(3);

    //hata mesajı alındığını dogrula
    assertTrue(blueRentalPage.errorMessage.isDisplayed());
    Driver.closeDriver();

    //sayfaya gidelim
    Driver.getDriver().get(ConfigReader.getProperty("blueRentalAcar_Url"));
    BlueRentalPage blueRentalPage = new BlueRentalPage();

    //login olalim
    blueRentalPage.login.click();
    blueRentalPage.email.sendKeys(ConfigReader.getProperty("blueRentAcar_Email"),
            Keys.TAB, ConfigReader.getProperty("blueRentAcar_Password"), Keys.ENTER);
    ReusableMethods.bekle(3);
    assertTrue(blueRentalPage.verify.getText().equals("Sam Walker"));
    Driver.closeDriver();

}
    }

