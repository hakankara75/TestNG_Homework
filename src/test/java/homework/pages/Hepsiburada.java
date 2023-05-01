package homework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import homework.utilities.Driver;

public class Hepsiburada {
    public Hepsiburada() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "myAccount")
    public WebElement login;
    @FindBy(xpath = "//*[@id='formBasicEmail']")
    public WebElement email;
    @FindBy(id = "dropdown-basic-button")
    public WebElement verify;
    @FindBy(xpath = "(//div[@class='invalid-feedback'])[1]")
    public WebElement hataMesaji;
    @FindBy(xpath = "//div[text()='Bad credentials']")
    public WebElement hataMesaji2;
    @FindBy(id = "dropdown-basic-button")
    public WebElement logOut1;
    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logOut2;
    @FindBy(xpath = "//button[@class='ajs-button ajs-ok']")
    public WebElement ok;

}
