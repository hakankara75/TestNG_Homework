package homework.pages;

import homework.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Amazon {
    public Amazon() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "nav-search-submit-button")
    public WebElement searchSubmitButton;


}
