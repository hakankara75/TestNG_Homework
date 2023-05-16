package homework.tests.day03;

import homework.pages.Amazon;
import homework.utilities.ConfigReader;
import homework.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class C01_JavaScriptExecutor_Shadow {
   @Test
    public void test() {
       Amazon bonigarcia=new Amazon();
       JavascriptExecutor js= (JavascriptExecutor) Driver.getDriver();

       //"https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html" adresine gidin
       Driver.getDriver().get(ConfigReader.getProperty("bonigarcia_Url"));

       //"Hello Shadow DOM" yazisini locate edin
       //xpath'i "document.getElementById("content");" olan scriptin basina "return" ekledik
       WebElement text= (WebElement) js.executeScript("return document.getElementById('content');");
       System.out.println(text.getText());
       assertEquals("Hello Shadow DOM", text.getText());
       Driver.closeDriver();



    }
}
