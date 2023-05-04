package homework.tests.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.AutomationExcerciseNK;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class task20 {
/*
2. Navigate to url 'http://automationexercise.com'
3. Click on 'Products' button
4. Verify user is navigated to ALL PRODUCTS page successfully
5. Enter product name in search input and click search button
6. Verify 'SEARCHED PRODUCTS' is visible
7. Verify all the products related to search are visible
8. Add those products to cart
9. Click 'Cart' button and verify that products are visible in cart
10. Click 'Signup / Login' button and submit login details
11. Again, go to Cart page
12. Verify that those products are visible in cart after login as well

 */

    @Test
    public void task20() throws InterruptedException {
            AutomationExcerciseNK automationExcerciseNK = new AutomationExcerciseNK();
//        2. Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get(ConfigReader.getProperty("url"));

//        3. Click on 'Products' button
        automationExcerciseNK.products.click();

//        4. Verify user is navigated to ALL PRODUCTS page successfully
        Actions actions=new Actions(Driver.getDriver());
        actions.moveToElement(automationExcerciseNK.allProducts).build().perform();
        Thread.sleep(2000);
        assertTrue(automationExcerciseNK.allProducts.isDisplayed());

//        5. Enter product name in search input and click search button
automationExcerciseNK.searchProduct.click();
automationExcerciseNK.searchProduct.sendKeys("Tshirt");
automationExcerciseNK.searchButton.click();

//        6. Verify 'SEARCHED PRODUCTS' is visible
        actions.moveToElement(automationExcerciseNK.searchProduct).build().perform();
        Thread.sleep(2000);
        assertTrue(automationExcerciseNK.searchButton.isDisplayed());

//        7. Verify all the products related to search are visible
        List<WebElement> elements=Driver.getDriver().findElements(By.xpath("//div[@class='productinfo text-center']"));
for (WebElement element : elements) {
    assertTrue(element.isDisplayed());
    }

//        8. Add those products to cart
              actions.sendKeys(Keys.PAGE_DOWN).perform();
        for (WebElement element : elements) {
            Thread.sleep(2000);
            element.click();
        }

//        9. Click 'Cart' button and verify that products are visible in cart
automationExcerciseNK.chart.click();
        List<WebElement> description=Driver.getDriver().findElements(By.xpath("//td[@class='cart_description']"));
        for (WebElement element : description) {
            assertTrue(element.isDisplayed());
        }
        Thread.sleep(2000);

//        10. Click 'Signup / Login' button and submit login details
automationExcerciseNK.signupLogin.click();
automationExcerciseNK.enterName.sendKeys("pass");
automationExcerciseNK.emailSignup.sendKeys("pass@pass.com");
automationExcerciseNK.signupButton.click();

//        11. Again, go to Cart page
Thread.sleep(2000);
        automationExcerciseNK.chart.click();

//        12. Verify that those products are visible in cart after login as well
        Thread.sleep(2000);
        for (WebElement element : elements) {
            assertTrue(element.isDisplayed());
        }
    }
}
