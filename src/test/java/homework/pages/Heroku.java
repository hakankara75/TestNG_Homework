package homework.pages;

import homework.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Heroku {
    public Heroku(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//button[@name='commit']")
    public WebElement login;
    @FindBy(xpath = "//div[@class='alert alert-danger']")
    public WebElement alert;



}
