package homework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import homework.utilities.Driver;

public class BlueRentalPage {
    public BlueRentalPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement login;
    @FindBy(xpath = "//*[@id='formBasicEmail']")
    public WebElement email;
    @FindBy(xpath = "//*[@id='formBasicPassword']")
    public WebElement password;
    @FindBy(xpath = "//div[@class='dropdown']")
    public WebElement verify;
    @FindBy(xpath = "//button[text()=' Login']")
    public WebElement loginButton;
    @FindBy(xpath = "(//div[@class='invalid-feedback'])[1]")
    public WebElement hataMesaji;
    @FindBy(xpath = "//div[text()='User with email fake@bluerentalcars.com not found']")
    public WebElement hataMesaji2;
    @FindBy(id = "liwn3e6e5")
    public WebElement uyeGirisiHataMesaji;

    @FindBy(id = "dropdown-basic-button")
    public WebElement logOut1;
    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logOut2;

    @FindBy(name = "car")
    public WebElement selectACar;
    @FindBy(name = "pickUpLocation")
    public WebElement pickUp;

    @FindBy(xpath = "(//input[@placeholder='Select a place'])[2]")
    public WebElement dropOff;

    @FindBy(xpath = "//input[@name='pickUpDate']")
    public WebElement pickUpDate;
    @FindBy(xpath = "//input[@name='pickUpTime']")
    public WebElement pickUpTime;
    @FindBy(xpath = "//input[@name='dropOffDate']")
    public WebElement dropOffDate;
    @FindBy(xpath = "//input[@name='dropOffTime']")
    public WebElement dropOffTime;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement continueButton;
    @FindBy(xpath = "//div[text()='Please first login']")
    public WebElement errorMessage;
}
