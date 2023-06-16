package homework.tests.selenium_grid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.AssertJUnit.assertEquals;

public class SeleniumGrid1 {
    WebDriver driver;

    @Test
    public void testName() throws MalformedURLException, InterruptedException {

        driver=new RemoteWebDriver(new URL("http://192.168.1.101:4444"), new ChromeOptions());
        driver.get("https://www.bluerentalcars.com/");
        assertEquals("Blue Rental Cars | Cheap, Hygienic, VIP Car Hire", driver.getTitle());
        Thread.sleep(3000);
        driver.close();
    }
}
