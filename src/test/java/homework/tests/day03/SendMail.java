package homework.tests.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import static org.testng.AssertJUnit.assertTrue;

public class SendMail {

    @Test
    public void testName() {
/*
Bu test için pom.xml'e jakarta.mail-api ve jakarta.mail dependencies eklendi
 */

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1); //belli araliklarla testi calistirir
        Runnable task = new Runnable() { //burasi da tekrar edecek gorevi tanimlar

            /**
             * Runs this operation.
             */
            @Override
            public void run() {
                WebDriver driver = null;
                try {
                    driver = new ChromeDriver();
                    driver.get("https://www.google.com");

                    WebElement search = driver.findElement(By.id("APjFqb"));
                    search.sendKeys("hakan");

                    assertTrue(driver.getPageSource().contains("hakan"));
                    if (driver.getCurrentUrl().equals("https://www.google.com/search?q=hakan&sca_esv=572136157&sxsrf=AM9HkKmgqDWDrFLvQAIFbjqA9Av5ZNeKiA%3A1696921801941&source=hp&ei=yfgkZfCTN86Nxc8P2Mec2AU&iflsig=AO6bgOgAAAAAZSUG2V0JwxZuOEkO7v0_IXv9sbtRrcQb&ved=0ahUKEwiwnNup9uqBAxXORvEDHdgjB1sQ4dUDCAo&uact=5&oq=hakan&gs_lp=Egdnd3Mtd2l6IgVoYWthbjIEECMYJzIEECMYJzIIEC4YgAQYsQMyCBAuGIAEGLEDMggQLhiABBixAzIIEC4YgAQYsQMyCBAuGIAEGLEDMggQLhiABBixAzILEC4YigUYsQMYgwEyBRAuGIAESM0NULUGWPAKcAF4AJABAJgBlwGgAfYEqgEDMC41uAEDyAEA-AEBqAIKwgIHECMY6gIYJ8ICDRAuGMcBGNEDGOoCGCfCAgsQABiABBixAxiDAcICERAuGIAEGLEDGIMBGMcBGNEDwgILEC4YgAQYsQMYgwHCAgcQIxiKBRgnwgILEAAYigUYsQMYgwHCAgUQABiABA&sclient=gws-wiz")) {
                        SendMailRaport.sendPlainTextEmail("hakanbatirhan@yahoo.com",
                                "Arama sonucu",
                                "Arama sonucu eslesti", true);

                    } else {
                        SendMailRaport.sendPlainTextEmail("hakanbatirhan@yahoo.com",
                                "Arama sonucu",
                                "Arama sonucu basarisiz", false);

                    }
                    driver.quit();

                } catch (Exception e) {

                }

            }

        };
        executorService.scheduleAtFixedRate(task, 0,10, TimeUnit.MINUTES); //her 10 dakikada 1 testi yapar



    }
}
