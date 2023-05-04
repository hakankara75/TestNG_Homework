package homework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;


public class AutomationExcerciseNK {

    public AutomationExcerciseNK(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    //    1. Sayfanin Locatleri
    @FindBy(xpath = "//html")
    public WebElement homePage;

    @FindBy(xpath = "//a[@href='/product_details/1']")
    public WebElement firstProduct;

    @FindBy(xpath = "//img[@src='/static/images/home/logo.png']")
    public WebElement logo;

    @FindBy(xpath = "(//h2)[1]")
    public WebElement loginToYourAccountText;
    @FindBy(xpath = "(//h2)[3]")
    public WebElement newUserSignuptext;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    public WebElement email;
    @FindBy(xpath = "//input[@type='password']")
    public WebElement password;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;
    @FindBy(xpath = "//input[@type='text']")
    public WebElement enterName;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    public WebElement emailSignup;
    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement signupButton;
    @FindBy(xpath = "//b")
    public WebElement enterAccountInformationText;

    @FindBy(id = "uniform-id_gender1")
    public WebElement cinsiyetE;

    @FindBy(id = "uniform-id_gender2")
    public WebElement cinsiyetK;

    @FindBy(xpath = "//a[@href='/products']")
    public WebElement products;

    @FindBy(xpath = "//a[@href='/view_cart']")
    public WebElement cart;
    @FindBy(linkText = "Delete Account")
    public WebElement deleteAccount;

    @FindBy(xpath = "//b")
    public WebElement accountDeletedText;






































    //Products Sayfası locateleri
    @FindBy(xpath = "//img[@src='/static/images/shop/sale.jpg']")
    public WebElement brand;
    @FindBy(xpath = "//div[@class='brands_products']//h2")
    public WebElement brandName;
    @FindBy(xpath = "//a[@href='/brand_products/Polo']")
    public WebElement polo;
    @FindBy(xpath = "//li[@class='active']")
    public WebElement poloText;
    @FindBy(xpath = "//a[@href='/brand_products/Kookie Kids']")
    public WebElement kookieKids;
    @FindBy(xpath = "//li[@class='active']")
    public WebElement kookieKidsText;
    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement allProducts;
    @FindBy(id = "search_product")
    public WebElement searchProduct;
    @FindBy(id = "submit_search")
    public WebElement searchButton;
    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement searchedProduct;
    @FindBy(xpath = "(//a[@href='/view_cart'])[1]")
    public WebElement chart;
    @FindBy(xpath = "//li[@class='active']")
    public WebElement shoppingCart;
    @FindBy(xpath = "//td[@class='cart_description']")
    public WebElement description;
    @FindBy(xpath = "//a[@href='/login']")
    public WebElement signupLogin;




}
