package homework.tests.day02;


import homework.pages.AutomationExcerciseNK;
import homework.utilities.ConfigReader;
import homework.utilities.Driver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;

public class task19 {
/*
2. Navigate to url 'http://automationexercise.com'
3. Click on 'Products' button
4. Verify that Brands are visible on left side bar
5. Click on any brand name
6. Verify that user is navigated to brand page and brand products are displayed
7. On left side bar, click on any other brand link

8. Verify that user is navigated to that brand page and can see products

 */
    @Test
    public void task19() throws InterruptedException {
        AutomationExcerciseNK automationExcerciseNK=new AutomationExcerciseNK();

//        2. Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get(ConfigReader.getProperty("url"));

//        3. Click on 'Products' button
        automationExcerciseNK.products.click();

//        4. Verify that Brands are visible on left side bar
        Actions actions=new Actions(Driver.getDriver());
        actions.moveToElement(automationExcerciseNK.brand).build().perform();
        assertTrue(automationExcerciseNK.brand.isDisplayed());

//        5. Click on any brand name
        actions.moveToElement(automationExcerciseNK.polo).build().perform();
        Thread.sleep(2000);
        automationExcerciseNK.polo.click();

//        6. Verify that user is navigated to brand page and brand products are displayed
assertTrue(automationExcerciseNK.polo.isDisplayed());

//        7. On left side bar, click on any other brand link
        automationExcerciseNK.kookieKids.click();

//        8. Verify that user is navigated to that brand page and can see products
        assertTrue(automationExcerciseNK.kookieKidsText.isDisplayed());

    }
}
