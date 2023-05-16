package homework.tests.day03;

import homework.pages.Amazon;
import homework.utilities.ConfigReader;
import homework.utilities.Driver;
import homework.utilities.ReusableMethods;
import homework.utilities.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class C02_JavaScriptExecutor {
    @Test
    public void test() {
        Amazon amazon = new Amazon();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        ReusableMethods.extentReport();

        //"https://amazon.com/" adresine gidin
        Driver.getDriver().get(ConfigReader.getProperty("amazon_Url"));
        ReusableMethods.extentTestInfo("//\"https://amazon.com/\" adresine gidildi");

        //arama kutusuna "iphone" yazip arama tusuna basin
        WebElement aramaKutusu = (WebElement) js.executeScript("return document.getElementById('twotabsearchtextbox');");
        aramaKutusu.sendKeys("iphone");
        ReusableMethods.extentTestInfo("arama kutusuna \"iphone\" yazip arama tusuna basildi");

        //WebElement submit = (WebElement) js.executeScript("return document.getElementById('nav-search-submit-button');");
       // js.executeScript("arguments[0].click()",submit);
      /*arguments[0] 0. elementdir. submit burda tek element. yaninda baska elementler olsaydi 1,2 olabilirdi.
      arguments[0].click()",submit de click yerine baska metotlar da gelebilir.
       */
        TestBase.clickByJavaScript(amazon.searchSubmitButton);
        ReusableMethods.extentTestInfo("Rapor bitirildi");
        ReusableMethods.extentRaporBitir();
        Driver.closeDriver();








    }
}