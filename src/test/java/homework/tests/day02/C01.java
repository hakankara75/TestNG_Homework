package homework.tests.day02;

import homework.pages.Automationtesting;
import homework.utilities.ConfigReader;
import homework.utilities.Driver;
import homework.utilities.ReusableMethods;
import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class C01 {
    /*
	Go to URL: http://demo.automationtesting.in/Alerts.html
	Click "Alert with OK" and click 'click the button to display an alert box:’
	Accept Alert(I am an alert box!) and print alert on console.
	Click "Alert with OK & Cancel" and click 'click the button to display a confirm box’
	Cancel Alert  (Press a Button !)
	Click "Alert with Textbox" and click 'click the button to demonstrate the prompt box’
	And then sendKeys «BootcampCamp» (Please enter your name)
	Finally print on console this message "Hello BootcampCamp How are you today" assertion these message.
	 */

    @Test
    public void testName() {
//        Go to URL: http://demo.automationtesting.in/Alerts.html
        Driver.getDriver().get(ConfigReader.getProperty("automationexercise_Url"));

//        Click "Alert with OK" and click 'click the button to display an alert box:’
        Automationtesting automationtesting = new Automationtesting();
        //automationtesting.alertWithOk.click();
        automationtesting.clickButton.click();
        ReusableMethods.bekle(2);

//        Accept Alert(I am an alert box!) and print alert on console.
        Driver.getDriver().switchTo().alert().accept();

//        Click "Alert with OK & Cancel" and click 'click the button to display a confirm box’

        automationtesting.alertWithCancel.click();
        automationtesting.clickButton2.click();
        ReusableMethods.bekle(2);

//      Click "Alert with Textbox" and click 'click the button to demonstrate the prompt box’
        automationtesting.alertWithText.click();
        automationtesting.clickButton3.click();
        ReusableMethods.bekle(2);

//        Cancel Alert  (Press a Button !)
        Driver.getDriver().switchTo().alert().dismiss();

//        And then sendKeys «BootcampCamp» (Please enter your name)
        Driver.getDriver().switchTo().alert().sendKeys("Hakan");

//        Finally print on console this message "Hello BootcampCamp How are you today" assertion these message.
       assertTrue(automationtesting.alertTextMessage.isDisplayed());

    }
}
