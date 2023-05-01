package homework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import homework.utilities.Driver;

public class BlueRentalPage {
    public BlueRentalPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//*[@role='button'])[1]")
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

    @FindBy(xpath = "//option[text()='Select a car']")
    public WebElement selectACar;
    @FindBy(xpath = "(//input[@placeholder='Select a place'])[1]")
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
