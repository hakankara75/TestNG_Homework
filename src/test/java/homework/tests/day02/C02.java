package homework.tests.day02;

import homework.pages.Demoqa;
import homework.utilities.ConfigReader;
import homework.utilities.Driver;
import homework.utilities.ReusableMethods;
import homework.utilities.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class C02 {
    @Test
    public void testName() {
        /*
https://demoqa.com/ url'ine gidin.
Alerts, Frame & Windows Butonuna click yap
""Please select an item from left to start practice."" yazısının görünür olduğunu doğrula
Sol'da açılan Menu den ""Browser Windows"" butonuna click yap
New Tab butonunun görünür olduğunu doğrula
New Tab butonuna click yap
Açılan yeni Tab da ""This is a sample page"" yazısının görünür olduğunu doğrula
İlk Tab'a geri dön
New Tab butonunun görünür olduğunu doğrula

 */

//https://demoqa.com/ url'ine gidin.
        Demoqa demoqa = new Demoqa();
        Driver.getDriver().get(ConfigReader.getProperty("demoqa_Url"));

//Alerts, Frame & Windows Butonuna click yap
        TestBase.arrowDown();
        TestBase.arrowDown();
        TestBase.threadSleep(2);
        demoqa.alertsFrameWindows.click();

//""Please select an item from left to start practice."" yazısının görünür olduğunu doğrula
       assertTrue(Driver.getDriver().findElement(By.xpath("//div[text()='Please select an item from left to start practice.']")).isDisplayed());

//Sol'da açılan Menu den ""Browser Windows"" butonuna click yap
        TestBase.threadSleep(2);
        demoqa.browserWindows.click();

//New Tab butonunun görünür olduğunu doğrula
        assertTrue(Driver.getDriver().findElement(By.xpath("//button[@id='tabButton']")).isDisplayed());

//New Tab butonuna click yap
        demoqa.newTab.click();

//Açılan yeni Tab da ""This is a sample page"" yazısının görünür olduğunu doğrula
        TestBase.switchToWindow1(1);
        Assert.assertTrue(Driver.getDriver().findElement(By.id("sampleHeading")).isDisplayed());
        TestBase.threadSleep(2);

//İlk Tab'a geri dön
        TestBase.switchToWindow1(0);

////New Tab butonunun görünür olduğunu doğrula
        assertTrue(Driver.getDriver().findElement(By.xpath("//button[@id='tabButton']")).isDisplayed());
        Driver.closeDriver();
    }
}
