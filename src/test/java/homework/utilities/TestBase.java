package homework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @BeforeTest
    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setReportName(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Test Report");
        htmlReporter.config().setJS("/* -- [ chart options ] -- */\n" +
                "            var options = {\n" +
                "              responsive: true,\n" +
                "              maintainAspectRatio: false,\n" +
                "              legend: {\n" +
                "                  position: \"right\",\n" +
                "                  labels: {\n" +
                "                      boxWidth: 10,\n" +
                "                      fontSize: 11,\n" +
                "                      lineHeight: 1,\n" +
                "                      fontFamily: \"Source Sans Pro\",\n" +
                "                      padding: 1\n" +
                "                  }\n" +
                "              },\n" +
                "              cutoutPercentage: 65\n" +
                "            };\n" +
                "\n" +
                "            function drawChart(ctx, config) {\n" +
                "                ctx.width = 100;\n" +
                "                ctx.height = 80;\n" +
                "                new Chart(ctx, config);\n" +
                "            }\n" +
                "\n" +
                "            /* -- [ parent chart ] -- */\n" +
                "            (function drawParentChart() {\n" +
                "                if (typeof statusGroup !== \"undefined\") {\n" +
                "                    var config = {\n" +
                "                        type: 'doughnut',\n" +
                "                            data: {\n" +
                "                                datasets: [{\n" +
                "                                    borderColor: 'transparent',\n" +
                "                                    data: [\n" +
                "                                        statusGroup.passParent, statusGroup.failParent, statusGroup.fatalParent, statusGroup.errorParent, statusGroup.warningParent, statusGroup.skipParent\n" +
                "                                    ],\n" +
                "                                    backgroundColor: [\n" +
                "                                        \"#00af00\", \"#F7464A\", \"#8b0000\", \"#ff6347\", \"#FDB45C\", \"#1e90ff\"\n" +
                "                                    ]\n" +
                "                                }],\n" +
                "                                labels: [ \"Pass\", \"Fail\", \"Fatal\", \"Error\", \"Warning\", \"Skip\" ]\n" +
                "                            },\n" +
                "                            options: options\n" +
                "                        };\n" +
                "\n" +
                "                        var ctx = document.getElementById(\"parent-analysis\").getContext('2d');\n" +
                "                        drawChart(ctx, config);\n" +
                "                }\n" +
                "            })();\n" +
                "\n" +
                "            /* -- [ children chart ] -- */\n" +
                "            (function drawChildChart() {\n" +
                "                if (typeof statusGroup !== \"undefined\" && statusGroup.childCount > 0) {\n" +
                "                    var config = {\n" +
                "                        type: 'doughnut',\n" +
                "                        data: {\n" +
                "                            datasets: [{\n" +
                "                                borderColor: 'transparent',\n" +
                "                                data: [\n" +
                "                                    statusGroup.passChild, statusGroup.failChild, statusGroup.fatalChild, statusGroup.errorChild, statusGroup.warningChild, statusGroup.skipChild,statusGroup.infoChild\n" +
                "                                ],\n" +
                "                                backgroundColor: [\n" +
                "                                    \"#00af00\", \"#F7464A\", \"#8b0000\", \"#ff6347\", \"#FDB45C\", \"#1e90ff\", \"#46BFBD\"\n" +
                "                                ]\n" +
                "                            }],\n" +
                "                            labels: [ \"Pass\", \"Fail\", \"Fatal\", \"Error\", \"Warning\", \"Skip\", \"Info\" ]\n" +
                "                        },\n" +
                "                        options: options\n" +
                "                    };\n" +
                "\n" +
                "                    var ctx = document.getElementById(\"child-analysis\").getContext('2d');\n" +
                "                    drawChart(ctx, config);\n" +
                "                }\n" +
                "            })();\n" +
                "\n" +
                "            /* -- [ grand-children chart ] -- */\n" +
                "            (function drawGrandChildChart() {\n" +
                "                if ($('#grandchild-analysis').length > 0 && typeof statusGroup !== \"undefined\" && statusGroup.grandChildCount > 0) {\n" +
                "                    var config = {\n" +
                "                        type: 'doughnut',\n" +
                "                        data: {\n" +
                "                            datasets: [{\n" +
                "                                borderColor: 'transparent',\n" +
                "                                data: [\n" +
                "                                    statusGroup.passGrandChild, statusGroup.failGrandChild, statusGroup.fatalGrandChild, statusGroup.errorGrandChild, statusGroup.warningGrandChild, statusGroup.skipGrandChild, statusGroup.infoGrandChild\n" +
                "                                ],\n" +
                "                                backgroundColor: [\n" +
                "                                    \"#00af00\", \"#F7464A\", \"#8b0000\", \"#ff6347\", \"#FDB45C\", \"#1e90ff\", \"#46BFBD\"\n" +
                "                                ]\n" +
                "                            }],\n" +
                "                            labels: [ \"Pass\", \"Fail\", \"Fatal\", \"Error\", \"Warning\", \"Skip\", \"Info\" ]\n" +
                "                        },\n" +
                "                        options: options\n" +
                "                    };\n" +
                "\n" +
                "                    var ctx = document.getElementById(\"grandchild-analysis\").getContext('2d');\n" +
                "                    drawChart(ctx, config);\n" +
                "                }\n" +
                "            })();\n" +
                "\n" +
                "            /* -- [ timeline ] -- */\n" +
                "            function getRandomColor() {\n" +
                "                var letters = '0123456789ABCDEF';\n" +
                "                var color = '#';\n" +
                "                for (var i = 0; i < 6; i++) {\n" +
                "                color += letters[Math.floor(Math.random() * 16)];\n" +
                "                }\n" +
                "                return color;\n" +
                "            }\n" +
                "\n" +
                "            (function drawTimelineChart() {\n" +
                "                if (typeof timeline !== \"undefined\") {\n" +
                "                    var datasets = [];\n" +
                "                    for (var key in timeline) {\n" +
                "                        datasets.push({ label:key, data:[timeline[key]], backgroundColor: getRandomColor(), borderWidth: 1 });\n" +
                "                    }\n" +
                "                    var ctx = document.getElementById('timeline').getContext('2d');\n" +
                "\n" +
                "                    new Chart(ctx, {\n" +
                "                        type: 'horizontalBar',\n" +
                "                        data: {\n" +
                "                            datasets: datasets\n" +
                "                        },\n" +
                "                        options: {\n" +
                "                            responsive: true,\n" +
                "                            maintainAspectRatio: false,\n" +
                "                            tooltips: {\n" +
                "                                mode: 'point'\n" +
                "                            },\n" +
                "                            scales: {\n" +
                "                                xAxes: [{\n" +
                "                                    stacked: true,\n" +
                "                                    gridLines: false\n" +
                "                                }],\n" +
                "                                yAxes: [{\n" +
                "                                    stacked: true,\n" +
                "                                    gridLines: false,\n" +
                "                                    barThickness: 25\n" +
                "                                }]\n" +
                "                            },\n" +
                "                            legend: {\n" +
                "                                display: false\n" +
                "                            }\n" +
                "                        }\n" +
                "                    });\n" +
                "                }\n" +
                "            })();");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        extentReports.setSystemInfo("Automation Test Engineer", "Hakan Kara");

        return extentReports;
    }
    @AfterMethod
    public void tearDown() {
        extentReports.flush(); //Extent Report icin

        driver.quit();
    }

    /**
     bu metot ile alert deki metin alinir
     */
    public static void alertText() {
        driver.switchTo().alert().getText();
    }

    //DropDown VisibleText
    /*
        Select select2 = new Select(gun);
        select2.selectByVisibleText("7");
        //ddmVisibleText(gun,"7"); --> Yukarıdaki kullanım yerine sadece method ile handle edebilirim
     */

    /** Bu metot xpath ile alinan locate sendkey gonderir
     * @param xPath buraya elementin xpath locati verilecek
     * @param sendKeys buraya elemente dongerilecek metin girilecek
     */
    public void webElementSendKeys(String xPath, String sendKeys){
        driver.findElement(By.xpath(xPath)).sendKeys(sendKeys);

    }

    /**
     bu metot ile dropdown menude yazan bir text secilir
     @param ddm girilmesi gereken menunun locatidir
     @param secenek dropdown menunde yazan gorun textin string halidir
     */
    public static void ddmVisibleText(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByVisibleText(secenek);
    }

    /**
     bu metot ile dropdown menudeki seceneklerden birisi index ile secilir
     @param ddm girilmesi gereken menunun locatidir
     @param index dropdown menunde yazan index numarasidir
     */
    public static void ddmIndex(WebElement ddm, int index) {
        Select select = new Select(ddm);
        select.selectByIndex(index);
    }
    /**
     bu metot ile dropdown menudeki valued seceneklerinden birisi strin gonderilerek secilir
     @paramType ddm girilmesi gereken menunun locatidir
     @paramType secenek dropdown menunde yazan ve girilmesi gereken value nun string halidir
     */
    public static void ddmValue(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByValue(secenek);
    }

    /**
     bu metot ile acilan ilk pencereye donulur
     @param sayi girilmesi gereken gecilecek pencerenin indexidir
     */
    public static void switchToWindow1(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tumWindowHandles.get(sayi));
    }

    /**
     bu metot ile acilan pencereye gecilir
     @param sayi girilmesi gereken gecilecek pencerenin indexidir
     */
    public static void switchToWindow2(int sayi) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[sayi].toString());
    }

    /**
     * Explicit wait yapar
     bu metot ile bir element gorulene kadar kodlar bekletilir
     @param element girilmesi gereken locate dir
     @param sayi girilmesi gereken saniyedir
     */
    public static void visibleWait(WebElement element, int sayi) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     bu metot ile bir element gorulene kadar kodlar bekletme suresi return eder
     @param locator girilmesi gereken locate dir
     @param locator girilmesi gereken saniyedir
     */
    public static WebElement visibleWait(By locator, int sayi) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sayi));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    /**
     bu metot ile alert gorulene kadar kodlar bekletilir
     @param sayi girilmesi gereken saniyedir
     */
    public static void alertWait(int sayi) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    /**
     bu metot ile tum sayfanin screenshot i alinir
     */
    public static void tumSayfaScreenShoot() {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot" + tarih + ".png";
        TakesScreenshot ts = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     bu metot ile webelementin screenshot i alinir
     @param element girilmesi gereken locate dir
     */
    public static void webElementScreenShoot(WebElement element) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/webElementScreenshot" + tarih + ".png";
        try {
            FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     bu metot ile mause element ustunde bekletilir
     @param webElement girilmesi gereken locate dir
     */
    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }

    /**
     bu metot ile herhangi bir webelemente JavascriptExecutor kullanarak tiklayabilirim
     @param webElement girilmesi gereken locate dir
     */
    public static void clickByJavaScript(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("arguments[0].click();", webElement);

    }

    /**
     * bu metot elementin ustune JavascriptExecutor ile goturur
     @param webElement girilmesi gereken locate dir
     */
    public static void scrollIntoViewByJavaScript(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;//Casting
        jse.executeScript("arguments[0].scrollIntoView(true);", webElement);

    }

    /**
     * bu metot javascript kodu ile elemente string gonderir(java sendkey() ile ayni)
     @param webElement girilmesi gereken locate dir
     @param string locate gonderilecek olan deger
     */
    public static void sendKeyWithJavaScript(String string, WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;//Casting
        jse.executeScript("arguments[0].value = '"+string+"';", webElement);

    }
    /**
     bu metot javascript kodu ile sayfayi en alta goturur
     */
    public static void scrollEndByJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     bu metot javascript kodu ile sayfayi en yukari goturur
     */
    public static void scrollTopByJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }

    /**bu metot sayfayi girilen string degerindeki elemente goturur
     * @param str girilmesi gereken elementin locatinin string halidir
     */
    public static void scrollToElementWithString(String str) {
        WebElement bottom = driver.findElement(By.xpath(str));
        Actions actions = new Actions(driver);
        actions.scrollToElement(bottom).perform();

    }

    /**
     * @param element girilmesi gereken locatidir
    bu metot sayfayi girilen elemente goturur
     */
    public static void scrollToElementWithWebElement(WebElement element) {
        WebElement bottom = element;
        Actions actions = new Actions(driver);
        actions.scrollToElement(bottom).perform();
    }

    /**
     * @param sleep girilmesi gereken saniye
    bu metot girilen saniye kadar java kodlarini bekletir
     */
    public static void threadSleep(int sleep) {
        try {
            Thread.sleep(sleep * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param str degeri expected metin
     * @param atr degeri actual metin
    bu metot expected metinin alertteki actual metini icerdigini dogrulamak icin
     */
    public static void assertTextContainsAssertTrue(String str, String atr) {
        assertTrue(str.contains(atr));
    }

    /**Bu metot bir webelementin secili olup olmadigini dogrular
     *  @param webElement girilecek webelement dir.
     */
    public void assertTrueIsSelected(WebElement webElement){
        Assert.assertTrue(webElement.isSelected());
    }

    /** Bu metot iki string degerin birbirine equal olup olmadigini dogrular
     @param str girilecek 1. metindir
     @param str1 girilecek 2. metindir
     */
    public void assertTrueEquals(String str, String str1){
        Assert.assertTrue(str.equals(str1));
    }

    /**
     bu metot alerti kabul eder
     */
    public static void switchAlertAccept() {
        driver.switchTo().alert().accept();
    }

    /**
     bu metot alerti reddeder
     */
    public static void switchAlertDismiss() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * @param str olarak alerte gonderilecek metin girilmeli
    bu metot girilen metini alerte mesaj olarak gonderir
     */
    public static void switchAlertSendKey(String str) {
        driver.switchTo().alert().sendKeys(str);
    }

    /**
     * @param str olarak xpath locati girilmeli
    bu metot girilen xpath locati ile webelement olusturur
     */
    public static String findByXpathString(String str) {
        String location = driver.findElement(By.xpath(str)).getText();
        return location;
    }

    /** bu metot girilen id locati ile webelement olusturur
     * @param str olarak id locati girilmeli
     */
    public static WebElement findByIdWebelement(String str) {
        WebElement w = driver.findElement(By.id(str));
        return w;
    }

    /**
     bu metot ekrani bir masue tekeri donmesi kadar asagi kaydirir
     */
    public static void pageDown() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    /**
     bu metot ekrani bir masue tekeri donmesi kadar yukari kaydirir
     */
    public static void pageUp() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    /**
     bu metot ekrani bir tik asagi kaydirir
     */
    public static void arrowDown() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
    }

    /**
     bu metot ekrani bir tik yukari kaydirir
     */
    public static void arrowUp() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_UP).perform();
    }

    /** bu metot webelementin gorunur oldugunu dogrulamak icindir
     @param  webElement girilecek olan webelementdir
     */
    public static void assertTrueIsDisplayed(WebElement webElement) {
        assertTrue(webElement.isDisplayed());
    }

    /**
     @param  webElement girilmesi gereken element locati
     @param str gonderilmek istenen metin
      *bu metot ile metin kutusuna sendkeys gonderir
     */
    public static void sendAttributeJavaScript(WebElement webElement, String str) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + str + "')", webElement);
    }

    /**
     @param id girilmesi gereken id degeri
     @param attributeName gonderilmesi gereken attribute ismi
     bu metot ile girilen attribute degerleri ile texti alabilirim
     */
    public static void getValueByJavaScript(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String string = js.executeScript("return document.getElementById('" + id + "')." + attributeName + "").toString();
        System.out.println(string);
        //        NOT: document.querySelector("p").value;  -> TAG KULLANILABILIR
//             document.querySelector(".example").value; -> CSS DEGERI KULLANILABILIR
//             document.querySelector("#example").value; -> CSS DEGERI KULLANILABILIR
    }

    /**
     *  JavaScript ile webelement olusturma
     * @param javascriptYolu internet sitesinden sag klik ile JS yolunu kopyala ile alınan metin olacak
     */

    public static WebElement webelementJavaScript(String javascriptYolu) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webElement = (WebElement) js.executeScript("return "+javascriptYolu+"");
        return webElement;
    }

    /**
     *  JavaScript ile webelement olusturup isEnabled oldugunu sorgulama
     * @param str internet sitesinden sag klik ile JS yolunu kopyala ile alınan metin olacak
     */
    public static void assertIsEnabled(String str){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webElement = (WebElement) js.executeScript("return "+str+"");
        Assert.assertTrue(webElement.isEnabled());
    }

    /**
     *  JavaScript ile cift klik yapma
     * @param element ile locate verilir
     */
    public static void doubleClick (WebElement element){
        Actions actions=new Actions(driver);
        actions.doubleClick(element).perform();
    }


    /**bu metot search boxa sendkeys gonderir
     * @param webElement girilmesi gereken element dir
     * @param str sendkey ile gonderilmek istenen metindir
     */
    public static void typeWithJavaScript(WebElement webElement, String str) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + str + "')", webElement);
    }


    /**
     * Bu metot ile pencere degistirilir. ikinci pencereye gecilir.
     * @param firstPage parametresine ilk pencerenin handle degeri girilir.
     */

    public void switchToHandle( String firstPage){
        firstPage=driver.getWindowHandle();
        Set<String> pagesHandles=driver.getWindowHandles();
        for (String str: pagesHandles){
            if(!str.equals(firstPage)){
                driver.switchTo().window(str);

            }
        }

    }
    //*[contains(@name,'q')]
    //*[contains(@title,'Ara')]
    //*[contains(@maxlength,'2048')]
    //*[contains(@maxlength,'20')]
    // * [cointains ( @Attribute = ’Value’) ]
}