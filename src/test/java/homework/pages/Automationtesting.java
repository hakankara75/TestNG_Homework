package homework.pages;

import homework.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Automationtesting {
    public  Automationtesting(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@href='#OKTab']")
    public WebElement alertWithOk;
    @FindBy(xpath = "//button[@class='btn btn-danger']")
    public WebElement clickButton;
    @FindBy(xpath = "(//a[@data-toggle='tab'])[2]")
    public WebElement alertWithCancel;
    @FindBy(xpath = "//button[@onclick='confirmbox()']")
    public WebElement clickButton2;
    @FindBy(xpath = "//a[@href='#Textbox']")
    public WebElement alertWithText;
    @FindBy(xpath = "//button[@onclick='promptbox()']")
    public WebElement clickButton3;
    @FindBy(xpath = "//p[@id='demo1']")
    public WebElement alertTextMessage;



}
