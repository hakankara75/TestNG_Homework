package homework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertTrue;

public abstract class TestBase {
    //TestBase class'indan obje olusturmanin onune gecilmesi icin abstract yapilabilir
//Orn: TestBase base=new TestBase();
//Bu class'a extend ettigimiz test classlarinda ulasabiliriz.
    protected static WebDriver driver;
    protected static ExtentReports extentReports; //Extent Report icin: Raporlamayı başlatır
    protected static ExtentHtmlReporter extentHtmlReporter;//Extent Report icinÇ Raporu HTML formatında düzenler
    protected static ExtentTest extentTest;//Extent Report icin: Tüm test aşamalarında extentTest objesi ile bilgi ekleriz

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //Extent Report icin asagisi
        //----------------------------------------------------------------------------------------
        extentReports = new ExtentReports();
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/reports/extentReport_" + tarih + ".html";
        extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
        extentReports.attachReporter(extentHtmlReporter);
        //Raporda gözükmesini istediğimiz bilgiler için
        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Tester", "Hakan");
        extentHtmlReporter.config().setDocumentTitle("Extent Report");
        extentHtmlReporter.config().setReportName("Smoke Test Raporu");
        extentTest = extentReports.createTest("ExtentTest", "Test Raporu");

    }

    @AfterMethod
    public void tearDown() {
        extentReports.flush(); //Extent Report icin

        driver.quit();
    }

    public static void threadSleep(int sleep) {
        try {
            Thread.sleep(sleep * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static WebElement findXpathWebelement(String str) {
        WebElement w = Driver.getDriver().findElement(By.xpath(str));
        return w;
    }

    public static void assertTextContainsAssertTrue(String str, String atr) {
        assertTrue(str.contains(atr));
    }

    public static void switchAlertAccept() {
        Driver.getDriver().switchTo().alert().accept();
    }

    public static void switchAlertDismiss() {
        Driver.getDriver().switchTo().alert().dismiss();
    }

    public static void switchAlertSendKey(String str) {
        Driver.getDriver().switchTo().alert().sendKeys(str);
    }

    public String findByXpathString(String str) {
        String location = Driver.getDriver().findElement(By.xpath(str)).getText();
        return location;
    }

    public void findByXpathClick(String str) {
        Driver.getDriver().findElement(By.xpath(str)).click();
    }

    public void findByIdClick(String str) {
        Driver.getDriver().findElement(By.id(str)).click();
    }

    public WebElement findByIdWebelement(String str) {
        WebElement w = Driver.getDriver().findElement(By.id(str));
        return w;
    }

    public static void pageDown() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    public static void  pageUp() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    public static void arrowDown() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ARROW_DOWN).perform();
    }

    public static void arrowUp() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ARROW_UP).perform();
    }

    public static void assertDisplayedWebelement(String a) {
        assertTrue(Driver.getDriver().findElement(By.xpath(a)).isDisplayed());
    }

    public static void scrollToElement(String str) {
        WebElement bottom = Driver.getDriver().findElement(By.xpath(str));
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollToElement(bottom).perform();
        //bu kod locati alinan elemana kadar sayfayi asagi goturur
    }

    public static void tumSayfaScreenShoot() {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot" + tarih + ".png";
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //WebElement ScreenShot
    public static void webElementScreenShoot(WebElement element) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/webElementScreenshot" + tarih + ".png";
        try {
            FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  static void switchToWindow(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tumWindowHandles.get(sayi));
    }

    //SwitchToWindow2
    public  static void window(int sayi) {
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[sayi].toString());
    }
    //bu metot ile herhangi bir webelemente JavascriptExecutor kullanarak tiklayabilirim
    public static void clickByJavaScript(WebElement webElement){
        JavascriptExecutor jse= (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", webElement);

    }

    public static void scrollIntoViewByJavaScript(WebElement webElement){
        JavascriptExecutor jse=(JavascriptExecutor) Driver.getDriver();//Casting
        jse.executeScript("arguments[0].scrollIntoView(true);",webElement);

    }

    //bu metot ile sayfayi en alta kaydirabilirim
    public static void scrollEndByJavaScript(){
        JavascriptExecutor js= (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    //bu metot ile sayfayi en yukari kaydirabilirim
    public static void scrollTopByJavaScript(){
        JavascriptExecutor js= (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }

    public static void typeWithJavaScript(WebElement webElement, String str){
        JavascriptExecutor js= (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value', '"+str+"')", webElement);
    }

    //bu metot ile attribute degerleri ile texti alabilirim
    public static String getValueByJavaScript(String id, String attributeName){
        JavascriptExecutor js= (JavascriptExecutor) Driver.getDriver();
        String string= js.executeScript("return document.getElementById('"+id+"')."+attributeName+"").toString();
        System.out.println(string);
        return string;
        //        NOT: document.querySelector("p").value;  -> TAG KULLANILABILIR
//             document.querySelector(".example").value; -> CSS DEGERI KULLANILABILIR
//             document.querySelector("#example").value; -> CSS DEGERI KULLANILABILIR
    }

}
