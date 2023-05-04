package homework.pages;

import homework.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Demoqa {
    public Demoqa() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//div[@class='card-body'])[3]")
    public WebElement alertsFrameWindows;
    @FindBy(xpath = "(//li[@id='item-0'])[3]")
    public WebElement browserWindows;

    @FindBy(xpath = "//button[@id='tabButton']")
    public WebElement newTab;
}
